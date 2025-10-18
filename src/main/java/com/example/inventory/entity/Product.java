package com.example.inventory.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Product name is required")
    @Size(min = 1, max = 255, message = "Product name must be between 1 and 255 characters")
    private String name;
    @DecimalMin(value = "0.01", message = "Price must be greater than $0.00")
    @NotNull(message = "Price is required")
    private Double price;
    @Min(value = 0, message = "Inventory cannot be negative")
    @NotNull(message = "Inventory is required")
    private Integer inv;
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
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public Integer getInv() { return inv; }
    public void setInv(Integer inv) { this.inv = inv; }
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", inv=" + inv +
                '}';
    }
}
