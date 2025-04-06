package com.sk.skala.stockbackend.repository;

import com.sk.skala.stockbackend.domain.StockPriceHistory;
import java.util.List;

public interface StockPriceHistoryBatchRepository {
    void saveAllInOneBatch(List<StockPriceHistory> historyList);

    void saveAll(List<StockPriceHistory> histories);
}
