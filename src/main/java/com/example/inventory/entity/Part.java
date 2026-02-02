package com.example.inventory.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "parts")
public class Part {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Part name is required")
    private String name;
    
    @Min(value = 0, message = "Price must be positive")
    private double price;
    
    @Min(value = 0, message = "Inventory must be positive")
    private int inv;

    @Min(value = 0, message = "Minimum inventory must be positive")
    private Integer minInv;

    @Min(value = 0, message = "Maximum inventory must be positive")
    private Integer maxInv;

    // Constructors
    public Part() {}

    public Part(String name, double price, int inv, int minInv, int maxInv) {
        this.name = name;
        this.price = price;
        this.inv = inv;
        this.minInv = minInv;
        this.maxInv = maxInv;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    
    public int getInv() { return inv; }
    public void setInv(int inv) { this.inv = inv; }

    public Integer getMinInv() { return minInv; }
    public void setMinInv(Integer minInv) { this.minInv = minInv; }

    public Integer getMaxInv() { return maxInv; }
    public void setMaxInv(Integer maxInv) { this.maxInv = maxInv; }

    // Validation methods
    public boolean isInventoryValid() {
        if (minInv == null || maxInv == null) {
            return true;
        }
        return inv >= minInv && inv <= maxInv;
    }

    public boolean isMinMaxValid() {
        if (minInv == null || maxInv == null) {
            return true;
        }
        return minInv <= maxInv;
    }
}
