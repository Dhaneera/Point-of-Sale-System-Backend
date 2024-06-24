package org.CypsoLabs.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.CypsoLabs.dto.CategoryDto;
import org.CypsoLabs.entity.Category;
import org.CypsoLabs.repository.CategoryRepository;
import org.CypsoLabs.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public boolean saveCategory(CategoryDto categoryDto) {
        Category category= objectMapper.convertValue(categoryDto, Category.class);
        Category save = categoryRepository.save(category);
        return save.getId() != null;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        Iterable<Category>  iterable = categoryRepository.findAll();
        Iterator<Category> iterator = iterable.iterator();
        List<CategoryDto>  categoryDtoList =new ArrayList<>();
        while (iterator.hasNext()){
            Category category = iterator.next();
            CategoryDto categoryDto = objectMapper.convertValue(category, CategoryDto.class);
            categoryDtoList.add(categoryDto);
        }
        return categoryDtoList;
    }

    @Override
    public CategoryDto getCategoryByName(String name) {
        try{
            Category category = categoryRepository.getByName(name);
            return objectMapper.convertValue(category,CategoryDto.class);
        }catch (Exception exception){
            return CategoryDto.builder().id(null).name(null).build();
        }

    }
    @Override
    public boolean deleteCategoryByName(String name) {
        CategoryDto categoryDto = getCategoryByName(name);
        categoryRepository.deleteById(categoryDto.getId());
        return true;
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        try {
            Category category = categoryRepository.findById(id).orElse(null);
            if (category != null) {
                log.debug("Category found: " + category);
                return objectMapper.convertValue(category, CategoryDto.class);
            }else{
                log.warn("No category found with id: " + id);
            }
        }catch (Exception e) {
            log.error("Error fetching category with name: " + id, e);
        }
        return null;
    }
    @Override
    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()){
            Category category = optionalCategory.get();
            category.setName(categoryDto.getName());

            Category updateCategory = categoryRepository.save(category);
            return objectMapper.convertValue(updateCategory,CategoryDto.class);
        }
        return  null;
    }
}
