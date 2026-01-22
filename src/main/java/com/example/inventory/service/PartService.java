package com.example.inventory.service;

import java.util.List;

import com.example.inventory.entity.Part;

public interface PartService {
    List<Part> findAll();
    Part findById(Long id);
    Part save(Part part);
    void deleteById(Long id);
}
