package com.eager2code.service.impl;

import com.eager2code.mapper.CategoryMapper;
import com.eager2code.model.Category;
import com.eager2code.pojo.CategoryDTO;
import com.eager2code.pojo.SalonDTO;
import com.eager2code.repository.CategoryRepository;
import com.eager2code.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    /**
     * @param categoryDTO
     * @param salonDTO
     * @return
     */
    @Override
    public Category saveCategory(CategoryDTO categoryDTO, SalonDTO salonDTO) {
        Category category = CategoryMapper.MAPPER.mapToCategory(categoryDTO);
        return categoryRepository.save(category);
    }

    /**
     * @param salonId
     * @return
     */
    @Override
    public Set<CategoryDTO> getAllCategoriesBySalonId(Long salonId) {
        Set<Category> categories = categoryRepository.findBySalonId(salonId);
        return CategoryMapper.MAPPER.mapToSet(categories);
    }

    /**
     * @param categoryId
     * @return
     */
    @Override
    public CategoryDTO getCategoryById(Long categoryId) throws Exception {

        Category cat = categoryRepository.findById(categoryId).orElse(null);
        if(cat == null){
            throw new Exception("Category not exist");
        }
        return CategoryMapper.MAPPER.mapToDto(cat);
    }

    /**
     * @param categoryId
     * @return
     */
    @Override
    public void deleteCategoryById(Long categoryId) throws Exception {

        Category cat = categoryRepository.findById(categoryId).orElse(null);
        if(cat != null){
            categoryRepository.deleteById(categoryId);
        }else{
            throw new Exception("Category not exist");
        }
    }
}
