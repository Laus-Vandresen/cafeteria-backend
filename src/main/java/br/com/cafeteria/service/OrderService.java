package br.com.cafeteria.service;

import br.com.cafeteria.dto.OrderDto;
import br.com.cafeteria.entity.OrderEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface OrderService {

    Long createNewOrder();

    Optional<OrderEntity> findById(Long id);

    OrderDto getTotalPrice(Long orderId);

    OrderDto closeOrder(Long orderId, BigDecimal paidValue, BigDecimal totalPrice);

    List<OrderDto> getAllOrders();
}