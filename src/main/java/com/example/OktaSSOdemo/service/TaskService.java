package com.example.OktaSSOdemo.service;

import com.example.OktaSSOdemo.controller.request.TaskDTO;
import com.example.OktaSSOdemo.document.Statues;
import com.example.OktaSSOdemo.document.Task;
import com.example.OktaSSOdemo.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TaskService {
    @Autowired
    private final TaskRepository taskRepository;

    public List<Task> getTasks(String userId) throws Exception{
        try {
            return taskRepository.getTasksByOwnerId(userId);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public Task saveTask(TaskDTO taskDTO,String ownerId) throws Exception {
        Task task = new Task();
        task.setId(UUID.randomUUID());
        task.setDescription(taskDTO.getDescription());
        task.setStatus(Statues.PENDING);
        task.setCretedDate(Date.valueOf(LocalDate.now()));
        task.setOwnerId(ownerId);
        try {
            return taskRepository.save(task);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
//    TODO: Create the correct method to update the task
//    public Task updateTask(TaskDTO taskDTO,String ownerId) throws Exception {
//        try {
//            Optional<Task> oldTask = taskRepository.findById(taskDTO.getId());
//            Task newTask = new Task();
//            newTask.setId(oldTask.get().getId());
//        } catch (Exception e){
//            throw new Exception(e.getMessage());
//        }
//
//    }
}
