package com.ucc.orders.controller;

import com.ucc.orders.model.dto.CustomerDTO;
import com.ucc.orders.model.dto.CustomerResponseDTO;
import com.ucc.orders.model.entities.Customer;
import com.ucc.orders.model.mappers.CustomerShippingMapper;
import com.ucc.orders.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerShippingMapper mapper;

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customerEntity = mapper.customerDTOToCustomer(customerDTO);
        Customer createdCustomer = customerService.createCustomer(customerEntity);
        CustomerResponseDTO responseDTO = mapper.customerToCustomerResponseDTO(createdCustomer);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        CustomerResponseDTO responseDTO = mapper.customerToCustomerResponseDTO(customer);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        Customer customerEntity = mapper.customerDTOToCustomer(customerDTO);
        Customer updatedCustomer = customerService.updateCustomer(id, customerEntity);
        CustomerResponseDTO responseDTO = mapper.customerToCustomerResponseDTO(updatedCustomer);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomerById(id);  // Cambié el método para que coincida con el servicio
        return ResponseEntity.noContent().build();
    }
}