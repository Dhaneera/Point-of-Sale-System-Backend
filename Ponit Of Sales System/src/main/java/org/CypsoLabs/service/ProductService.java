package org.CypsoLabs.service;

import org.CypsoLabs.dto.ProductDto;

import java.util.List;

public interface ProductService {
    Boolean addProduct(ProductDto productDto);
    ProductDto getProductById(Long id);
    List<ProductDto> getAllProducts();
    List<ProductDto> getProductByCategory(String categoryName);

    ProductDto getProductByName(String name);

    ProductDto updateProductById(Long id , ProductDto productDto);

    Boolean deleteProduct(Long id);

    List<ProductDto> getProductByStock(Long StockId);


}
