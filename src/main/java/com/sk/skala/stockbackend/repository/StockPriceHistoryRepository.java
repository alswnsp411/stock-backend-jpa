package com.sk.skala.stockbackend.repository;

import com.sk.skala.stockbackend.domain.StockPriceHistory;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockPriceHistoryRepository extends JpaRepository<StockPriceHistory, UUID>, StockPriceHistoryBatchRepository {

}