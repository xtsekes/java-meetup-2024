package dev.example.meetup.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "stock_item")
public class StockItemJPA {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "stock_item_seq_gen")
    @SequenceGenerator(name = "stock_item_seq_gen", sequenceName = "stock_item_seq", allocationSize = 1)
    private Long id;

    private String name;

    private String description;

    private Integer quantity;

    private String unit;

    private Double pricePerUnit;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }
}
