package br.com.cafeteria.service.impl;

import br.com.cafeteria.dto.ProductOrderDto;
import br.com.cafeteria.entity.OrderEntity;
import br.com.cafeteria.entity.ProductEntity;
import br.com.cafeteria.entity.ProductOrderEntity;
import br.com.cafeteria.repository.ProductOrderRepository;
import br.com.cafeteria.service.OrderService;
import br.com.cafeteria.service.ProductOrderService;
import br.com.cafeteria.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class ProductOrderServiceImpl implements ProductOrderService {

    @Autowired
    private ProductOrderRepository productOrderRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Override
    public ProductOrderDto addNewProduct(Long orderId, Long productId, Integer quantity) {
        Optional<OrderEntity> orderEntity = orderService.findById(orderId);
        Optional<ProductEntity> productEntity = productService.findById(productId);
        if (orderEntity.isPresent() && productEntity.isPresent()) {
            ProductOrderEntity productOrderEntity = productOrderRepository.findFirstByOrderIdAndProductId(orderId, productId);
            if (Objects.nonNull(productOrderEntity)) {
                productOrderEntity.updateQuantity(productOrderEntity.getQuantity() + quantity);
                productOrderRepository.save(productOrderEntity);
                return new ProductOrderDto(productOrderEntity);
            } else {
                return new ProductOrderDto(productOrderRepository.save(new ProductOrderEntity(orderEntity.get(), productEntity.get(), quantity)));
            }
        }
        return null;
    }

    @Override
    public ProductOrderDto removeProduct(Long orderId, Long productId, Integer quantity) {
        ProductOrderEntity productOrderEntity = productOrderRepository.findFirstByOrderIdAndProductId(orderId, productId);
        if (Objects.nonNull(productOrderEntity)) {
            if (quantity < productOrderEntity.getQuantity()) {
                productOrderEntity.updateQuantity(productOrderEntity.getQuantity() - quantity);
                productOrderRepository.save(productOrderEntity);
            } else {
                productOrderEntity.updateQuantity(0);
                productOrderRepository.deleteById(productOrderEntity.getId());
            }
            return new ProductOrderDto(productOrderEntity);
        }
        return new ProductOrderDto(0);
    }
}