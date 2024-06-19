package org.CypsoLabs.controller;

import jakarta.validation.Valid;
import org.CypsoLabs.dto.CategoryDto;
import org.CypsoLabs.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/category/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<String> addCategory(@Valid @RequestBody CategoryDto categoryDto){

        boolean isSaved= categoryService.saveCategory(categoryDto);
        if (isSaved){
            return ResponseEntity.status(HttpStatus.CREATED).body("Category created successfully.");
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create category.");
        }
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }
    @GetMapping("/getByName/{name}")
    public  ResponseEntity<CategoryDto> getCategoryByName(@PathVariable String name){
        CategoryDto categoryDto = categoryService.getCategoryByName(name);
        if (categoryDto !=null) {
            return ResponseEntity.ok(categoryDto);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    } @GetMapping("/getById/{id}")
    public  ResponseEntity<CategoryDto> getCategoryById(@PathVariable long id){
        CategoryDto categoryDto = categoryService.getCategoryById(id);
        if (categoryDto.getId() == null)return ResponseEntity.notFound().build();
        return ResponseEntity.ok(categoryDto);
    }
    @DeleteMapping("/delete/{name}")
    public ResponseEntity<String>deleteCategoryByName(@PathVariable String name){
        boolean deleted = categoryService.deleteCategoryByName(name);
        if (deleted)return ResponseEntity.ok("Category deleted successfully");
        else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete category");
    }

}
