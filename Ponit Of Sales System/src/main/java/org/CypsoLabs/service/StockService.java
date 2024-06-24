package org.CypsoLabs.service;

import org.CypsoLabs.dto.StockDto;
import org.CypsoLabs.entity.Stock;

import java.util.List;

public interface StockService {
    Boolean addStock(StockDto stockDto);
    StockDto updateStock(Long id, StockDto stockDto);
    Boolean deleteStock(Long id);
    List<StockDto> listStock(Long id);
    StockDto getStockById(Long id);






}
