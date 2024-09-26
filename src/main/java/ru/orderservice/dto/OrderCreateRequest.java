package ru.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

public record OrderCreateRequest(
        @NotNull @JsonProperty("userId") long userId
) {
}
