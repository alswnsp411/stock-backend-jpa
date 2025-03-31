package com.sk.skala.stockbackend.controller;

import com.sk.skala.stockbackend.dto.request.CreateStockRequest;
import com.sk.skala.stockbackend.dto.response.StockResponse;
import com.sk.skala.stockbackend.service.StockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Stock API", description = "주식 API입니다.")
@RestController
@RequestMapping("/api/stocks")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    /**
     * 전체 주식 목록 출력
     */
    @Operation(summary = "전체 주식 목록 확인", description = "시장에 나와있는 전체 주식 목록을 조회합니다.")
    @GetMapping("")
    public ResponseEntity<List<StockResponse>> getCurrentStockList() {
        List<StockResponse> response = stockService.getCurrentStockList();

        return ResponseEntity.ok(response);
    }

    /**
     * 주식 상장
     */
    @Operation(summary = "주식 상장", description = "새로운 주식을 생성합니다.")
    @PostMapping("")
    public ResponseEntity<StockResponse> addStock(@RequestBody CreateStockRequest request) {
        StockResponse response = stockService.createStock(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * 주식 상장 폐지
     */
//    @Operation(summary = "주식 상장 폐지", description = "요청된 주식을 삭제합니다. 회원이 보유한 주식 목록에서도 삭제합니다.")
//    @DeleteMapping("")
//    public ResponseEntity<String> deleteStock() {
//        return ResponseEntity.ok("");
//    }
}
