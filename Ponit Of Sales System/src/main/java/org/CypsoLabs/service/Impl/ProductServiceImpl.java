package org.CypsoLabs.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.CypsoLabs.config.ResourceNotFoundException;
import org.CypsoLabs.dto.CategoryDto;
import org.CypsoLabs.dto.ProductDto;
import org.CypsoLabs.entity.Category;
import org.CypsoLabs.entity.Product;
import org.CypsoLabs.repository.ProductRepository;
import org.CypsoLabs.service.CategoryService;
import org.CypsoLabs.service.ProductService;
import org.CypsoLabs.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class ProductServiceImpl  implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService category;


    @Autowired
    ObjectMapper objectMapper;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Override
    public Boolean addProduct(ProductDto productDto) {
        CategoryDto categoryDto = category.getCategoryById(productDto.getCategory().getId());
        System.out.println(categoryDto);
        if (categoryDto.getId() == null) {
            log.warn("Category not found or ID is null for product: " + productDto);
            return false;
        }
        Long id =categoryDto.getId();
        log.debug("Category found for product: " + categoryDto);

        Product product = Product.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .desc(productDto.getDesc())
                .category(Category.builder()
                        .id(id).name(categoryDto.getName())
                        .build())
                .build();
        Product save = productRepository.save(product);
        return  save.getId()!=null;
    }

    @Override
    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id).get();
        if (product.getId()!=null){
            ProductDto productDto = objectMapper.convertValue(product, ProductDto.class);
            productDto.
                    setCategory(Category.builder()
                            .id(product.getCategory().getId())
                            .build());
            return productDto;
        }
        return null;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        Iterable<Product> iterableProducts=productRepository.findAll();
        Iterator<Product> iteratorProducts =iterableProducts.iterator();
        List<ProductDto> list = new ArrayList<>();
        while (iteratorProducts.hasNext()){
            Product product=iteratorProducts.next();
            ProductDto productDto=objectMapper.convertValue(product,ProductDto.class);
            productDto.setCategory
                    (Category.builder()
                            .id(product.getCategory().getId())
                            .name(product.getCategory().getName()).build());
            list.add(productDto);
        }
        return list;
    }


    @Override
    public List<ProductDto> getProductByCategory(String categoryName) {
        List<ProductDto> listOfALlProducts=getAllProducts();
        List<ProductDto> listOfSpecificProducts = new ArrayList<>();
        for (ProductDto productDto:listOfALlProducts){
            if (Objects.equals(productDto.getCategory().getName(), categoryName)){
                listOfSpecificProducts.add(productDto);
            }
        }
        return  listOfSpecificProducts;
    }

    @Override
    public ProductDto updateProductById(Long id, ProductDto productDto) {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();

            // Update fields from productDto
            product.setName(productDto.getName());
            product.setDesc(productDto.getDesc());
            product.setPrice(productDto.getPrice());

            // Update category if provided in productDto
            if (productDto.getCategory() != null) {
                 Category category1= productDto.getCategory();

                // Convert CategoryDto to Category entity if necessary
                CategoryDto categoryDto = objectMapper.convertValue(category1, CategoryDto.class);

                // Set the converted category entity
                product.setCategory(category1);
            } else {
                // Handle case where productDto has null category (optional)
                product.setCategory(null); // Or handle default category logic if needed
            }

            // Save the updated product
            Product updatedProduct = productRepository.save(product);

            // Convert updated product to ProductDto

            return objectMapper.convertValue(updatedProduct, ProductDto.class);
        }

        return null; // Handle case where product with given ID is not found
    }

    @Override
    public Boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }else {
            throw new ResourceNotFoundException("product info not available for this id to delete: "+id);
        }

    }

    @Override
    public List<ProductDto> getProductByStock(Long StockId) {
        return null;
    }
}
