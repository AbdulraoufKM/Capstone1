package com.example.capstone1.Controller;

import com.example.capstone1.Api.ApiResponse;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity get(){
        return ResponseEntity.status(200).body(productService.getProducts());
    }

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody @Valid Product product, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        boolean isAdded = productService.add(product);

        if (isAdded){
            return ResponseEntity.status(200).body(new ApiResponse("Product added successfully"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Failed to add a product"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable String id, @RequestBody @Valid Product product , Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        boolean isUpdated = productService.update(product,id);

        if (isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("product updated successfully"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Failed to update a product"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable String id){

        boolean isDeleted = productService.delete(id);
        if (isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("Product deleted successfully"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Failed to delete a product"));
    }


    //===================Endpoint======================



    //#1 wishlist

    @PutMapping("/wishlist/{userid}/{productId}")
    public ResponseEntity addProductToWishlist(@PathVariable String userid, @PathVariable String productId) {
        int addWishlist = productService.addProductToWishlist(userid, productId);
        if (addWishlist == 1)
            return ResponseEntity.status(400).body(new ApiResponse("Product Already In wishlist"));
        else if (addWishlist == 3)
            return ResponseEntity.status(400).body(new ApiResponse("Invalid User ID"));

        return ResponseEntity.status(200).body(new ApiResponse("Product Added To wishlist"));
    }

    @GetMapping("/getWishlist/{userId}")
    public ResponseEntity getWishlist( @PathVariable String userId ) {
        List<Product> wishlist = productService.getWishlistProducts(userId);

        if (wishlist == null) {
            return ResponseEntity.status(400).body(new ApiResponse("Not Found"));
        }
        return ResponseEntity.status(200).body(wishlist);
    }

    //---------------------------------------------------
    //#2 rating and review

    @PutMapping("/addReview/{productId}/{review}/{rating}")
    public ResponseEntity<String> addReviewAndRating(@PathVariable String productId, @PathVariable String review, @PathVariable int rating) {

        String result = productService.addReviewAndRating(productId, review, rating);
        if (result.contains("successfully")) {
            return ResponseEntity.status(200).body(result);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    @GetMapping("/getReviews/{productId}")
    public ResponseEntity<List<String>> getReviews(@PathVariable String productId) {
        return ResponseEntity.status(200).body(productService.getReviews(productId));
    }

    @GetMapping("/getAverageRating/{productId}")
    public ResponseEntity<Double> getAverageRating(@PathVariable String productId) {
        return ResponseEntity.status(200).body(productService.getAverageRating(productId));
    }


    //------------------------------
    //Extra




    @PutMapping("/changeProductName/{userId}/{productId}/{newName}")
    public ResponseEntity<String> changeProductName(@PathVariable String userId, @PathVariable String productId, @PathVariable String newName) {

        String result = productService.changeProductName(userId, productId, newName);

        if (result.contains("successfully")) {
            return ResponseEntity.status(200).body(result);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }









}
