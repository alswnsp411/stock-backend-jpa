package com.sk.skala.stockbackend.dto.request;

import lombok.Getter;

@Getter
public class SignUpRequest {

    private String nickname;

    private String password;

    private int money;
    
}
