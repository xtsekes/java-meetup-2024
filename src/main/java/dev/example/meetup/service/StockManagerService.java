package dev.example.meetup.service;

import dev.example.meetup.data.StockItemJPAMapper;
import dev.example.meetup.data.StockItemJPARepository;
import dev.example.meetup.service.model.StockItemDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockManagerService {

    private final StockItemJPARepository stockItemJPARepository;

    public StockManagerService(StockItemJPARepository stockItemJPARepository) {
        this.stockItemJPARepository = stockItemJPARepository;
    }

    public List<StockItemDTO> getAllStockItems() {
        return stockItemJPARepository.findAll()
                .stream()
                .map(StockItemJPAMapper::toDTO)
                .toList();
    }

    public void addStockItem(StockItemDTO stockItemDTO) {
        stockItemJPARepository.save(StockItemJPAMapper.toJPA(stockItemDTO));
    }

    public StockItemDTO getStockItem(Long id) {
        return stockItemJPARepository.findById(id)
                .map(StockItemJPAMapper::toDTO)
                .orElseThrow(() -> new IllegalArgumentException("Stock item not found"));
    }

    public void updateStockItem(Long id, StockItemDTO stockItemDTO) {
        stockItemJPARepository.findById(id)
                .map(stockItemJPA -> {
                    stockItemJPA.setDescription(stockItemDTO.description());
                    stockItemJPA.setQuantity(stockItemDTO.quantity());
                    stockItemJPA.setPricePerUnit(stockItemDTO.pricePerUnit());

                    return stockItemJPARepository.save(stockItemJPA);
                })
                .orElseThrow(() -> new IllegalArgumentException("Stock item not found"));
    }

    public void deleteStockItem(Long id) {
        stockItemJPARepository.deleteById(id);
    }
}
