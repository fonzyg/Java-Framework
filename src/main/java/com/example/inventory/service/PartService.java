package com.example.inventory.service;
import com.example.inventory.entity.Part;
import com.example.inventory.repository.PartRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class PartService {
    private final PartRepository partRepository;
    public PartService(PartRepository partRepository) {
        this.partRepository = partRepository;
    }
    public List<Part> findAll() {
        return partRepository.findAll();
    }
    public Part findById(Long id) {
        return partRepository.findById(id).orElse(null);
    }
    public Part save(Part part) {
        return partRepository.save(part);
    }
    public void deleteById(Long id) {
        partRepository.deleteById(id);
    }
}
