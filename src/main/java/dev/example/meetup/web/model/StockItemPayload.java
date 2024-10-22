package dev.example.meetup.web.model;

public record StockItemPayload(
        String name,
        String description,
        Integer quantity,
        String unit,
        Double pricePerUnit
) {}
