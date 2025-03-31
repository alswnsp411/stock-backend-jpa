package com.sk.skala.stockbackend.controller;

import com.sk.skala.stockbackend.dto.request.BuyPlayerStockRequest;
import com.sk.skala.stockbackend.dto.request.LoginRequest;
import com.sk.skala.stockbackend.dto.request.SellPlayerStockRequest;
import com.sk.skala.stockbackend.dto.request.SignUpRequest;
import com.sk.skala.stockbackend.dto.response.PlayerResponse;
import com.sk.skala.stockbackend.dto.response.PlayerStockListResponse;
import com.sk.skala.stockbackend.dto.response.PlayerStockResponse;
import com.sk.skala.stockbackend.service.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Player API", description = "사용자 API입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerService playerService;

    /**
     * 로그인
     */
    @Operation(summary = "로그인", description = "사용자가 로그인을 합니다.")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        playerService.login(loginRequest);
        return ResponseEntity.ok("로그인 되었습니다.");
    }

    /**
     * 회원가입
     */
    @Operation(summary = "회원가입", description = "새로운 회원 가입 요청을 생성합니다.")
    @PostMapping("/sign-up")
    public ResponseEntity<PlayerResponse> signUp(@RequestBody SignUpRequest signUpRequest) {
        PlayerResponse response = playerService.signUp(signUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * 보유 주식 목록 확인
     */
    @Operation(summary = "보유 주식 목록 확인", description = "사용자의 보유 주식 목록을 조회합니다.")
    @GetMapping("/player-stocks")
    public ResponseEntity<List<PlayerStockListResponse>> getPlayerStockList(@RequestParam("playerId") UUID playerId) {
        List<PlayerStockListResponse> response = playerService.getPlayerStockList(playerId);
        return ResponseEntity.ok(response);
    }

    /**
     * 주식 매수
     */
    @Operation(summary = "주식 매수", description = "사용자의 주식 매수 요청을 처리합니다.")
    @PostMapping("/player-stocks")
    public ResponseEntity<PlayerStockResponse> addPlayerStock(@RequestBody BuyPlayerStockRequest request) {
        PlayerStockResponse response = playerService.buyStock(request);
        return ResponseEntity.ok(response);
    }

    /**
     * 주식 매도
     */
    @Operation(summary = "주식 매도", description = "사용자의 주식 매도 요청을 처리합니다.")
    @PutMapping("/player-stocks")
    public ResponseEntity<PlayerStockResponse> sellPlayerStock(@RequestBody SellPlayerStockRequest request) {
        PlayerStockResponse response = playerService.sellPlayerStock(request);
        return ResponseEntity.ok(response);
    }

}
