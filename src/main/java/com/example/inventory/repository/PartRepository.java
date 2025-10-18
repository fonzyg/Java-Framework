package com.example.inventory.repository;
import com.example.inventory.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PartRepository extends JpaRepository<Part, Long> {
}
