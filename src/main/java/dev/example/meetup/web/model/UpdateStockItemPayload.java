package dev.example.meetup.web.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateStockItemPayload (
        @NotBlank
        String description,
        @NotNull
        Integer quantity,
        Double pricePerUnit
) {}
