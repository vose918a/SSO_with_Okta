package com.example.OktaSSOdemo.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Document(collection = "Task")
@AllArgsConstructor @NoArgsConstructor
@Data @ToString
public class Task {
    @Id
    private UUID id;
    @Field
    private String description;
    @Field
    private LocalDate createdDate;
    @Field
    private Statues status;
    @Field
    private String ownerId;
}
