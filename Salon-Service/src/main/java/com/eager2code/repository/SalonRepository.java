package com.eager2code.repository;

import com.eager2code.model.Salon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalonRepository extends JpaRepository<Salon,Long> {
}
