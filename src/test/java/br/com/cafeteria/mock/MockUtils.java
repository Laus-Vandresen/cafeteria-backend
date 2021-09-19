package br.com.cafeteria.mock;

import br.com.cafeteria.entity.OrderEntity;
import br.com.cafeteria.entity.ProductEntity;
import br.com.cafeteria.entity.ProductOrderEntity;
import br.com.cafeteria.enuns.OrderStatusEnum;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class MockUtils {

    public static OrderEntity getOrderEntityMock() {
        return new OrderEntity(1L, OrderStatusEnum.PAID, BigDecimal.valueOf(200D), BigDecimal.valueOf(180D), Arrays.asList(new ProductOrderEntity(null, new ProductEntity(1L, "PRODUCT MOCK", BigDecimal.valueOf(200D)), 1),
                new ProductOrderEntity(null, new ProductEntity(2L, "PRODUCT MOCK 2", BigDecimal.valueOf(150D)), 1)));
    }

    public static List<OrderEntity> getAllOrdersMock() {
        return Arrays.asList(new OrderEntity(1L, OrderStatusEnum.OPENED, BigDecimal.valueOf(200D), BigDecimal.valueOf(180D)), new OrderEntity(2L, OrderStatusEnum.PAID, BigDecimal.valueOf(250D), BigDecimal.valueOf(200D)));
    }
}
