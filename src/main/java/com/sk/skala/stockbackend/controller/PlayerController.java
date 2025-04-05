package com.sk.skala.stockbackend.controller;

import com.sk.skala.stockbackend.dto.request.AddMoneyRequest;
import com.sk.skala.stockbackend.dto.request.LoginRequest;
import com.sk.skala.stockbackend.dto.request.SignUpRequest;
import com.sk.skala.stockbackend.dto.response.PlayerResponse;
import com.sk.skala.stockbackend.service.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @PlayerApiDocument.LoginApiDoc
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        playerService.login(loginRequest);
        return ResponseEntity.ok("로그인 되었습니다.");
    }

    /**
     * 회원가입
     */
    @Operation(summary = "회원가입", description = "회원 가입 요청에 대해 새로운 사용자 데이터를 생성합니다.")
    @PostMapping("/sign-up")
    @PlayerApiDocument.SignUpApiDoc
    public ResponseEntity<PlayerResponse> signUp(@RequestBody SignUpRequest signUpRequest) {
        PlayerResponse response = playerService.signUp(signUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * 플레이어 전체 목록 조회
     */
    @Operation(summary = "플레이어 전체 목록 조회", description = "모든 플레이어를 조회합니다.")
    @GetMapping("")
    @PlayerApiDocument.GetPlayerListApiDoc
    public ResponseEntity<List<PlayerResponse>> getPlayerList(
            @RequestParam(value = "page", defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "id"));
        List<PlayerResponse> response = playerService.getPlayerList(pageable);
        return ResponseEntity.ok(response);
    }

    /**
     * 플레이어 자금 추가
     */
    @Operation(summary = "플레이어 자금 추가", description = "자금 추가 요청으로부터 사용자의 자본금을 추가합니다.")
    @PutMapping("/{playerId}/money")
    @PlayerApiDocument.AddPlayerApiDoc
    public ResponseEntity<PlayerResponse> sellPlayerStock(@PathVariable("playerId") UUID playerId,
                                                          @RequestBody AddMoneyRequest request) {
        PlayerResponse response = playerService.addPlayerMoney(playerId, request);
        return ResponseEntity.ok(response);
    }

}
