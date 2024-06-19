package org.CypsoLabs.controller;

import jakarta.validation.Valid;
import org.CypsoLabs.dto.ProductDto;
import org.CypsoLabs.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/product/api")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<String>addProduct(@Valid @RequestBody ProductDto productDto){
        Boolean saved = productService.addProduct(productDto);
        if (saved)return ResponseEntity.status(HttpStatus.CREATED).body("Product added successfully");
        else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add Product");
    }
    @GetMapping("/getById/{id}")
    public  ResponseEntity<ProductDto> getProductById(@PathVariable long id){
        ProductDto productDto = productService.getProductById(id);
        if (productDto.getId()==null)return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productDto);

    }
    @GetMapping("/getAll")
    public ResponseEntity<List<ProductDto>> getAllCategories() {
        List<ProductDto> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDto>updateProduct(@PathVariable Long id,@RequestBody ProductDto productDto){
        ProductDto updateProductDto = productService.updateProductById(id, productDto);
        if (updateProductDto!=null)return ResponseEntity.ok(updateProductDto);
        else return ResponseEntity.notFound().build();

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String>deleteProduct(@PathVariable Long id){
        Boolean deleted = productService.deleteProduct(id);
        if (deleted)return ResponseEntity.ok("Product deleted");
        else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error occurred when delete Product");
    }
}
