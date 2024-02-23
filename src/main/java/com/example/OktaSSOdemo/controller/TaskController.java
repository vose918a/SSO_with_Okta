package com.example.OktaSSOdemo.controller;

import com.example.OktaSSOdemo.controller.request.TaskDTO;
import com.example.OktaSSOdemo.document.Task;
import com.example.OktaSSOdemo.service.TaskService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**This is the principal controller now*/
@RestController
@RequestMapping("/task")
@AllArgsConstructor
@CrossOrigin("*")
public class TaskController {
    @Autowired
    private final TaskService taskService;
    /**This method is for extract the user id form JWT*/
    private String ownerId(Jwt jwt){
        return jwt.getClaimAsString("uid");
    }
    /**This method is a GET for the creation of Tasks and receives on the request body a taskDTO class object
    * and extract form header authentication a JWT*/
    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@RequestBody @NonNull TaskDTO task,
                                           @AuthenticationPrincipal Jwt jwt) throws Exception {
        Task saved = taskService.saveTask(task,ownerId(jwt));
        return saved != null ?
                ResponseEntity.ok(saved) :
                ResponseEntity.badRequest().build();
    }
    /**This method is a PUT for update the status of one task to finished, this method receives via path variable the id of the task to update and extract from the authentication header a JWT for search the task*/
    @PutMapping("/finishTask/{taskId}")
    public ResponseEntity<Task> finishTask(@AuthenticationPrincipal Jwt jwt,
                                           @PathVariable @NonNull String taskId) throws Exception{
        Task updated = taskService.finishTask(ownerId(jwt),taskId);
        return updated != null ?
                ResponseEntity.ok(updated) :
                ResponseEntity.notFound().build();
    }
    /**This method is a PUT for update the status of one task to pending, this method receives via path variable the id of the task to update and extract from the authentication header a JWT for search the task*/
    @PutMapping("/pendingTask/{taskId}")
    public ResponseEntity<Task> pendingTask(@AuthenticationPrincipal Jwt jwt,
                                           @PathVariable @NonNull String taskId) throws Exception{
        Task updated = taskService.pendingTask(ownerId(jwt),taskId);
        return updated != null ?
                ResponseEntity.ok(updated) :
                ResponseEntity.notFound().build();
    }
    /**This method is a PUT for update the description of one task,
     * this method receives in the path variable a id of task to update,
     * form the headers extract the authentication JWT and in the request body receives a TaskDTO with the description*/
    @PutMapping("/updateDescription/{taskId}")
    public ResponseEntity<Task> updateDescription(@AuthenticationPrincipal Jwt jwt,
                                                  @PathVariable String taskId,
                                                  @RequestBody @NonNull TaskDTO taskDTO) throws Exception{
        Task updated = taskService.updateTaskDescription(ownerId(jwt),taskId,taskDTO);
        return updated != null ?
                ResponseEntity.ok(updated) :
                ResponseEntity.notFound().build();
    }
    /**This method is a GET and return a user list task, the user is obtained form the JWT,
     * the JWT is grabbed of the authentication header*/
    @GetMapping("/getTasks")
    public ResponseEntity<List<Task>> getTaskList(@AuthenticationPrincipal Jwt jwt) throws Exception {
        List<Task> List = taskService.getTasks(ownerId(jwt));
        return List != null ?
                ResponseEntity.ok(List) :
                ResponseEntity.notFound().build();
    }
    /**This method is a DELETE, his receives a Task id to delete in the path variable and grabbed a JWT form headers to confirm if the user is owner of the task*/
    @DeleteMapping("/deleteTask/{taskId}")
    public ResponseEntity<?> deleteTask(@AuthenticationPrincipal Jwt jwt,
                                        @PathVariable @NonNull String taskId) throws Exception{
        return taskService.deleteTask(ownerId(jwt),taskId) ?
                ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
