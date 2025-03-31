package com.sk.skala.stockbackend.dto.request;

import lombok.Getter;

@Getter
public class CreateStockRequest {
    //    @NotBlank
    private String name;
    //    @NotBlank
    private int price;
    //    @NotBlank
    private String description;
}
