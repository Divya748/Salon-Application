package com.eager2code.service;

import com.eager2code.model.Category;
import com.eager2code.pojo.CategoryDTO;
import com.eager2code.pojo.SalonDTO;

import java.util.Set;

public interface CategoryService {

    Category saveCategory(CategoryDTO categoryDTO, SalonDTO salonDTO);

    Set<CategoryDTO> getAllCategoriesBySalonId(Long salonId);

    CategoryDTO getCategoryById(Long categoryId) throws Exception;

    void deleteCategoryById(Long categoryId, Long salonId) throws Exception;
}
