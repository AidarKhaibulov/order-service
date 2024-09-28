package ru.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CancelOrderRequest(

        /*
          Canceled order id.
         */
        @JsonProperty("orderId") long orderId,

        /*
          User ID extracted from the token of the current user who owns the order.
        */
        @JsonProperty("userId") long userId) {
}

