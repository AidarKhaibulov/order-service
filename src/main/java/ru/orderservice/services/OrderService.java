package ru.orderservice.services;

import jakarta.validation.Valid;
import ru.orderservice.dto.CancelOrderRequest;
import ru.orderservice.dto.CancelOrderResponse;
import ru.orderservice.dto.OrderCreateRequest;

public interface OrderService {

    Long createOrder(@Valid OrderCreateRequest request);

    CancelOrderResponse cancelOrder(@Valid CancelOrderRequest request);

}

