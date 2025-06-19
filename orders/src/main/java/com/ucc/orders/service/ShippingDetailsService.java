package com.ucc.orders.service;

import com.ucc.orders.exceptions.shipping.ShippingDetailsNotFoundException;
import com.ucc.orders.model.entities.ShippingDetails;
import com.ucc.orders.repository.ShippingDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShippingDetailsService {

    private final ShippingDetailsRepository shippingDetailsRepository;

    public List<ShippingDetails> getAllShippingDetails() {
        return shippingDetailsRepository.findAll();
    }

    public ShippingDetails getShippingDetailsById(Long id) {
        return shippingDetailsRepository.findById(id)
                .orElseThrow(() -> new ShippingDetailsNotFoundException("No se encontraron los detalles de envío con ID: " + id));
    }

    public ShippingDetails createShippingDetails(ShippingDetails shippingDetails) {
        return shippingDetailsRepository.save(shippingDetails);
    }

    public ShippingDetails updateShippingDetails(Long id, ShippingDetails updatedShippingDetails) {
        ShippingDetails existingDetails = shippingDetailsRepository.findById(id)
                .orElseThrow(() -> new ShippingDetailsNotFoundException("No se encontraron los detalles de envío con ID para actualizar: " + id));

        existingDetails.setStreetAddress(updatedShippingDetails.getStreetAddress());
        existingDetails.setCity(updatedShippingDetails.getCity());
        existingDetails.setZipCode(updatedShippingDetails.getZipCode());
        existingDetails.setState(updatedShippingDetails.getState());

        return shippingDetailsRepository.save(existingDetails);
    }

    public void deleteShippingDetailsById(Long id) {
        if (!shippingDetailsRepository.existsById(id)) {
            throw new ShippingDetailsNotFoundException("No se encontraron los detalles de envío con ID para eliminar: " + id);
        }
        shippingDetailsRepository.deleteById(id);
    }
}
