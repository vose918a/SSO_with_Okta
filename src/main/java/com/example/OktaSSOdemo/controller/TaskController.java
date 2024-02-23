package com.example.OktaSSOdemo.controller;

import com.example.OktaSSOdemo.controller.request.TaskDTO;
import com.example.OktaSSOdemo.document.Task;
import com.example.OktaSSOdemo.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@AllArgsConstructor
@CrossOrigin("*")
public class TaskController {
    @Autowired
    private final TaskService taskService;

    private String ownerId(Jwt jwt){
        return jwt.getClaimAsString("uid");
    }

    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@RequestBody TaskDTO task,
                                           @AuthenticationPrincipal Jwt jwt) throws Exception {
        Task saved = taskService.saveTask(task,ownerId(jwt));
        return saved != null ?
                ResponseEntity.ok(saved) :
                ResponseEntity.badRequest().build();
    }

    @PutMapping("/finishTask/{taskId}")
    public ResponseEntity<Task> finishTask(@AuthenticationPrincipal Jwt jwt,
                                           @PathVariable String taskId) throws Exception{
        Task updated = taskService.finishTask(ownerId(jwt),taskId);
        return updated != null ?
                ResponseEntity.ok(updated) :
                ResponseEntity.notFound().build();
    }

    @PutMapping("/pendingTask/{taskId}")
    public ResponseEntity<Task> pendingTask(@AuthenticationPrincipal Jwt jwt,
                                           @PathVariable String taskId) throws Exception{
        Task updated = taskService.pendingTask(ownerId(jwt),taskId);
        return updated != null ?
                ResponseEntity.ok(updated) :
                ResponseEntity.notFound().build();
    }

    @PutMapping("/updateDescription/{taskId}")
    public ResponseEntity<Task> updateDescription(@AuthenticationPrincipal Jwt jwt,
                                                  @PathVariable String taskId,
                                                  @RequestBody TaskDTO taskDTO) throws Exception{
        Task updated = taskService.updateTaskDescription(ownerId(jwt),taskId,taskDTO);
        return updated != null ?
                ResponseEntity.ok(updated) :
                ResponseEntity.notFound().build();
    }

    @GetMapping("/getTasks")
    public ResponseEntity<List<Task>> getTaskList(@AuthenticationPrincipal Jwt jwt) throws Exception {
        List<Task> List = taskService.getTasks(ownerId(jwt));
        return List != null ?
                ResponseEntity.ok(List) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteTask/{taskId}")
    public ResponseEntity<?> deleteTask(@AuthenticationPrincipal Jwt jwt,
                                        @PathVariable String taskId) throws Exception{
        return taskService.deleteTask(ownerId(jwt),taskId) ?
                ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
