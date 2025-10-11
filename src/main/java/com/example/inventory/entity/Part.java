package com.example.inventory.entity; // Package declaration for entity classes

import java.util.HashSet; // Import for HashSet collection implementation
import java.util.Set; // Import for Set interface

public class Part { // Entity class representing a part in the inventory system
    private Long id; // Unique identifier for the part
    private String name; // Name or description of the part
    private double price; // Price of the part in dollars
    private int inv; // Current inventory count of this part
    private int min; // Minimum allowed inventory level for this part
    private int max; // Maximum allowed inventory level for this part
    private Set<Product> products = new HashSet<>(); // Set of products that use this part

    public Part() {} // Default no-argument constructor for object instantiation

    public Part(String name, double price, int inv, int min, int max) { // Parameterized constructor for creating parts with initial values
        this.name = name; // Sets the part name from the provided parameter
        this.price = price; // Sets the part price from the provided parameter
        this.inv = inv; // Sets the current inventory from the provided parameter
        this.min = min; // Sets the minimum inventory level from the provided parameter
        this.max = max; // Sets the maximum inventory level from the provided parameter
    }

    // Getter and setter methods for accessing and modifying private fields
    public Long getId() { return id; } // Returns the unique identifier of the part
    public void setId(Long id) { this.id = id; } // Sets the unique identifier of the part
    
    public String getName() { return name; } // Returns the name of the part
    public void setName(String name) { this.name = name; } // Sets the name of the part
    
    public double getPrice() { return price; } // Returns the price of the part
    public void setPrice(double price) { this.price = price; } // Sets the price of the part
    
    public int getInv() { return inv; } // Returns the current inventory count
    public void setInv(int inv) { this.inv = inv; } // Sets the current inventory count
    
    public int getMin() { return min; } // Returns the minimum inventory level
    public void setMin(int min) { this.min = min; } // Sets the minimum inventory level
    
    public int getMax() { return max; } // Returns the maximum inventory level
    public void setMax(int max) { this.max = max; } // Sets the maximum inventory level
    
    public Set<Product> getProducts() { return products; } // Returns the set of products using this part
    public void setProducts(Set<Product> products) { this.products = products; } // Sets the collection of products using this part
}