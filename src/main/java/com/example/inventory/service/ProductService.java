package com.example.inventory.service;

import java.util.List;

import com.example.inventory.entity.Product;

public interface ProductService {
    List<Product> findAll();
    Product findById(Long id);
    Product save(Product product);
    void deleteById(Long id);
}
