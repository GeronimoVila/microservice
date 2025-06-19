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
public class CustomerResponseDTO implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
