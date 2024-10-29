package dev.example.meetup.data;

import dev.example.meetup.service.model.StockItemDTO;

public class StockItemJPAMapper {
    public static StockItemDTO toDTO(StockItemJPA stockItemJPA) {
        return new StockItemDTO(
                stockItemJPA.getId(),
                stockItemJPA.getName(),
                stockItemJPA.getDescription(),
                stockItemJPA.getQuantity(),
                stockItemJPA.getUnit(),
                stockItemJPA.getPricePerUnit()
        );
    }

    public static StockItemJPA toJPA(StockItemDTO stockItemDTO) {
        StockItemJPA stockItemJPA = new StockItemJPA();
        stockItemJPA.setName(stockItemDTO.name());
        stockItemJPA.setDescription(stockItemDTO.description());
        stockItemJPA.setQuantity(stockItemDTO.quantity());
        stockItemJPA.setUnit(stockItemDTO.unit());
        stockItemJPA.setPricePerUnit(stockItemDTO.pricePerUnit());

        return stockItemJPA;
    }
}
