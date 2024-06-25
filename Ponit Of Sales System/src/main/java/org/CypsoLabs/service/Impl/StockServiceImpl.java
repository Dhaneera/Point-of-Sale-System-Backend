package org.CypsoLabs.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.CypsoLabs.dto.ProductDto;
import org.CypsoLabs.dto.StockDto;
import org.CypsoLabs.entity.Product;
import org.CypsoLabs.entity.Stock;
import org.CypsoLabs.repository.StockRepository;
import org.CypsoLabs.service.ProductService;
import org.CypsoLabs.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class StockServiceImpl implements StockService {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    StockRepository stockRepository;

    @Autowired
    ProductService productService;

    @Override
    public Boolean addStock(StockDto stockDto) {
        ProductDto productDto = productService.getProductById(stockDto.getProduct().getId());
        Stock stock = objectMapper.convertValue(stockDto, Stock.class);
        stock.setProduct(Product.builder().id(productDto.getId()).name(productDto.getName()).build());
        Stock save = stockRepository.save(stock);
        return save.getId()!=null;
    }


    @Override
    public StockDto updateStock(Long id, StockDto stockDto) {
        Optional<Stock> stockOptional = stockRepository.findById(id);
        if (stockOptional.isPresent()){
            Stock stock = stockOptional.get();
            stock.setQty(stockDto.getQty());
            stock.setPrice(stockDto.getPrice());
            if (stockDto.getProduct()!=null){
               Product product1= stockDto.getProduct();
               ProductDto productDto=objectMapper.convertValue(product1,ProductDto.class);
               stock.setProduct(product1);
            }else {
                stock.setProduct(null);
            }
            Stock updateStock = stockRepository.save(stock);
            return objectMapper.convertValue(updateStock,StockDto.class);
        }
        return null;
    }

    @Override
    public Boolean deleteStock(Long id) {
        if (stockRepository.existsById(id)){
            stockRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<StockDto> listStock(Long id) {
        Optional<Stock> stockOptional = stockRepository.findById(id);
        if (stockOptional.isPresent()) {
            Stock stock = stockOptional.get();
            Long productId=stock.getProduct().getId();
            ProductDto productDto=productService.getProductById(productId);
            StockDto convertedStock=convertStockToDTO(stock);
            convertedStock.setProduct(Product.builder().id(productDto.getId()).name(productDto.getName()).build());
            return Collections.singletonList(convertedStock);
        } else {

            return Collections.emptyList();
        }
    }
    private StockDto convertStockToDTO(Stock stock) {
        StockDto stockDTO = new StockDto();
        stockDTO.setId(stock.getId());
        stockDTO.setPrice(stock.getPrice());
        stockDTO.setQty(stock.getQty());

        if (stock.getId()!=null){
            Product product = stock.getProduct();
            ProductDto productDto =new ProductDto();
            productDto.setId(product.getId());
            productDto.setName(product.getName());
            productDto.setPrice(product.getPrice());
            productDto.setDesc(product.getDesc());
            productDto.setCategory(product.getCategory());
        }
        return stockDTO;

    }

    @Override
    public StockDto getStockById(Long id) {
        Stock stock = stockRepository.findById(id).get();
        if (stock.getId()!=null){
            StockDto stockDto=objectMapper.convertValue(stock,StockDto.class);
            stockDto.setProduct(
                    Product.builder()
                            .id(stock.getProduct().getId())
                            .name(stock.getProduct().getName())
                            .price(stock.getProduct().getPrice())
                            .desc(stock.getProduct().getDesc())
                            .stocks(stock.getProduct().getStocks())
                            .build());
            return stockDto;
        }
        return null;
    }
}
