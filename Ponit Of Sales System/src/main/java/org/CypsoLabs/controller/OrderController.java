package org.CypsoLabs.controller;

import org.CypsoLabs.dto.OrdersDto;
import org.CypsoLabs.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/orders/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/add")
    public ResponseEntity<String> addOrder(@Valid @RequestBody OrdersDto orderDto) {

        boolean isAdded = orderService.addOrder(orderDto);
        if (isAdded) {
            return new ResponseEntity<>("Order added successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Order could not be added", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<OrdersDto> getOrderById(@PathVariable Long id) {
        OrdersDto orderDto = orderService.getOrderById(id);
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<OrdersDto>> getAllOrders() {
        List<OrdersDto> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
