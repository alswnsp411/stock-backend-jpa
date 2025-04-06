package com.sk.skala.stockbackend.repository;

import com.sk.skala.stockbackend.domain.Stock;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, UUID> {
    Stock save(Stock stock);

    List<Stock> findAll();

    Optional<Stock> findByName(String name);

    boolean existsByName(String name);

    @Query("SELECT s FROM Stock s JOIN FETCH s.priceHistories ph WHERE s.id = :stockId")
    Optional<Stock> findByIdWithHistory(UUID stockId);
}