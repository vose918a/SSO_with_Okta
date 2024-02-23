package com.example.OktaSSOdemo.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TaskDTO {
    @NotBlank
    private String description;
}
