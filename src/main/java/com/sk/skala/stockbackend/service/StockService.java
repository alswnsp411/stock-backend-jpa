package com.sk.skala.stockbackend.service;

import com.sk.skala.stockbackend.domain.Stock;
import com.sk.skala.stockbackend.dto.request.CreateStockRequest;
import com.sk.skala.stockbackend.dto.response.StockResponse;
import com.sk.skala.stockbackend.exception.CustomException;
import com.sk.skala.stockbackend.mapper.StockMapper;
import com.sk.skala.stockbackend.repository.StockRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;
    private final StockMapper stockMapper;

    public Stock findById(final UUID stockId) {
        return stockRepository.findById(stockId)
                .orElseThrow(() -> new CustomException("해당 주식이 존재하지 않습니다.", HttpStatus.NOT_FOUND));
    }

    public Stock findByName(final String stockName) {
        return stockRepository.findByName(stockName)
                .orElseThrow(() -> new CustomException("해당 주식이 존재하지 않습니다.", HttpStatus.NOT_FOUND));
    }

    /**
     * 전체 주식 목록 출력
     *
     * @return
     */
    public List<StockResponse> getCurrentStockList(Pageable pageable) {
        Page<Stock> marketStockList = stockRepository.findAll(pageable);
        return marketStockList.stream().map(stockMapper::toResponse).toList();
    }

    /**
     * 주식 상장
     *
     * @param request
     * @return
     */
    @Transactional
    public StockResponse createStock(CreateStockRequest request) {
        if (stockRepository.existsByName(request.getName())) {
            throw new CustomException("이미 존재하는 주식입니다.", HttpStatus.CONFLICT);
        }

        Stock stock = stockMapper.toEntity(request);
        stock = stockRepository.save(stock);

        return stockMapper.toResponse(stock);
    }

    /**
     * 랜덤 비율로 주식 가격을 변경합니다.
     *
     * @return 변경된 주식 가격
     */
    @Transactional
    public int changePrice(Stock stock) {
        int changedPrice = calculateFluctuation(stock.getPrice());
        stock.changePrice(changedPrice);

        return changedPrice;
    }

    public int calculateFluctuation(int price) {
        // 가격 변동률(-10% ~ +10% 범위의 랜덤 값)
        double percentage = (Math.random() * 20 - 10) / 100.0;
        //새 가격 계산(최소 1원으로 제한)
        return Math.max(1, (int) (price * (1 + percentage)));
    }
}
