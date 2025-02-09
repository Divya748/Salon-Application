package com.eager2code.repository;

import com.eager2code.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CategoryRepository extends JpaRepository<Category, Long> {
     Set<Category> findBySalonId(Long salonId) ;

}
