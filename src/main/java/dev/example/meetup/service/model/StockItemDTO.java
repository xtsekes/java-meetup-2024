package dev.example.meetup.service.model;

import java.time.LocalDateTime;

public record StockItemDTO(
        Long id,
        String name,
        String description,
        Integer quantity,
        String unit,
        Double pricePerUnit,
        LocalDateTime lastUpdated
) {
}
