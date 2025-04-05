package com.sk.skala.stockbackend.service;

import com.sk.skala.stockbackend.domain.Stock;
import com.sk.skala.stockbackend.repository.StockPriceHistoryRepository;
import com.sk.skala.stockbackend.repository.StockRepository;
import jakarta.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StockPriceUpdateService {

    private final EntityManager entityManager;

    private final StockRepository stockRepository;
    private final StockService stockService;

    private final StockPriceHistoryRepository stockPriceHistoryRepository;

    /**
     * 5초마다 모든 주식의 가격을 10% 내외로 변경하고 이력을 저장합니다. fixedRate: 메소드 실행 시작 시간으로부터 다음 실행 시작 시간까지의 간격
     */
    @Scheduled(fixedRate = 5000) //5초마다 실행
    @Transactional
    public void updateStockPrices() {
        List<Stock> stockList = stockRepository.findAll();

        for (int i = 0; i < stockList.size(); i++) {
            Stock stock = stockList.get(i);
            log.info("{}", stock.getName());
            log.info("변동 전: {}", stock.getPrice());
            int changedPrice = stockService.changePrice(stock);

            try {
                stockPriceHistoryRepository.insertHistory(UUID.randomUUID(), stock.getId(), changedPrice,
                        LocalDateTime.now());
            } catch (Exception e) {  // UUID 충돌 발생 시 새 UUID로 재시도
                stockPriceHistoryRepository.insertHistory(UUID.randomUUID(), stock.getId(), changedPrice,
                        LocalDateTime.now());
            }

            log.info("변동 후: {}", changedPrice);

            // 20개마다 수동 플러시 (메모리 관리)
            if (i > 0 && i % 20 == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }

    }
}
