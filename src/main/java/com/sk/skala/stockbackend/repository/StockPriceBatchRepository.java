package com.sk.skala.stockbackend.repository;

import java.util.Map;
import java.util.UUID;

public interface StockPriceBatchRepository {

    void updatePricesAll(Map<UUID, Integer> stockPriceUpdateList);
}
