package com.example.inventory.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.inventory.entity.Product;

@Service
public class ProductService {
    
    private List<Product> products = new ArrayList<>();
    
    public ProductService() {
        products.add(new Product("Car Engine Kit", 2500.00, 5));
        products.add(new Product("Brake System", 450.00, 15));
        products.add(new Product("Maintenance Package", 89.99, 50));
        products.add(new Product("Transmission Service Kit", 1250.00, 8));
        products.add(new Product("Electrical System Kit", 320.00, 12));
        
        for (int i = 0; i < products.size(); i++) {
            products.get(i).setId((long) (i + 1));
        }
    }
    
    public List<Product> findAll() {
        return new ArrayList<>(products);
    }
    
    public Product findById(Long id) {
        return products.stream()
                .filter(product -> product.getId() != null && product.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    
    public Product save(Product product) {
        if (product.getId() == null) {
            Long newId = products.size() + 1L;
            product.setId(newId);
            products.add(product);
        } else {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getId().equals(product.getId())) {
                    products.set(i, product);
                    break;
                }
            }
        }
        return product;
    }
    
    public void deleteById(Long id) {
        products.removeIf(product -> product.getId() != null && product.getId().equals(id));
    }
}