package com.sk.skala.stockbackend.mapper;

import com.sk.skala.stockbackend.domain.StockPriceHistory;
import com.sk.skala.stockbackend.dto.response.StockPriceHistoryResponse;
import org.springframework.stereotype.Component;

@Component
public class StockHistoryMapper {

    public StockPriceHistoryResponse toResponse(final StockPriceHistory priceHistory) {
        return new StockPriceHistoryResponse(
                priceHistory.getPrice(),
                priceHistory.getTimestamp());
    }

}
