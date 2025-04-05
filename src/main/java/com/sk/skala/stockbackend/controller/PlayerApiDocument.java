package com.sk.skala.stockbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Tag(name = "Player API", description = "사용자 API입니다.")
public @interface PlayerApiDocument {

    @Operation(summary = "로그인", description = "플레이어가 로그인을 합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "로그인 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터"),
            @ApiResponse(responseCode = "404", description = "플레이어가 존재하지 않습니다."),
            @ApiResponse(responseCode = "409", description = "비밀번호가 일치하지 않습니다."),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface LoginApiDoc {
    }

    @Operation(summary = "회원가입", description = "새로운 사용자가 회원가입을 합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "새로운 플레이어 회원 가입 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터"),
            @ApiResponse(responseCode = "409", description = "이미 존재하는 닉네임입니다."),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface SignUpApiDoc {
    }

    @Operation(summary = "플레이어 전체 목록 조회", description = "전체 플레이어를 조회합니다. 플레이어를 10개씩 반환합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "전체 플레이어 목록 조회 성공"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface GetPlayerListApiDoc {
    }

    @Operation(summary = "플레이어 자금 추가", description = "사용자에게 추가 자본금을 받습니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "플레이어 자본금 추가 완료"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터"),
            @ApiResponse(responseCode = "404", description = "플레이어를 찾을 수 없습니다."),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface AddPlayerApiDoc {
    }

}
