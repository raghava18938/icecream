package com.nrt.icecream.service;

import com.nrt.icecream.dto.CreateProductRequest;
import com.nrt.icecream.entity.Product;
import com.nrt.icecream.exception.ProductNotFoundException;
import com.nrt.icecream.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {


    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    @Transactional
    public Product createProduct(CreateProductRequest request){

        Product product = new Product();
        product.setName(request.name());
        product.setSize(request.size());
        product.setPrice(request.price());
        product.setAvailable(request.available());
        return productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public Product getProductById(Long id){
        return productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException(id));
    }



}
