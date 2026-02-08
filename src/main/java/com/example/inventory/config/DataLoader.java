package com.example.inventory.config;

import com.example.inventory.entity.Part;
import com.example.inventory.entity.Product;
import com.example.inventory.repository.PartRepository;
import com.example.inventory.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final PartRepository partRepository;
    private final ProductRepository productRepository;

    public DataLoader(PartRepository partRepository, ProductRepository productRepository) {
        this.partRepository = partRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (partRepository.count() == 0) {
            loadSampleParts();
        }
        if (productRepository.count() == 0) {
            loadSampleProducts();
        }
    }

    private void loadSampleParts() {
        Part part1 = new Part("Brake Pads", 45.99, 15, 5, 50);
        Part part2 = new Part("Oil Filter", 12.50, 25, 10, 100);
        Part part3 = new Part("Air Filter", 18.75, 8, 5, 30);
        Part part4 = new Part("Spark Plugs", 8.99, 40, 20, 80);
        Part part5 = new Part("Battery", 89.99, 3, 2, 15);

        partRepository.save(part1);
        partRepository.save(part2);
        partRepository.save(part3);
        partRepository.save(part4);
        partRepository.save(part5);
    }

    private void loadSampleProducts() {
        // Get the parts that were already saved
        Part brakePads = partRepository.findById(1L).orElse(null);
        Part oilFilter = partRepository.findById(2L).orElse(null);
        Part airFilter = partRepository.findById(3L).orElse(null);
        Part sparkPlugs = partRepository.findById(4L).orElse(null);
        Part battery = partRepository.findById(5L).orElse(null);

        // Create products and associate parts with them
        Product product1 = new Product("Complete Brake Kit", 199.99, 5);
        if (brakePads != null) {
            product1.getParts().add(brakePads);
        }

        Product product2 = new Product("Engine Tune-Up Kit", 129.99, 8);
        if (sparkPlugs != null) {
            product2.getParts().add(sparkPlugs);
        }
        if (airFilter != null) {
            product2.getParts().add(airFilter);
        }
        if (oilFilter != null) {
            product2.getParts().add(oilFilter);
        }

        Product product3 = new Product("Oil Change Kit", 34.99, 12);
        if (oilFilter != null) {
            product3.getParts().add(oilFilter);
        }

        Product product4 = new Product("Suspension System", 349.99, 6);
        if (brakePads != null) {
            product4.getParts().add(brakePads);
        }

        Product product5 = new Product("Performance Air Intake Kit", 159.99, 10);
        if (airFilter != null) {
            product5.getParts().add(airFilter);
        }

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);
        productRepository.save(product5);
    }
}
