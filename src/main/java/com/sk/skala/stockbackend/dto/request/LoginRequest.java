package com.sk.skala.stockbackend.dto.request;

import lombok.Getter;

@Getter
public class LoginRequest {
//    @NotBlank
    private String nickname;
//    @NotBlank
    private String password;
}
