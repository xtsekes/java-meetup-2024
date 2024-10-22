package dev.example.meetup.web.model;

public record StockItemDetailsView (
        Long id,
        String name,
        String description,
        Integer quantity,
        String unit,
        Double pricePerUnit,
        String lastUpdated
) {}
