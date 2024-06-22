package org.CypsoLabs.controller;

import jakarta.validation.Valid;
import org.CypsoLabs.dto.ProductDto;
import org.CypsoLabs.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/product/api")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<String>addProduct(@Valid @RequestBody ProductDto productDto){
        Boolean saved = productService.addProduct(productDto);
        if (saved)return ResponseEntity.status(HttpStatus.CREATED).body("Product added successfully");
        else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add Product");
    }
    @GetMapping("/getById/{id}")
    public  ResponseEntity<ProductDto>getProductById(@PathVariable long id){
        ProductDto productDto = productService.getProductById(id);
        if (productDto.getId()==null)return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productDto);

    }
    @GetMapping("/getAll")
    public ResponseEntity<List<ProductDto>> getAllCategories() {
        List<ProductDto> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/getProductByName/{name}")
    public  ResponseEntity<ProductDto>getProductByName(String name){
        ProductDto productDto = productService.getProductByName(name);
        if (productDto!=null)return ResponseEntity.ok(productDto);
        else return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDto>updateProduct(@PathVariable Long id,@RequestBody ProductDto productDto){
        ProductDto updateProductDto = productService.updateProductById(id, productDto);
        if (updateProductDto!=null)return ResponseEntity.ok(updateProductDto);
        else return ResponseEntity.notFound().build();

    }
    @GetMapping("/getByCategory/{categoryName}")
    public ResponseEntity<List<ProductDto>> getProductByCategoryName(@PathVariable String categoryName){
        List<ProductDto> productByCategory = productService.getProductByCategory(categoryName);
        if (productByCategory.isEmpty())return ResponseEntity.noContent().build();
        else return ResponseEntity.ok().body(productByCategory);

    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String>deleteProduct(@PathVariable Long id){
        Boolean deleted = productService.deleteProduct(id);
        if (deleted)return ResponseEntity.ok("Product deleted");
        else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error occurred when delete Product");
    }
}
