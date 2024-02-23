package com.example.OktaSSOdemo.service;

import com.example.OktaSSOdemo.controller.request.TaskDTO;
import com.example.OktaSSOdemo.document.Statues;
import com.example.OktaSSOdemo.document.Task;
import com.example.OktaSSOdemo.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TaskService {
    @Autowired
    private final TaskRepository taskRepository;

    private Task getTaskFromList (List<Task> tasks, UUID taskId) throws Exception {
        for (Task task : tasks) {
            if (task.getId().equals(taskId)) {
                return task;
            } else {throw new Exception("not found");}
        }
        return null;
    }

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
        task.setCreatedDate(LocalDate.now());
        task.setOwnerId(ownerId);
        try {
            return taskRepository.save(task);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public Task finishTask(String ownerId, String taskId) throws Exception {
        try {
            List<Task> tasks = taskRepository.getTasksByOwnerId(ownerId);
            Task oldTask = getTaskFromList(tasks,UUID.fromString(taskId));
            if(oldTask != null) {
                oldTask.setStatus(Statues.FINISHED);
                return taskRepository.save(oldTask);
            }
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return null;
    }

    public Task pendingTask(String ownerId, String taskId) throws Exception {
        try {
            List<Task> tasks = taskRepository.getTasksByOwnerId(ownerId);
            Task oldTask = getTaskFromList(tasks,UUID.fromString(taskId));
            if(oldTask != null) {
                oldTask.setStatus(Statues.PENDING);
                return taskRepository.save(oldTask);
            }
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return null;
    }

    public Task updateTaskDescription(String ownerId,String taskId,TaskDTO taskDTO) throws Exception{
        try {
            List<Task> tasks = taskRepository.getTasksByOwnerId(ownerId);
            Task oldTask = getTaskFromList(tasks,UUID.fromString(taskId));
            if(oldTask != null) {
                oldTask.setDescription(taskDTO.getDescription());
                return taskRepository.save(oldTask);
            }
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return null;
    }

    public boolean deleteTask(String ownerId, String taskId) throws Exception{
        List<Task> tasks = taskRepository.getTasksByOwnerId(ownerId);
        if(getTaskFromList(tasks,UUID.fromString(taskId)) != null){
            taskRepository.deleteById(UUID.fromString(taskId));
            return true;
        }else{
            throw new Exception("Task with this Id non exist");
        }
    }
}
