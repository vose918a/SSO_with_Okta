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

@RestController
@RequestMapping("/task")
@AllArgsConstructor
@CrossOrigin("*")
public class TaskController {
    @Autowired
    private final TaskService taskService;

    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@RequestBody TaskDTO task, @AuthenticationPrincipal Jwt jwt) throws Exception {
        String owner = jwt.getClaimAsString("uid");
        Task saved = taskService.saveTask(task,owner);
        return saved != null ?
                ResponseEntity.ok(saved) :
                ResponseEntity.badRequest().build();
    }

}
