package com.example.OktaSSOdemo.service;

import com.example.OktaSSOdemo.controller.request.TaskDTO;
import com.example.OktaSSOdemo.document.Statues;
import com.example.OktaSSOdemo.document.Task;
import com.example.OktaSSOdemo.repository.TaskRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
/**This is the service for Task CRUD his call to repository and work with him*/
@Service
@AllArgsConstructor
public class TaskService {
    @Autowired
    private final TaskRepository taskRepository;
/**This function return one task from a task list, is a private function. Receives a task list and id of the task required*/
    private Task getTaskFromList (@NonNull List<Task> tasks, UUID taskId) throws Exception {
        for (Task task : tasks) {
            if (task.getId().equals(taskId)) {
                return task;
            } else {throw new Exception("not found");}
        }
        return null;
    }
/**This function return a task list form database, receives an userId for the user logged in*/
    public List<Task> getTasks(String userId) throws Exception{
        try {
            return taskRepository.getTasksByOwnerId(userId);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
/**This function save a task in database, his receives a taskDTO class object and an ownerId,
 * set a random UUID, set description from description of taskDTO,
 * set the status pending, set the now locale date and the owner id in correspond field.
 * Finally execute a save method*/
    public Task saveTask(@NonNull TaskDTO taskDTO, String ownerId) throws Exception {
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
/**This function update status to finished, receives a taskId and the ownerId,
 * in the function obtain a task list of the user, in this list is searched a task with
 * the id equal to taskId parameter, if exist a task the status is changed to Finished
 * and saved
 * else throw an Exception*/
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
    /**This function update status to pending, receives a taskId and the ownerId,
     * in the function obtain a task list of the user, in this list is searched a task with
     * the id equal to taskId parameter, if exist a task the status is changed to Pending
     *  and save else throw an Exception*/
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
/**This function update the description of task, receives an Owner id, Task id and a taskDTO object class
 * in the function, he obtains a task list and after search in this list
 * a task with id equal to task id parameter, if this task exist,
 * the description is updated(the old description is set with the description of the taskDTO object)
 * finally, the task is saved, else the function throw an Exception whit he respects message*/
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
/**This function delete a task from database, he receives by parameters an owner id and the task id,
 * he obtains a task list of user with the equal id with owner id, and if in the list
 * of tasks exist a task with same id of task id passed by parameters, if called from repository a method
 * to delete, if a task with same id no exist, a function throw an exception.*/
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
