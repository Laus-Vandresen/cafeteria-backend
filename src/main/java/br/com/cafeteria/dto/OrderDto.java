package br.com.cafeteria.dto;


import br.com.cafeteria.entity.OrderEntity;
import br.com.cafeteria.enuns.OrderStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long id;
    private OrderStatusEnum status;
    private BigDecimal paidValue;
    private BigDecimal totalPrice;

    public OrderDto(Long id, OrderStatusEnum status, BigDecimal totalPrice) {
        this.id = id;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    public OrderDto(OrderEntity orderEntity) {
        this.id = orderEntity.getId();
        this.status = orderEntity.getStatus();
        this.paidValue = orderEntity.getPaidValue();
        this.totalPrice = orderEntity.getTotalPrice();
    }
}
