package com.ucc.orders.exceptions.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMensajeDTO {
    private Integer code;
    private String message;
    private String path;
}