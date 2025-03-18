package com.example.capstone1.Model;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Product {

    @NotEmpty(message = "id should not be empty")
    private String id;

    @NotEmpty(message = "name should not be empty")
    @Size( min= 4)
    private String name;

    @NotNull(message = "Price should not be empty")
    @Positive
    private int price;

    @NotEmpty(message = "Category id should not be empty")
    private String categoryID;


}
