package dev.example.meetup.web.model;

public record UpdateStockItemPayload (
        String description,
        Integer quantity,
        Double pricePerUnit
) {}
