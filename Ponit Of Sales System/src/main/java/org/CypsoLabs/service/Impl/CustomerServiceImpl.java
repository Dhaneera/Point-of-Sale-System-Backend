package org.CypsoLabs.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.CypsoLabs.config.Resource.ResourceNotFoundException;
import org.CypsoLabs.dto.CustomerDto;
import org.CypsoLabs.entity.Customer;
import org.CypsoLabs.repository.CustomerRepository;
import org.CypsoLabs.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public boolean addCustomer(CustomerDto customerDto) {
        Customer customer = mapper.convertValue(customerDto, Customer.class);
        Customer save = customerRepository.save(customer);
        return save.getId()!=null;
    }

    @Override
    public CustomerDto getCustomerByName(String name) {
        try{
            Customer customer = customerRepository.getByName(name);
            return mapper.convertValue(customer, CustomerDto.class);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public CustomerDto updateCustomer(Long id, CustomerDto customerDto) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            customer.setName(customerDto.getName());
            customer.setMail(customerDto.getMail());
            customer.setAddress(customerDto.getAddress());
            customer.setPhone(customerDto.getPhone());

            Customer updateCustomer = customerRepository.save(customer);
            return mapper.convertValue(updateCustomer, CustomerDto.class);
        }

        return null;
    }

    @Override
    public CustomerDto getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).get();
        if (customer.getId()!=null){
            return mapper.convertValue(customer, CustomerDto.class);
        }
        return null;
    }

    @Override
    public boolean deleteCustomerById(Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return true;
        }else{
            throw new ResourceNotFoundException("Customer info not available for this id to delete: "+id);
        }

    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        Iterable<Customer> iterableCustomers=customerRepository.findAll();
        Iterator<Customer> iteratorCustomers =iterableCustomers.iterator();
        List<CustomerDto> list = new ArrayList<>();
        while (iteratorCustomers.hasNext()){
            Customer customer=iteratorCustomers.next();
            CustomerDto customerDto=mapper.convertValue(customer,CustomerDto.class);
            list.add(customerDto);
        }
        return list;
    }

}
