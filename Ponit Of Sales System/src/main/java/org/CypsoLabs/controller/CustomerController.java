package org.CypsoLabs.controller;

import jakarta.validation.Valid;
import org.CypsoLabs.dto.CustomerDto;
import org.CypsoLabs.dto.ProductDto;
import org.CypsoLabs.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/customer/api")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity<String> addCustomer(@Valid @RequestBody CustomerDto customerDto){
        boolean saved = customerService.addCustomer(customerDto);
        if (saved) return ResponseEntity.status(HttpStatus.CREATED).body("Customer add successfully");
        else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add Customer");
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<CustomerDto>getCustomerById(@PathVariable Long id){
        CustomerDto customerDto = customerService.getCustomerById(id);
        if (customerDto !=null)return ResponseEntity.ok(customerDto);
        else return ResponseEntity.notFound().build();
    }
    @GetMapping("/getByName/{name}")
    public ResponseEntity<CustomerDto>getCustomerByName(@PathVariable String name){
        CustomerDto customerDto = customerService.getCustomerByName(name);
        if (customerDto ==null)return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(customerDto);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<CustomerDto>updateCustomerById(@PathVariable Long id, @RequestBody CustomerDto customerDto){
        CustomerDto  updateCustomerDto = customerService.updateCustomer(id, customerDto);
        if (updateCustomerDto!=null)return ResponseEntity.ok(updateCustomerDto);
        else return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String>removeCustomerById(@PathVariable Long id){
        boolean removed = customerService.deleteCustomerById(id);
        if(removed)return ResponseEntity.ok("Customer successfully removed");
        else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error occurred while removing Customer");
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<CustomerDto>> getAllCategories() {
        List<CustomerDto> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

}
