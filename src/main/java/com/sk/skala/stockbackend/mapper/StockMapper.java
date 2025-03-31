package com.sk.skala.stockbackend.mapper;

import com.sk.skala.stockbackend.domain.Stock;
import com.sk.skala.stockbackend.dto.request.CreateStockRequest;
import com.sk.skala.stockbackend.dto.response.StockResponse;
import java.util.ArrayList;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class StockMapper {

    public Stock toEntity(final CreateStockRequest request) {
        return new Stock(
                UUID.randomUUID(),
                request.getName(),
                request.getPrice(),
                request.getDescription(),
                new ArrayList<>()
        );
    }

    public StockResponse toResponse(final Stock stock) {
        return new StockResponse(
                stock.getName(),
                stock.getPrice());
    }
}
