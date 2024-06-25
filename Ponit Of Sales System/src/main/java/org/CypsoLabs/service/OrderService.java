package org.CypsoLabs.service;

import org.CypsoLabs.dto.OrdersDto;
import java.util.List;

public interface OrderService {
    boolean addOrder(OrdersDto orderDto);
    OrdersDto getOrderById(Long id); // Add this method to get order details by ID
    List<OrdersDto> getAllOrders(); // Optionally, add a method to get all orders
}
