package com.example.inventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.inventory.entity.Part;
import com.example.inventory.repository.PartRepository;

@Service
public class PartServiceImpl implements PartService {
    
    private final PartRepository partRepository;
    
    @Autowired
    public PartServiceImpl(PartRepository partRepository) {
        this.partRepository = partRepository;
    }
    
    @Override
    public List<Part> findAll() {
        return partRepository.findAll();
    }
    
    @Override
    public Part findById(Long id) {
        return partRepository.findById(id).orElse(null);
    }
    
    @Override
    public Part save(Part part) {
        return partRepository.save(part);
    }
    
    @Override
    public void deleteById(Long id) {
        partRepository.deleteById(id);
    }
}