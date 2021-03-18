package com.example.autogarage.repository;

import com.example.autogarage.model.Reparation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReparationRepository extends JpaRepository<Reparation, Long> {
}
