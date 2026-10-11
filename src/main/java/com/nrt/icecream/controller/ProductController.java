package com.nrt.icecream.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @GetMapping
    public List<ProductResponse> getProducts(){
        return List.of(
                new ProductResponse(1L, "Vanilla Ice Cream", "Small", 5, true),
                new ProductResponse(2L, "Chocolate Ice Cream", "Medium", 7,false)
        );
    }

    public record ProductResponse(
            Long id,
            String name,
            String size,
            int price,
            boolean available
    ){
    }

}
