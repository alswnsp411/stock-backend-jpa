package com.sk.skala.stockbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlayerStockService {

    /**
     * 주식 가격 계산
     *
     * @param stockPrice    현재 주식 가격
     * @param stockQuantity 구매량
     */
    public int calculation(int stockPrice, int stockQuantity) {
        return stockPrice * stockQuantity;
    }
}
