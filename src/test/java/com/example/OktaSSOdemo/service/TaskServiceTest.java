package com.example.OktaSSOdemo.service;

import com.example.OktaSSOdemo.document.Statues;
import com.example.OktaSSOdemo.document.Task;
import com.example.OktaSSOdemo.repository.TaskRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskServiceTest {

    @Autowired
    private TaskService mockTaskService;

    @MockBean
    private TaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        Task task = new Task(UUID.fromString("086bc844-4d99-4fed-beac-9d005c1791eb"),
                "MockTask"
                ,LocalDate.now(),
                Statues.PENDING,
                "00uf74n6rlWBQf2Jm5d9");
        Mockito.when(taskRepository.getTaskById(UUID.fromString("086bc844-4d99-4fed-beac-9d005c1791eb")))
                .thenReturn(task);
    }

    @Test
    void taskFounds() throws Exception {
        List<Task> tasks = mockTaskService.getTasks("00uf74n6rlWBQf2Jm5d9");
        assertEquals(1, tasks.size());
    }

    @Test
    void taskNotFounds() throws Exception{
        List<Task> tasks = mockTaskService.getTasks("000");
        assertEquals(tasks.size(),0);
    }

    @Test
    void saveTask() {
    }

    @Test
    void finishTask() throws Exception {
        Task task = taskRepository.getTaskById(UUID.fromString("086bc844-4d99-4fed-beac-9d005c1791eb"));
        mockTaskService.finishTask("00uf74n6rlWBQf2Jm5d9","086bc844-4d99-4fed-beac-9d005c1791eb");
        assertEquals(Statues.FINISHED.name(),task.getStatus());
    }

    @Test
    void pendingTask() {
    }

    @Test
    void updateTaskDescription() {
    }

    @Test
    void deleteTask() {
    }
}