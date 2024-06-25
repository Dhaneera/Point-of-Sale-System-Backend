package org.CypsoLabs.service.Impl;

import org.CypsoLabs.dto.CustomerDto;
import org.CypsoLabs.dto.OrdersDto;
import org.CypsoLabs.entity.Customer;
import org.CypsoLabs.entity.Orders;
import org.CypsoLabs.repository.CustomerRepository;
import org.CypsoLabs.repository.OrdersRepository;
import org.CypsoLabs.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public boolean addOrder(OrdersDto orderDto) {


        Orders order = Orders.builder()
                .address(orderDto.getAddress())
                .phone(orderDto.getPhone())
                .total(orderDto.getTot().longValue())
                .build();

        orderRepository.save(order);
        return true;
    }

    @Override
    public OrdersDto getOrderById(Long id) {
        Orders order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));


        return OrdersDto.builder()
                .id(order.getId())
                .address(order.getAddress())
                .phone(order.getPhone())
                .tot(order.getTotal().doubleValue())
                .build();
    }

    @Override
    public List<OrdersDto> getAllOrders() {
        return orderRepository.findAll().stream().map(order -> {

            return OrdersDto.builder()
                    .id(order.getId())
                    .address(order.getAddress())
                    .phone(order.getPhone())
                    .tot(order.getTotal().doubleValue())
                    .build();
        }).collect(Collectors.toList());
    }
}
