package com.example.inventory.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.inventory.entity.Part;

@Service
public class PartService {
    
    private List<Part> parts = new ArrayList<>();
    
    public PartService() {
        parts.add(new Part("Engine", 1200.00, 10, 5, 20));
        parts.add(new Part("Brake Pad", 45.00, 25, 10, 50));
        parts.add(new Part("Oil Filter", 12.50, 100, 20, 200));
        parts.add(new Part("Transmission", 2500.00, 5, 2, 15));
        parts.add(new Part("Alternator", 185.00, 15, 8, 30));
        
        for (int i = 0; i < parts.size(); i++) {
            parts.get(i).setId((long) (i + 1));
        }
    }
    
    public List<Part> findAll() {
        return new ArrayList<>(parts);
    }
    
    public Part findById(Long id) {
        return parts.stream()
                .filter(part -> part.getId() != null && part.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    
    public Part save(Part part) {
        if (part.getId() == null) {
            Long newId = parts.size() + 1L;
            part.setId(newId);
            parts.add(part);
        } else {
            for (int i = 0; i < parts.size(); i++) {
                if (parts.get(i).getId().equals(part.getId())) {
                    parts.set(i, part);
                    break;
                }
            }
        }
        return part;
    }
    
    public void deleteById(Long id) {
        parts.removeIf(part -> part.getId() != null && part.getId().equals(id));
    }
}