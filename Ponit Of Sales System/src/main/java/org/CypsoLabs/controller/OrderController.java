package org.CypsoLabs.controller;

import jakarta.validation.Valid;
import org.CypsoLabs.dto.CategoryDto;
import org.CypsoLabs.dto.OrdersDto;
import org.CypsoLabs.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("/orders/api")
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;
    @PostMapping("/add")
    public ResponseEntity<String>addOrders(@Valid @RequestBody OrdersDto ordersDto){
        boolean saved = orderService.addOrder(ordersDto);
        if (saved)return ResponseEntity.status(HttpStatus.CREATED).body("Order add successfully");
        else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add Order");
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<OrdersDto>> getAllOrders() {
        List<OrdersDto> orderDetails = orderService.getAllOrderDetails();
        return ResponseEntity.ok(orderDetails);
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<OrdersDto>getOrdersById(@PathVariable Long id){
        OrdersDto orderDto = orderService.getOrderById(id);
        if (orderDto.getId()==null)return ResponseEntity.notFound().build();
        return ResponseEntity.ok(orderDto);
    }
    @DeleteMapping("/remove/id")
    public ResponseEntity<String>removeOrderById(@PathVariable Long id){
        boolean  removed = orderService.deleteOrders(id);
        if (removed)return ResponseEntity.ok("Order Successfully removed");
        else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while removing Order ");
    }
    @PutMapping("/update/id")
    public ResponseEntity<OrdersDto>updateOrdersById(@PathVariable Long id,@RequestBody OrdersDto ordersDto){
        OrdersDto updateDto = orderService.updateOrders(id, ordersDto);
        if (updateDto!=null)return ResponseEntity.ok(updateDto);
        else return ResponseEntity.notFound().build();
    }
}
