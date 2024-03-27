package com.example.OktaSSOdemo.repository;

import com.example.OktaSSOdemo.document.Statues;
import com.example.OktaSSOdemo.document.Task;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class TaskRepositoryTest {

    @Autowired @Mock
    TaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        Task task = Task.builder()
                .id(UUID.fromString("8aaee3b4-95e1-42d2-9ce8-7c6309ac8e47"))
                .description("Task repository")
                .createdDate(LocalDate.now())
                .status(Statues.PENDING)
                .ownerId("00uf74n6rlWBQf2Jm5d7").build();
        taskRepository.save(task);
    }

    @AfterEach
    public void tearDown(){
        taskRepository.deleteById(UUID.fromString("8aaee3b4-95e1-42d2-9ce8-7c6309ac8e47"));
    }

    @Test
    public void findTaskByIdIgnoreCaseFound(){
        Task task = taskRepository.getTaskById(UUID.fromString("8aaee3b4-95e1-42d2-9ce8-7c6309ac8e47"));
        assertEquals(task.getDescription(),"Task repository");
    }

    @Test
    public void findTaskByIdIgnoreCaseNotFound(){
        Task task = taskRepository.getTaskById(UUID.randomUUID());
        assertNull(task);
    }

    @Test
    public void findTaskByOwnerIdIgnoreCaseFound(){
        List<Task> task = taskRepository.getTasksByOwnerId("00uf74n6rlWBQf2Jm5d7");
        assertEquals(task.size(), 3);
    }

    @Test
    public void findTaskByOwnerIdIgnoreCaseNotFound(){
        List<Task> task = taskRepository.getTasksByOwnerId("00uf74n6rlWBQf2");
        assertEquals(task.size(),0);
    }
}