package com.example.capstone1.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data

public class User {

    @NotEmpty
    private String id;

    @NotEmpty
    @Size(min = 6)
    private String username;

    @NotEmpty
    @Size(min = 7)
//    @Pattern(regexp = "[A-Za-z0-9]" )
    private String password;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Pattern(regexp = ("Admin|Customer"),message = "must be either  Admin or Customer ")
    private String role;

    @NotNull
    @Positive
    private int balance;




}
