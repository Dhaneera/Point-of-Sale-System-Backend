package org.CypsoLabs.service;

import org.CypsoLabs.dto.CategoryDto;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface CategoryService{
    boolean saveCategory(CategoryDto categoryDto);
    List<CategoryDto>getAllCategories();
    CategoryDto getCategoryByName(String name);
    boolean deleteCategoryByName(String name);

}
