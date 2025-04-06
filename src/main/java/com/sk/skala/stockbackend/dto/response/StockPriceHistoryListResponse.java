package com.sk.skala.stockbackend.dto.response;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StockPriceHistoryListResponse {

    private UUID stockId;
    private String stockName;
    private String description;

    private List<StockPriceHistoryResponse> stockPriceHistoryResponses;

}
