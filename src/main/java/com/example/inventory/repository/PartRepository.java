package com.example.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.inventory.entity.Part;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {
}
