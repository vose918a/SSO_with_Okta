package com.example.OktaSSOdemo.repository;

import com.example.OktaSSOdemo.document.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends MongoRepository<Task, UUID> {
    List<Task> getTasksByCreatedDate(Date cretedDate);
    List<Task> getTasksByOwnerId(String ownerId);
}
