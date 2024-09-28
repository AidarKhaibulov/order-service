package ru.orderservice.dto;

public record GetCanceledOrderRequest(
        long orderId,
        long userId
) {
}


