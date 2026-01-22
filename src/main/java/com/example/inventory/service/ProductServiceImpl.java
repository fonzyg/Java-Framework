package com.example.inventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.inventory.entity.Product;
import com.example.inventory.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
    
    private final ProductRepository productRepository;
    
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
    
    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
    
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }
    
    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}