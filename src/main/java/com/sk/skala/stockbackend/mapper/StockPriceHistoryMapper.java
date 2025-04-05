package com.sk.skala.stockbackend.mapper;

import com.sk.skala.stockbackend.domain.Stock;
import com.sk.skala.stockbackend.domain.StockPriceHistory;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class StockPriceHistoryMapper {

    public StockPriceHistory toEntity(final Stock stock, final int newPrice) {
        return new StockPriceHistory(
                UUID.randomUUID(),
                stock,
                newPrice,
                LocalDateTime.now()
        );
    }
}
