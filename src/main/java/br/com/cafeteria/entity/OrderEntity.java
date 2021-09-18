package br.com.cafeteria.entity;

import br.com.cafeteria.enuns.OrderStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private OrderStatusEnum status;

    private BigDecimal paidValue;

    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<ProductOrderEntity> productOrderEntityList;

    public OrderEntity(OrderStatusEnum status) {
        this.status = status;
    }

    public OrderEntity closeOrder(OrderStatusEnum orderStatusEnum, BigDecimal paidValue, BigDecimal totalPrice) {
        this.status = orderStatusEnum;
        this.paidValue = paidValue;
        this.totalPrice = totalPrice;
        return this;
    }
}