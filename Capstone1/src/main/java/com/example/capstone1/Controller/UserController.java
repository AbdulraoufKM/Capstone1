package com.example.capstone1.Controller;

import com.example.capstone1.Api.ApiResponse;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Model.User;
import com.example.capstone1.Service.ProductService;
import com.example.capstone1.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ProductService productService;

    @GetMapping("/getUser")
    public ResponseEntity getUser(){
        return ResponseEntity.status(200).body(userService.getUsers());
    }

    @PostMapping("/addUser")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        boolean isAdded = userService.addUser(user);

        if (isAdded){
            return ResponseEntity.status(200).body(new ApiResponse("User added successfully"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Failed to add a user"));
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity updateUser(@PathVariable String id, @RequestBody @Valid User user , Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        boolean isUpdated = userService.updateUser(user,id);

        if (isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("User updated successfully"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Failed to update a user"));
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity deleteUser(@PathVariable String id){

        boolean isDeleted = userService.deleteUser(id);
        if (isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("User deleted successfully"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Failed to delete a user"));
    }

    @PostMapping("/buy/{userId}/{productId}/{merchantId}/")
    public ResponseEntity buyProduct(@PathVariable String userId ,@PathVariable String productId ,@PathVariable String merchantId ){
        int result = userService.buyProduct(userId ,productId ,merchantId );
        if(result == 0){
            return ResponseEntity.status(400).body(new ApiResponse("user id is not found"));
        }
        if(result == 1){
            return ResponseEntity.status(400).body(new ApiResponse("product id is not found"));
        }
        if(result == 2){
            return ResponseEntity.status(400).body(new ApiResponse("merchant id is not found"));
        }
        if(result == 3){
            return ResponseEntity.status(400).body(new ApiResponse("merchant Stock id is not found"));
        }
        if(result == 4){
            return ResponseEntity.status(400).body(new ApiResponse("inefficient balance"));
        }

        return ResponseEntity.status(200).body(new ApiResponse("product is bought successfully"));

    }

    @PutMapping("/changUsername/{id}/{oldName}/{newName}")
    public ResponseEntity changUsername(@PathVariable String id,@PathVariable String oldName,String newName){
        boolean isChanged=userService.changeUsername(id, oldName, newName);
        if(isChanged){
            return ResponseEntity.status(200).body(new ApiResponse("Username been changed successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Failed to change username"));
    }

    @GetMapping("/getRangPriceCategory/{categoryId}/{p1}/{p2}")
    public ResponseEntity getRangPriceCategory(@PathVariable String categoryId,@PathVariable int p1 ,@PathVariable int p2){
        if(userService.getRangPriceCategory(categoryId ,p1 ,p2) == null){
            return ResponseEntity.status(400).body( new ApiResponse( "Failed to get rang price category"));
        }
        return ResponseEntity.status(200).body(userService.getRangPriceCategory(categoryId ,p1 ,p2));
    }










}
