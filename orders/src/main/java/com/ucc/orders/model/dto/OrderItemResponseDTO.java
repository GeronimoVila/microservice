package com.ucc.orders.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResponseDTO implements Serializable {
    private Long productId;
    private Integer quantity;
    private Double unitPrice;
    private Double subtotal;
}