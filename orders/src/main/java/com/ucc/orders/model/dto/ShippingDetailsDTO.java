package com.ucc.orders.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShippingDetailsDTO implements Serializable {

    @NotBlank(message = "La dirección no puede estar vacía")
    @Size(min = 5, max = 255, message = "La dirección debe tener entre 5 y 255 caracteres")
    private String streetAddress;

    @NotBlank(message = "La ciudad no puede estar vacía")
    @Size(min = 2, max = 100, message = "La ciudad debe tener entre 2 y 100 caracteres")
    private String city;

    @NotBlank(message = "El código postal no puede estar vacío")
    @Size(min = 3, max = 20, message = "El código postal debe tener entre 3 y 20 caracteres")
    private String zipCode;

    @NotBlank(message = "La provincia/estado no puede estar vacía")
    @Size(min = 2, max = 100, message = "La provincia/estado debe tener entre 2 y 100 caracteres")
    private String state;

    @NotBlank(message = "El país no puede estar vacío")
    @Size(min = 2, max = 100, message = "El país debe tener entre 2 y 100 caracteres")
    private String country;

    @Size(max = 50, message = "El método de envío no debe superar los 50 caracteres")
    private String shippingMethod;

    private Double shippingCost;
}