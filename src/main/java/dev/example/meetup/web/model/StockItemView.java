package dev.example.meetup.web.model;

public record StockItemView (
        Long id,
        String name,
        String description,
        Integer quantity
) {}
