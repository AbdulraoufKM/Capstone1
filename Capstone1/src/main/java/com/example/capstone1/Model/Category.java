package com.example.capstone1.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Category {

    @NotEmpty(message = "id Should not be empty")
    private String id;

    @NotEmpty(message = "name should not be empty")
    @Size(min = 4)
    private String name;
}
