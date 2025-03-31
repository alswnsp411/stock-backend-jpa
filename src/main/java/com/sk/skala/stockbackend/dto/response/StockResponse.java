package com.sk.skala.stockbackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StockResponse {
    private String stockName;
    private int stockPrice;
}
