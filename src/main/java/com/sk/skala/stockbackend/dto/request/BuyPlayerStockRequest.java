package com.sk.skala.stockbackend.dto.request;

import java.util.UUID;
import lombok.Getter;

@Getter
public class BuyPlayerStockRequest {
    //    @NotBlank
    private UUID playerId;
    //    @NotBlank
    private UUID stockId;
    //    @NotBlank
    private int stockQuantity;
}
