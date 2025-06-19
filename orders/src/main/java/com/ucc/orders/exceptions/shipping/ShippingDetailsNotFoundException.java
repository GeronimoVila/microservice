package com.ucc.orders.exceptions.shipping;

public class ShippingDetailsNotFoundException extends RuntimeException {
    public ShippingDetailsNotFoundException(String message) {
        super(message);
    }
}