package ru.orderservice.services;

import jakarta.validation.Valid;
import ru.orderservice.dto.CancelOrderRequest;
import ru.orderservice.dto.CancelOrderResponse;
import ru.orderservice.dto.GetCanceledOrderRequest;
import ru.orderservice.dto.OrderCreateRequest;
import ru.orderservice.models.Order;

import java.util.Optional;

public interface IOrderService {

    Long createOrder(@Valid OrderCreateRequest request);

    CancelOrderResponse cancelOrder(@Valid CancelOrderRequest request);

    Optional<Order> getCanceledOrder(@Valid GetCanceledOrderRequest request);

}

