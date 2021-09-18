package br.com.cafeteria.controller;

import br.com.cafeteria.dto.OrderDto;
import br.com.cafeteria.dto.ProductOrderDto;
import br.com.cafeteria.service.OrderService;
import br.com.cafeteria.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductOrderService productOrderService;

    @PostMapping("/create-new-order")
    public Long createNewOrder() {
        return orderService.createNewOrder();
    }

    @PutMapping("/{orderId}/{productId}/{quantity}")
    public ProductOrderDto addNewProduct(@PathVariable Long orderId, @PathVariable Long productId, @PathVariable Integer quantity) {
        return productOrderService.addNewProduct(orderId, productId, quantity);
    }

    @DeleteMapping("/{orderId}/{productId}/{quantity}")
    public ProductOrderDto removeProduct(@PathVariable Long orderId, @PathVariable Long productId, @PathVariable Integer quantity) {
        return productOrderService.removeProduct(orderId, productId, quantity);
    }

    @GetMapping()
    public OrderDto getTotalPrice(@RequestParam Long orderId) {
        return orderService.getTotalPrice(orderId);
    }

    @PutMapping("/close-order/{orderId}/{paidValue}/{totalPrice}")
    public OrderDto closeOrder(@PathVariable Long orderId, @PathVariable BigDecimal paidValue, @PathVariable BigDecimal totalPrice) {
        return orderService.closeOrder(orderId, paidValue, totalPrice);
    }

    @GetMapping("get-all")
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }
}
