package com.sk.skala.stockbackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlayerResponse {
    private String nickname;
    private int money;
}
