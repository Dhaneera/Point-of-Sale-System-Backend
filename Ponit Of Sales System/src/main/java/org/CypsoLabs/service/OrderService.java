package org.CypsoLabs.service;

import org.CypsoLabs.dto.OrdersDto;
import java.util.List;

public interface OrderService {
    boolean addOrder(OrdersDto orderDto);
    OrdersDto getOrderById(Long id);
    List<OrdersDto> getAllOrders();
}
