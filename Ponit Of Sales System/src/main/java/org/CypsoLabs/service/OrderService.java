package org.CypsoLabs.service;

import org.CypsoLabs.dto.OrdersDto;

import java.util.List;

public interface OrderService {
    boolean addOrder(OrdersDto orderDto);
    OrdersDto getOrderById(Long id);
    OrdersDto updateOrders(Long id, OrdersDto ordersDto);
    boolean deleteOrders(Long id);

    List<OrdersDto> getAllOrderDetails();




}
