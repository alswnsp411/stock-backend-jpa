package com.sk.skala.stockbackend.dto.request;

import java.util.UUID;
import lombok.Getter;

@Getter
public class SellPlayerStockRequest {
    //    @NotBlank
    private UUID playerId;
    //    @NotBlank
    private UUID stockId;
    //    @NotBlank
    private int stockQuantity;
}
