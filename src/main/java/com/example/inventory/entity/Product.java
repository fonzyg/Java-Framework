package com.example.inventory.entity; // Package declaration for entity classes

import java.util.HashSet; // Import for HashSet collection implementation
import java.util.Set; // Import for Set interface

public class Product { // Entity class representing a product in the inventory system
    private Long id; // Unique identifier for the product
    private String name; // Name or description of the product
    private double price; // Price of the product in dollars
    private int inv; // Current inventory count of this product
    private Set<Part> parts = new HashSet<>(); // Set of parts that make up this product

    public Product() {} // Default no-argument constructor for object instantiation

    public Product(String name, double price, int inv) { // Parameterized constructor for creating products with initial values
        this.name = name; // Sets the product name from the provided parameter
        this.price = price; // Sets the product price from the provided parameter
        this.inv = inv; // Sets the current inventory from the provided parameter
    }

    // Getter and setter methods for accessing and modifying private fields
    public Long getId() { return id; } // Returns the unique identifier of the product
    public void setId(Long id) { this.id = id; } // Sets the unique identifier of the product
    
    public String getName() { return name; } // Returns the name of the product
    public void setName(String name) { this.name = name; } // Sets the name of the product
    
    public double getPrice() { return price; } // Returns the price of the product
    public void setPrice(double price) { this.price = price; } // Sets the price of the product
    
    public int getInv() { return inv; } // Returns the current inventory count
    public void setInv(int inv) { this.inv = inv; } // Sets the current inventory count
    
    public Set<Part> getParts() { return parts; } // Returns the set of parts that make up this product
    public void setParts(Set<Part> parts) { this.parts = parts; } // Sets the collection of parts for this product
}