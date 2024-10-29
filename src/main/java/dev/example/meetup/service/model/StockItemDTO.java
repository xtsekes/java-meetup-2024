package dev.example.meetup.service.model;

public record StockItemDTO(
        Long id,
        String name,
        String description,
        Integer quantity,
        String unit,
        Double pricePerUnit
) {
}
