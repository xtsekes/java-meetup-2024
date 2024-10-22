package dev.example.meetup.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StockItemJPARepository extends JpaRepository<StockItemJPA, Long> {
}