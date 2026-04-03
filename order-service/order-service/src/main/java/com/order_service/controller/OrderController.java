package com.order_service.controller;

import com.order_service.dto.OrderResponseDTo;
import com.order_service.dto.ProductDTo;
import com.order_service.entity.Order;
import com.order_service.repository.OrderRepository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private WebClient.Builder web;

    // Get all orders
    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Get order by ID
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderRepository.save(order);
    }
    
    @PostMapping("/place")
    public Mono<ResponseEntity<OrderResponseDTo>> placeOrder(@RequestBody Order order) {
        Order savedOrder = orderRepository.save(order);

        return web.build()
                .get()
                .uri("http://localhost:8080/api/products/" + savedOrder.getProductId())
                .retrieve()
                .bodyToMono(ProductDTo.class)
                .map(productDTo -> {
                    OrderResponseDTo res = new OrderResponseDTo();
                    
                    res.setOrderid(savedOrder.getId());
                    res.setProductid(savedOrder.getProductId());
                    res.setQuantity(savedOrder.getQuantity());
                    res.setProductname(productDTo.getName());
                    res.setPrice(productDTo.getPrice());
                    res.setTotalPrice(savedOrder.getQuantity() * productDTo.getPrice());
                    
                    return ResponseEntity.ok(res);
                });
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id, @RequestBody Order orderDetails) {
        return orderRepository.findById(id)
                .map(order -> {
                    order.setProductId(orderDetails.getProductId());
                    order.setQuantity(orderDetails.getQuantity());
                    return orderRepository.save(order);
                })
                .orElse(null);
    }

    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orderRepository.deleteById(id);
        return "Order deleted successfully!";
    }
}
