package ru.orderservice.controllers;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.orderservice.dto.CancelOrderRequest;
import ru.orderservice.dto.CancelOrderResponse;
import ru.orderservice.dto.OrderCreateRequest;
import io.swagger.v3.oas.annotations.Operation;
import ru.orderservice.services.OrderService;

@CrossOrigin()
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping()
    @Operation(
            summary = "Create a new order",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Required params for creating an order",
                    content = @Content(
                            schema = @Schema(implementation = OrderCreateRequest.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Order successfully created",
                            content = @Content(
                                    schema = @Schema(type = "integer", format = "int64")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error"
                    )
            }
    )
    public ResponseEntity<Long> createOrder(@Valid @RequestBody OrderCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(request));
    }

    @PostMapping("/cancel")
    public CancelOrderResponse cancelOrder(@Valid @RequestBody CancelOrderRequest request) {
       return orderService.cancelOrder(request);
    }

}

