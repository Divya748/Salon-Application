package com.eager2code.mapper;

import com.eager2code.model.Category;
import com.eager2code.pojo.CategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface CategoryMapper {

    CategoryMapper MAPPER = Mappers.getMapper(CategoryMapper.class);

    Category mapToCategory(CategoryDTO categoryDTO);

    CategoryDTO mapToDto(Category category);

    Set<CategoryDTO> mapToSet(Set<Category> categorySet);
}
