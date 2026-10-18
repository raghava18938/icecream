package com.nrt.icecream.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record CreateProductRequest(

        @NotBlank(message= "Product name is required")
        String name,

        @NotBlank(message="Product size is required")
        @Size(max=50)
        String size,

        @NotNull(message="Price is required")
        @DecimalMin(value="0.01", message= "Price must be positive")
        @Digits(integer=8, fraction=2)
        BigDecimal price,

        @NotNull(message= "Availability is required")
        Boolean available


        ) {
}
