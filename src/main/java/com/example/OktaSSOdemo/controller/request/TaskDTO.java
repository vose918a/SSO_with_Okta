package com.example.OktaSSOdemo.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
/**Basic data transfer object to model the request for creation of Task*/
@Data
public class TaskDTO {
    @NotBlank
    private String description;
}
