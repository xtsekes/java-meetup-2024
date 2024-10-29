package dev.example.meetup.web;

import dev.example.meetup.service.StockManagerService;
import dev.example.meetup.service.model.StockItemDTO;
import dev.example.meetup.web.model.StockItemDetailsView;
import dev.example.meetup.web.model.StockItemPayload;
import dev.example.meetup.web.model.StockItemView;
import dev.example.meetup.web.model.UpdateStockItemPayload;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StockManagerControllerTest {

    @Mock
    private StockManagerService stockManagerService;

    @InjectMocks
    private StockManagerController stockManagerController;

    @Test
    void givenStockItemsExist_whenGetAllStockItems_thenReturnListOfStockItems() {
        // Given
        when(stockManagerService.getAllStockItems()).thenReturn(expectedListOfStockItemDTOs());
        // When
        ResponseEntity<List<StockItemView>> response = stockManagerController.getAllStockItems();
        // Then
        verify(stockManagerService, times(1)).getAllStockItems();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).containsExactlyElementsOf(actualListOfStockItemViews());
    }

    @Test
    void givenNewStockItem_whenAddStockItem_thenExpectStockItemAdded() {
        // Given
        StockItemPayload stockItemPayload = new StockItemPayload("Item1", "Description1", 10, "pcs", 5.99);
        // When
        ResponseEntity<Void> response = stockManagerController.addStockItem(stockItemPayload);
        // Then
        verify(stockManagerService, times(1))
                .addStockItem(new StockItemDTO(
                        null,
                        stockItemPayload.name(),
                        stockItemPayload.description(),
                        stockItemPayload.quantity(),
                        stockItemPayload.unit(),
                        stockItemPayload.pricePerUnit())
                );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void givenStockItemExists_whenGetStockItem_thenReturnStockItemDetails() {
        // Given
        Long id = 1L;
        when(stockManagerService.getStockItem(id)).thenReturn(expectedListOfStockItemDTOs().getFirst());
        // When
        ResponseEntity<StockItemDetailsView> response = stockManagerController.getStockItem(id);
        // Then
        verify(stockManagerService, times(1)).getStockItem(id);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(actualStockItemDetailsView());
    }

    @Test
    void givenStockItemDoesNotExist_whenGetStockItem_thenThrowException() {
        // Given
        Long id = 2L;
        // When
        when(stockManagerService.getStockItem(id)).thenThrow(new IllegalArgumentException("Stock item not found"));
        // Then
        assertThatThrownBy(() -> stockManagerController.getStockItem(id))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Stock item not found");
    }

    @Test
    void givenStockItemExists_whenUpdateStockItem_thenStockItemIsUpdated() {
        // Given
        Long id = 1L;
        UpdateStockItemPayload updateStockItemPayload = new UpdateStockItemPayload("Description1", 10, 5.99);
        // When
        stockManagerController.updateStockItem(id, updateStockItemPayload);
        // Then
        verify(stockManagerService, times(1))
                .updateStockItem(id, new StockItemDTO(
                        null,
                        null,
                        updateStockItemPayload.description(),
                        updateStockItemPayload.quantity(),
                        null,
                        updateStockItemPayload.pricePerUnit())
                );
    }

    @Test
    void givenStockItemDoesNotExist_whenUpdateStockItem_thenThrowException() {
        // Given
        Long id = 2L;
        UpdateStockItemPayload updateStockItemPayload = new UpdateStockItemPayload("Description2", 10, 5.99);
        StockItemDTO stockItemDTO = new StockItemDTO(
                null,
                null,
                updateStockItemPayload.description(),
                updateStockItemPayload.quantity(),
                null,
                updateStockItemPayload.pricePerUnit()
        );
        // When
        doThrow(new IllegalArgumentException("Stock item not found"))
                .when(stockManagerService).updateStockItem(id, stockItemDTO);
        // Then
        assertThatThrownBy(() -> stockManagerController.updateStockItem(id, updateStockItemPayload))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Stock item not found");
    }

    private List<StockItemDTO> expectedListOfStockItemDTOs() {
        return List.of(
                new StockItemDTO(1L, "Item1", "Description1", 10, "pcs", 5.99),
                new StockItemDTO(2L, "Item2", "Description2", 20, "pcs", 10.99)
        );
    }

    private List<StockItemView> actualListOfStockItemViews() {
        return List.of(
                new StockItemView(1L, "Item1", "Description1", 10),
                new StockItemView(2L, "Item2", "Description2", 20)
        );
    }

    private StockItemDetailsView actualStockItemDetailsView() {
        return new StockItemDetailsView(1L, "Item1", "Description1", 10, "pcs", 5.99);
    }

}