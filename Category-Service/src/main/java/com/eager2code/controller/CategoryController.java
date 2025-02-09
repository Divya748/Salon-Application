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
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;



    @GetMapping("/getBySalon/{salonId}")
    public ResponseEntity<Set<CategoryDTO>> getCategoriesBySalon(
            @PathVariable Long salonId
    ){
        Set<CategoryDTO> categories = categoryService.getAllCategoriesBySalonId(salonId);
        return ResponseEntity.ok(categories);
    }
    @GetMapping("/getCategoryById/{categoryId}")
    public ResponseEntity<CategoryDTO> getCategoryById(
            @PathVariable Long categoryId
    ) throws Exception {
        CategoryDTO categoryDTO = categoryService.getCategoryById(categoryId);
        return ResponseEntity.ok(categoryDTO);
    }




}
