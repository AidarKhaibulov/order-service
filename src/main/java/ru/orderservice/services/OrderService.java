package ru.orderservice.services;

import jakarta.validation.Valid;
import ru.orderservice.dto.OrderCreateRequest;

public interface OrderService {

    Long createOrder(@Valid OrderCreateRequest request);
}

