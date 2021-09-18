package br.com.cafeteria.service;

import br.com.cafeteria.dto.ProductOrderDto;

public interface ProductOrderService {

    ProductOrderDto addNewProduct(Long orderId, Long productId, Integer quantity);

    ProductOrderDto removeProduct(Long orderId, Long productId, Integer quantity);
}
