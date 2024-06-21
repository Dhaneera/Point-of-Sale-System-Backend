package org.CypsoLabs.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.CypsoLabs.config.Resource.ResourceNotFoundException;
import org.CypsoLabs.dto.OrdersDto;
import org.CypsoLabs.entity.Orders;
import org.CypsoLabs.repository.OrdersRepository;
import org.CypsoLabs.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    OrdersRepository ordersRepository;

    @Override
    public boolean addOrder(OrdersDto orderDto) {
        Orders orders = objectMapper.convertValue(orderDto, Orders.class);
        Orders save = ordersRepository.save(orders);
        return save.getId()!=null;
    }

    @Override
    public OrdersDto getOrderById(Long id) {
        Orders orders = ordersRepository.findById(id).get();
        if (orders.getId()!=null){
            return objectMapper.convertValue(orders,OrdersDto.class);
        }
        return null;
    }

    @Override
    public OrdersDto updateOrders(Long id, OrdersDto ordersDto) {
        Optional<Orders> ordersOptional = ordersRepository.findById(id);
        if (ordersOptional.isPresent()){
            Orders orders = ordersOptional.get();
            orders.setAddress(orders.getAddress());
            orders.setPhone(orders.getAddress());
            orders.setTotal(orders.getTotal());

            Orders updateOrders = ordersRepository.save(orders);
            return objectMapper.convertValue(updateOrders,OrdersDto.class);
        }
        return null;
    }

    @Override
    public boolean deleteOrders(Long id) {
        if (ordersRepository.existsById(id)){
            ordersRepository.findById(id);
            return true;
        }
        else {
            throw new ResourceNotFoundException("Orders Info not available for this id to delete "+id);
        }
    }

    @Override
    public List<OrdersDto> getAllOrderDetails() {
        Iterable<Orders> iterableOrders=ordersRepository.findAll();
        Iterator<Orders> iteratorOrders =iterableOrders.iterator();
        List<OrdersDto> list = new ArrayList<>();
        while (iteratorOrders.hasNext()){
            Orders orders=iteratorOrders.next();
            OrdersDto ordersDto=objectMapper.convertValue(orders,OrdersDto.class);
            list.add(ordersDto);
        }
        return list;
    }
}
