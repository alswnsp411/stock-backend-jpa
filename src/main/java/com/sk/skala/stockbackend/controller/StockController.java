package com.sk.skala.stockbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
    /**
     * 전체 주식 목록 출력
     */
    @GetMapping("")
    public ResponseEntity<String> getStockList(){
        return ResponseEntity.ok("");
    }

    /**
     * 주식 상장
     */
    @PostMapping("")
    public ResponseEntity<String> addStock(){
        return ResponseEntity.ok("");
    }

    /**
     * 주식 상장 폐지
     */
    @DeleteMapping("")
    public ResponseEntity<String> deleteStock(){
        return ResponseEntity.ok("");
    }
}
