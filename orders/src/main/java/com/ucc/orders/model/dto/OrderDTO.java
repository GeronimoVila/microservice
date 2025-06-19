package com.ucc.orders.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO implements Serializable {
    private Long customerId;
    private ShippingDetailsDTO shippingDetails;
    private List<OrderItemDTO> orderItems;
}
