package com.sk.skala.stockbackend.repository;

import com.sk.skala.stockbackend.domain.StockPriceHistory;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StockPriceHistoryRepository extends JpaRepository<StockPriceHistory, UUID> {

    @Modifying
    @Query(value =
            "INSERT INTO stock_price_histories (stock_price_history_id, stock_id, price, timestamp) " +
                    "VALUES (:id, :stockId, :price, :timestamp)",
            nativeQuery = true)
    void insertHistory(@Param("id") UUID id,
                       @Param("stockId") UUID stockId,
                       @Param("price") int price,
                       @Param("timestamp") LocalDateTime timestamp);
} 