package com.sk.skala.stockbackend.controller;

import com.sk.skala.stockbackend.dto.request.LoginRequest;
import com.sk.skala.stockbackend.dto.request.SignUpRequest;
import com.sk.skala.stockbackend.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerService playerService;

    /**
     * 로그인
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        playerService.login(loginRequest);
        return ResponseEntity.ok("로그인 되었습니다.");
    }

    /**
     * 회원가입
     */
    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequest signUpRequest){
        playerService.signUp(signUpRequest);
        return ResponseEntity.ok("회원가입 되었습니다.");
    }

    /**
     * 보유 주식 목록 확인
     */
    @GetMapping("/player-stocks")
    public ResponseEntity<String> getPlayerStockList() {
        return ResponseEntity.ok("");
    }

    /**
     * 주식 구매
     */
    @PostMapping("/player-stocks")
    public ResponseEntity<String> addPlayerStock(){
        return ResponseEntity.ok("");
    }

    /**
     * 주식 판매
     */
    @PutMapping("/player-stocks")
    public ResponseEntity<String> putPlayerStock() {
        return ResponseEntity.ok("");
    }

}
