package com.nrt.icecream.controller;


import com.nrt.icecream.dto.CreateProductRequest;
import com.nrt.icecream.entity.Product;
import com.nrt.icecream.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService  productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductResponse> getProducts(){
        return productService.getProducts()
                .stream()
                .map(product-> new ProductResponse(
                        product.getId(),
                        product.getName(),
                        product.getSize(),
                        product.getPrice(),
                        product.isAvailable()
                ))
                .toList();

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProducts(@Valid @RequestBody CreateProductRequest request){
        Product savedProduct = productService.createProduct(request);
        return toResponse(savedProduct);
    }

    private ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getSize(),
                product.getPrice(),
                product.isAvailable()
        );
    }

    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable("id") Long id){
        return toResponse(productService.getProductById(id));
    }





    public record ProductResponse(
        Long id,
        String name,
        String size,
        BigDecimal price,
        boolean available
    )
    {
    }

}
