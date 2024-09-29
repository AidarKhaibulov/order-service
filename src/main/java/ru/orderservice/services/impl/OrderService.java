package ru.orderservice.services.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.orderservice.dto.CancelOrderRequest;
import ru.orderservice.dto.CancelOrderResponse;
import ru.orderservice.dto.GetCanceledOrderRequest;
import ru.orderservice.dto.OrderCreateRequest;
import ru.orderservice.enums.OrderStatus;
import ru.orderservice.models.Order;
import ru.orderservice.repositories.OrderRepository;
import ru.orderservice.services.IOrderService;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "or")
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;

    @Override
    public Long createOrder(@Valid OrderCreateRequest request) {
        Order order = Order.builder()
                .userId(request.userId())
                .status(OrderStatus.OPEN)
                .build();
        Long createdOrderId = orderRepository.save(order).getId();
        log.info("Created order with id {}", createdOrderId);
        return createdOrderId;
    }

    @Override
    public CancelOrderResponse cancelOrder(CancelOrderRequest request) {
        Optional<Order> order = orderRepository.findByIdAndUserId(request.orderId(), request.userId());
        if (order.isPresent()) {
            Order o = order.get();
            orderRepository.updateStatusById(OrderStatus.CANCELED, o.getId());
            return CancelOrderResponse.builder()
                    .isCanceled(true)
                    .build();
        } else {
            return CancelOrderResponse.builder()
                    .isCanceled(false)
                    .description("Order not found!")
                    .build();
        }
    }

    @Override
    @Cacheable(key = "#request.orderId()")
    public Optional<Order> getCanceledOrder(GetCanceledOrderRequest request) {
        return orderRepository.findByIdAndUserIdAndStatus(request.orderId(), request.userId(), OrderStatus.CANCELED);
    }

}

