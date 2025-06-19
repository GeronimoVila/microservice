package com.ucc.orders.model.mappers;

import com.ucc.orders.model.dto.OrderDTO;
import com.ucc.orders.model.dto.OrderResponseDTO;
import com.ucc.orders.model.entities.Order;
import com.ucc.orders.model.entities.Customer;
import com.ucc.orders.model.entities.ShippingDetails;
import com.ucc.orders.model.enums.OrderStatus;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderMapper {
    private final OrderItemMapper orderItemMapper;
    private final ShippingDetailsMapper shippingDetailsMapper;

    public OrderMapper(OrderItemMapper orderItemMapper, ShippingDetailsMapper shippingDetailsMapper) {
        this.orderItemMapper = orderItemMapper;
        this.shippingDetailsMapper = shippingDetailsMapper;
    }

    public Order orderDTOToOrder(OrderDTO orderDTO, Customer customer) {
        if (orderDTO == null) {
            return null;
        }

        Order order = new Order();
        order.setOrderDate(java.time.LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);

        // Asignar cliente
        order.setCustomer(customer);

        // Mapear los OrderItems y asignar la orden en cada uno
        order.setOrderItems(
                orderDTO.getOrderItems().stream()
                        .map(dto -> {
                            var item = orderItemMapper.orderItemDTOToOrderItem(dto);
                            item.setOrder(order);
                            return item;
                        })
                        .collect(Collectors.toList())
        );

        // Mapear ShippingDetails si viene
        if (orderDTO.getShippingDetails() != null) {
            ShippingDetails shippingDetails = shippingDetailsMapper.dtoToEntity(orderDTO.getShippingDetails());
            shippingDetails.setOrder(order);
            order.setShippingDetails(shippingDetails);
        }

        return order;
    }

    public OrderResponseDTO orderToOrderResponseDTO(Order order) {
        if (order == null) {
            return null;
        }

        return new OrderResponseDTO(
                order.getId(),
                order.getOrderDate(),
                order.getStatus(),
                order.getTotalAmount(),
                order.getOrderItems().stream()
                        .map(orderItemMapper::orderItemToOrderItemResponseDTO)
                        .collect(Collectors.toList())
        );
    }
}
