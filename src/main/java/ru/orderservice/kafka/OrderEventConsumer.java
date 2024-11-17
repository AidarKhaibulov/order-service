package ru.orderservice.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.orderservice.dto.OrderCreateRequest;
import ru.orderservice.services.IOrderService;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderEventConsumer {

    private final IOrderService orderService;

    @KafkaListener(topics = "orderCreate", groupId = "order-service-group")
    public void handleOrderCreateEvent(OrderCreateRequest request) {

        log.info("Received orderCreateEvent: {}", request);

        orderService.createOrder(request);
    }

}