package com.eager2code.controller;

import com.eager2code.pojo.CategoryDTO;
import com.eager2code.pojo.SalonDTO;
import com.eager2code.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/categories/salon-owner")
@RequiredArgsConstructor
public class SalonCategoryController {

    private final CategoryService categoryService;
    @PostMapping("/saveCategories")
    public ResponseEntity<String> saveCategories(@RequestBody CategoryDTO categoryDTO){
        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setId(1L);
        categoryService.saveCategory(categoryDTO,salonDTO);
        return new ResponseEntity<>(
                "Categories are saved successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/delCategory/{categoryId}")
    public ResponseEntity<String> deleteCategory(
            @PathVariable Long categoryId) throws Exception {
        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setId(1L);
        categoryService.deleteCategoryById(categoryId,salonDTO.getId());
        return new ResponseEntity<>(
                "Categories are deleted successfully", HttpStatus.OK);
    }

}
