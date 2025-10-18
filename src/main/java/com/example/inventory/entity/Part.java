package com.example.inventory.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
@Entity
@Table(name = "parts")
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Part name is required")
    @Size(min = 1, max = 50, message = "Part name must be between 1 and 50 characters")
    private String name;
    @DecimalMin(value = "0.01", message = "Price must be greater than $0.00")
    @NotNull(message = "Price is required")
    private Double price;
    @Min(value = 0, message = "Inventory cannot be negative")
    @NotNull(message = "Current inventory is required")
    private Integer inv;
    @Min(value = 0, message = "Minimum inventory cannot be negative")
    @NotNull(message = "Minimum inventory is required")
    private Integer minInv;
    @Min(value = 1, message = "Maximum inventory must be at least 1")
    @NotNull(message = "Maximum inventory is required")
    private Integer maxInv;
    public Part() {}
    public Part(String name, Double price, Integer inv, Integer minInv, Integer maxInv) {
        this.name = name;
        this.price = price;
        this.inv = inv;
        this.minInv = minInv;
        this.maxInv = maxInv;
    }
    public boolean isInventoryValid() {
        if (inv == null || minInv == null || maxInv == null) {
            return false;
        }
        return inv >= minInv && inv <= maxInv;
    }
    public boolean isMinMaxValid() {
        if (minInv == null || maxInv == null) {
            return false;
        }
        return maxInv > minInv;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public Integer getInv() { return inv; }
    public void setInv(Integer inv) { this.inv = inv; }
    public Integer getMinInv() { return minInv; }
    public void setMinInv(Integer minInv) { this.minInv = minInv; }
    public Integer getMaxInv() { return maxInv; }
    public void setMaxInv(Integer maxInv) { this.maxInv = maxInv; }
}
