package com.example.capstone1.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MerchantStock {

    @NotEmpty(message = "id should not be empty")
    private String id;

    @NotEmpty(message = "product id should not be empty")
    private String productId;

    @NotEmpty(message = "merchant id should not be empty")
    private String merchantId;
    @Min(11)
    private int stock;


}
