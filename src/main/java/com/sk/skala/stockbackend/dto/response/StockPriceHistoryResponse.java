package com.sk.skala.stockbackend.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StockPriceHistoryResponse {

    private int price;
    private LocalDateTime timestamp;

}
