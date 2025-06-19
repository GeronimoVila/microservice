package com.ucc.orders.model.mappers;

import com.ucc.orders.model.dto.ShippingDetailsDTO;
import com.ucc.orders.model.entities.ShippingDetails;
import org.springframework.stereotype.Component;

@Component
public class ShippingDetailsMapper {

    public ShippingDetails dtoToEntity(ShippingDetailsDTO dto) {
        if (dto == null) {
            return null;
        }

        ShippingDetails entity = new ShippingDetails();
        entity.setStreetAddress(dto.getStreetAddress());
        entity.setCity(dto.getCity());
        entity.setZipCode(dto.getZipCode());
        entity.setState(dto.getState());
        entity.setCountry(dto.getCountry());
        entity.setShippingMethod(dto.getShippingMethod());
        entity.setShippingCost(dto.getShippingCost());

        return entity;
    }

    public ShippingDetailsDTO entityToDto(ShippingDetails entity) {
        if (entity == null) {
            return null;
        }

        ShippingDetailsDTO dto = new ShippingDetailsDTO();
        dto.setStreetAddress(entity.getStreetAddress());
        dto.setCity(entity.getCity());
        dto.setZipCode(entity.getZipCode());
        dto.setState(entity.getState());
        dto.setCountry(entity.getCountry());
        dto.setShippingMethod(entity.getShippingMethod());
        dto.setShippingCost(entity.getShippingCost());

        return dto;
    }
}
