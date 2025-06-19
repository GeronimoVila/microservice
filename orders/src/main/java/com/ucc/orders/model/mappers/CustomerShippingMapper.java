package com.ucc.orders.model.mappers;

import com.ucc.orders.model.dto.CustomerDTO;
import com.ucc.orders.model.dto.CustomerResponseDTO;
import com.ucc.orders.model.dto.ShippingDetailsDTO;
import com.ucc.orders.model.entities.Customer;
import com.ucc.orders.model.entities.ShippingDetails;
import org.springframework.stereotype.Component;

@Component
public class CustomerShippingMapper {


    public Customer customerDTOToCustomer(CustomerDTO customerDTO) {
        if (customerDTO == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhone(customerDTO.getPhone());
        return customer;
    }

    public CustomerResponseDTO customerToCustomerResponseDTO(Customer customer) {
        if (customer == null) {
            return null;
        }
        return new CustomerResponseDTO(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhone()
        );
    }


    public ShippingDetails shippingDetailsDTOToShippingDetails(ShippingDetailsDTO shippingDetailsDTO) {
        if (shippingDetailsDTO == null) {
            return null;
        }
        ShippingDetails shippingDetails = new ShippingDetails();
        shippingDetails.setStreetAddress(shippingDetailsDTO.getStreetAddress());
        shippingDetails.setCity(shippingDetailsDTO.getCity());
        shippingDetails.setZipCode(shippingDetailsDTO.getZipCode());
        shippingDetails.setState(shippingDetailsDTO.getState());
        return shippingDetails;
    }

    public ShippingDetailsDTO shippingDetailsToShippingDetailsDTO(ShippingDetails shippingDetails) {
        if (shippingDetails == null) {
            return null;
        }

        ShippingDetailsDTO dto = new ShippingDetailsDTO();
        dto.setStreetAddress(shippingDetails.getStreetAddress());
        dto.setCity(shippingDetails.getCity());
        dto.setZipCode(shippingDetails.getZipCode());
        dto.setState(shippingDetails.getState());
        dto.setCountry(shippingDetails.getCountry());
        dto.setShippingMethod(shippingDetails.getShippingMethod());
        dto.setShippingCost(shippingDetails.getShippingCost());
        return dto;
    }

}