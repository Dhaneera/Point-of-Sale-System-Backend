package org.CypsoLabs.service;

import org.CypsoLabs.dto.CategoryDto;

import java.util.List;


public interface CategoryService{
    boolean saveCategory(CategoryDto categoryDto);
    List<CategoryDto>getAllCategories();
    CategoryDto getCategoryByName(String name);
    boolean deleteCategoryByName(String name);
    CategoryDto getCategoryById(Long id);

    CategoryDto updateCategory(Long id, CategoryDto categoryDto);

}
