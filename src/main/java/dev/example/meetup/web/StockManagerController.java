package dev.example.meetup.web;

import dev.example.meetup.service.model.StockItemDTO;
import dev.example.meetup.service.StockManagerService;
import dev.example.meetup.web.model.StockItemDetailsView;
import dev.example.meetup.web.model.StockItemPayload;
import dev.example.meetup.web.model.StockItemView;
import dev.example.meetup.web.model.UpdateStockItemPayload;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StockManagerController {

    private final StockManagerService stockManagerService;

    public StockManagerController(StockManagerService stockManagerService) {
        this.stockManagerService = stockManagerService;
    }

    @GetMapping("/stock")
    public ResponseEntity<List<StockItemView>> getAllStockItems() {
        return ResponseEntity.ok(stockManagerService.getAllStockItems()
                .stream()
                .map(this::mapToStockItemView)
                .toList());
    }

    @PostMapping("/add-stock")
    public ResponseEntity<Void> addStockItem(@Valid @RequestBody StockItemPayload stockItemPayload) {
        stockManagerService.addStockItem(mapToStockItemDTO(stockItemPayload));

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/stock/{id}")
    public ResponseEntity<StockItemDetailsView> getStockItem(@PathVariable Long id) {
        return ResponseEntity.ok(mapToStockItemDetailsView(stockManagerService.getStockItem(id)));
    }

    @PutMapping("/stock/{id}")
    public ResponseEntity<Void> updateStockItem(@PathVariable Long id, @Valid @RequestBody UpdateStockItemPayload stockItemPayload) {
        stockManagerService.updateStockItem(id, mapUpdateStockItemPayloadToDTO(stockItemPayload));

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("/stock/{id}")
    public ResponseEntity<Void> deleteStockItem(@PathVariable Long id) {
        stockManagerService.deleteStockItem(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    private StockItemDTO mapUpdateStockItemPayloadToDTO(UpdateStockItemPayload stockItemPayload) {
        return new StockItemDTO(
                null,
                null,
                stockItemPayload.description(),
                stockItemPayload.quantity(),
                null,
                stockItemPayload.pricePerUnit()
        );
    }

    private StockItemDetailsView mapToStockItemDetailsView(StockItemDTO stockItem) {
        return new StockItemDetailsView(
                stockItem.id(),
                stockItem.name(),
                stockItem.description(),
                stockItem.quantity(),
                stockItem.unit(),
                stockItem.pricePerUnit()

        );
    }

    private StockItemView mapToStockItemView(StockItemDTO stockItemDTO) {
        return new StockItemView(
                stockItemDTO.id(),
                stockItemDTO.name(),
                stockItemDTO.description(),
                stockItemDTO.quantity()
        );
    }

    private StockItemDTO mapToStockItemDTO(StockItemPayload stockItemPayload) {
        return new StockItemDTO(
                null,
                stockItemPayload.name(),
                stockItemPayload.description(),
                stockItemPayload.quantity(),
                stockItemPayload.unit(),
                stockItemPayload.pricePerUnit()
        );
    }
}
