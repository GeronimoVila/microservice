package com.ucc.orders.controller;

import com.ucc.orders.model.dto.ShippingDetailsDTO;
import com.ucc.orders.model.entities.ShippingDetails;
import com.ucc.orders.model.mappers.ShippingDetailsMapper;
import com.ucc.orders.service.ShippingDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shipping-details")
@RequiredArgsConstructor
public class ShippingDetailsController {

    private final ShippingDetailsService shippingDetailsService;
    private final ShippingDetailsMapper shippingDetailsMapper;

    @PostMapping
    public ResponseEntity<ShippingDetailsDTO> create(@RequestBody ShippingDetailsDTO dto) {
        ShippingDetails entity = shippingDetailsMapper.dtoToEntity(dto);
        ShippingDetails saved = shippingDetailsService.createShippingDetails(entity);
        ShippingDetailsDTO responseDto = shippingDetailsMapper.entityToDto(saved);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShippingDetailsDTO> getShippingDetailsById(@PathVariable Long id) {
        ShippingDetails entity = shippingDetailsService.getShippingDetailsById(id);
        ShippingDetailsDTO dto = shippingDetailsMapper.entityToDto(entity);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShippingDetailsDTO> updateShippingDetails(@PathVariable Long id, @RequestBody ShippingDetailsDTO dto) {
        ShippingDetails entity = shippingDetailsMapper.dtoToEntity(dto);
        ShippingDetails updated = shippingDetailsService.updateShippingDetails(id, entity);
        ShippingDetailsDTO responseDto = shippingDetailsMapper.entityToDto(updated);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShippingDetails(@PathVariable Long id) {
        shippingDetailsService.deleteShippingDetailsById(id);
        return ResponseEntity.noContent().build();
    }
}
