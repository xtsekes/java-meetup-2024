package dev.example.meetup.web.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StockItemPayload(
        @NotBlank
        String name,
        @NotBlank
        String description,
        @NotNull
        Integer quantity,
        @NotBlank
        String unit,
        @NotNull
        Double pricePerUnit
) {}
