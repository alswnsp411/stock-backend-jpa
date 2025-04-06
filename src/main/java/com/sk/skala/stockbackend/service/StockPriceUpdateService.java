package com.sk.skala.stockbackend.service;

import com.sk.skala.stockbackend.domain.Stock;
import com.sk.skala.stockbackend.domain.StockPriceHistory;
import com.sk.skala.stockbackend.mapper.StockPriceHistoryMapper;
import com.sk.skala.stockbackend.repository.StockPriceHistoryBatchRepositoryImpl;
import com.sk.skala.stockbackend.repository.StockRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

    private final StockRepository stockRepository;
    private final StockService stockService;

    private final StockPriceHistoryBatchRepositoryImpl stockPriceHistoryBatchRepositoryImpl;
    private final StockPriceHistoryMapper stockPriceHistoryMapper;

    /**
     * 5초마다 모든 주식의 가격을 10% 내외로 변경하고 이력을 저장합니다. fixedRate: 메소드 실행 시작 시간으로부터 다음 실행 시작 시간까지의 간격
     */
    @Scheduled(fixedRate = 5000) //5초마다 실행
    @Transactional
    public void updateStockPrices() {
        List<Stock> stockList = stockRepository.findAll();
        List<StockPriceHistory> historyList = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();

        for (Stock stock : stockList) {
            log.info("{}", stock.getName());
            log.info("변동 전: {}", stock.getPrice());
            int changedPrice = stockService.changePrice(stock);

            StockPriceHistory history = stockPriceHistoryMapper.toEntity(stock, changedPrice, now);
            historyList.add(history);
            log.info("변동 후: {}", changedPrice);
        }

        stockPriceHistoryBatchRepositoryImpl.saveAllInOneBatch(historyList);


    }
}
