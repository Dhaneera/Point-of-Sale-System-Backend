package org.CypsoLabs.service;

import org.CypsoLabs.dto.CustomerDto;
import org.CypsoLabs.dto.ProductDto;

import java.util.List;

public interface CustomerService {
    boolean addCustomer(CustomerDto customerDto);
    CustomerDto getCustomerByName(String name);

    CustomerDto updateCustomer(Long id, CustomerDto customerDto);

    CustomerDto getCustomerById(Long id);

    boolean  deleteCustomerById(Long id);
    List<CustomerDto> getAllCustomers();



}
