package br.com.cafeteria.service.impl;

import br.com.cafeteria.dto.OrderDto;
import br.com.cafeteria.entity.OrderEntity;
import br.com.cafeteria.enuns.OrderStatusEnum;
import br.com.cafeteria.repository.OrderRepository;
import br.com.cafeteria.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Long createNewOrder() {
        return orderRepository.save(new OrderEntity(OrderStatusEnum.OPENED)).getId();
    }

    @Override
    public Optional<OrderEntity> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public OrderDto getTotalPrice(Long orderId) {
        Optional<OrderEntity> orderEntity = orderRepository.findById(orderId);
        if (orderEntity.isPresent()) {
            BigDecimal totalPrice = orderEntity.get().getProductOrderEntityList().stream().map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity()))).reduce(BigDecimal.ZERO, BigDecimal::add);
            return new OrderDto(orderEntity.get().getId(), orderEntity.get().getStatus(), totalPrice);
        }
        return null;
    }

    @Override
    public OrderDto closeOrder(Long orderId, BigDecimal paidValue, BigDecimal totalPrice) {
        Optional<OrderEntity> orderEntity = orderRepository.findById(orderId);
        if (orderEntity.isPresent()) {
            orderEntity.get().closeOrder(OrderStatusEnum.PAID, paidValue, totalPrice);
            OrderEntity entity = orderRepository.save(orderEntity.get());
            return new OrderDto(entity);
        }
        return null;
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<OrderEntity> allOrdersEntity = orderRepository.findAll();
        return allOrdersEntity.stream().map(OrderDto::new).collect(Collectors.toList());
    }
}
