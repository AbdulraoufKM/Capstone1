package com.example.capstone1.Controller;


import com.example.capstone1.Api.ApiResponse;
import com.example.capstone1.Model.Merchant;
import com.example.capstone1.Model.MerchantStock;
import com.example.capstone1.Service.MerchantService;
import com.example.capstone1.Service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/merchantStock")
@RequiredArgsConstructor

public class MerchantStockController {

    private final MerchantStockService merchantStockService;

    //========================CRUD==========================
    @GetMapping("/get")
    public ResponseEntity get() {
        return ResponseEntity.status(200).body(merchantStockService.getMerchantStocks());
    }

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody @Valid MerchantStock merchant, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        boolean isAdded = merchantStockService.add(merchant);

        if (isAdded) {
            return ResponseEntity.status(200).body(new ApiResponse("Merchant Stock added successfully"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Failed to add a merchant stock"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable String id, @RequestBody @Valid MerchantStock merchant, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        boolean isUpdated = merchantStockService.update(merchant, id);

        if (isUpdated) {
            return ResponseEntity.status(200).body(new ApiResponse("Merchant stock updated successfully"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Failed to update a merchant stock"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable String id) {

        boolean isDeleted = merchantStockService.delete(id);
        if (isDeleted) {
            return ResponseEntity.status(200).body(new ApiResponse("Merchant stock deleted successfully"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Failed to delete a Merchant stock"));
    }


    //==============================Endpoint==========================


    @PutMapping("/addStock/{productId}/{merchantId}/{amount}")
    public ResponseEntity merchantAddStocks(@PathVariable String productId, @PathVariable String merchantId, @PathVariable int amount) {

        if (amount <= 0) {
            return ResponseEntity.badRequest().body("Amount must be greater than zero");
        }

        boolean addStock = merchantStockService.addStockToMerchant(productId, merchantId, amount);

        if (addStock) {
            return ResponseEntity.status(200).body(new ApiResponse("Additional stock added successfully to Merchant Stock"));
        }
            return ResponseEntity.status(400).body(new ApiResponse(" Sorry Failed Process ,Check if the product and merchant IDs are correct."));
    }




}