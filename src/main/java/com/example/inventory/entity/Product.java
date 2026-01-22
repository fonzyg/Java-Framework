package com.example.inventory.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "products")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Product name is required")
    private String name;
    
    @Min(value = 0, message = "Price must be positive")
    private double price;
    
    @Min(value = 0, message = "Inventory must be positive")
    private int inv;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "product_parts",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "part_id")
    )
    private Set<Part> parts = new HashSet<>();
    
    public Product() {}
    
    public Product(String name, Double price, Integer inv) {
        this.name = name;
        this.price = price;
        this.inv = inv;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    
    public int getInv() { return inv; }
    public void setInv(int inv) { this.inv = inv; }
    
    public Set<Part> getParts() { return parts; }
    public void setParts(Set<Part> parts) { this.parts = parts; }
}
