package ru.orderservice.services.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.orderservice.dto.OrderCreateRequest;
import ru.orderservice.enums.OrderStatus;
import ru.orderservice.models.Order;
import ru.orderservice.repositories.OrderRepository;
import ru.orderservice.services.OrderService;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

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
}
