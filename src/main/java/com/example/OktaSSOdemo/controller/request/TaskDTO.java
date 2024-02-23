package com.example.OktaSSOdemo.controller.request;

import com.example.OktaSSOdemo.document.Statues;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TaskDTO {
    @NotBlank
    private String description;
}
