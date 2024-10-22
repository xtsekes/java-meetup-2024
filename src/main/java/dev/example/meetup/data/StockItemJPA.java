package dev.example.meetup.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "stock_item")
public class StockItemJPA {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "stock_item_seq_gen")
    @SequenceGenerator(name = "stock_item_seq_gen", sequenceName = "stock_item_seq", allocationSize = 1)
    @Getter
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private Integer quantity;

    @Getter
    @Setter
    private String unit;

    @Getter
    @Setter
    private Double pricePerUnit;

    @Getter
    @Setter
    @LastModifiedDate
    private LocalDateTime lastUpdated;
}
