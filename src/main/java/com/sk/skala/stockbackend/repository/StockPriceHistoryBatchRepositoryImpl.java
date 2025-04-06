package com.sk.skala.stockbackend.repository;

import com.sk.skala.stockbackend.domain.StockPriceHistory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public class StockPriceHistoryBatchRepositoryImpl implements StockPriceHistoryBatchRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private final int BATCH_SIZE = 20;

    @Override
    public void saveAllInOneBatch(List<StockPriceHistory> histories) {
        if (histories.isEmpty()) {
            return;
        }

        // 배치 사이즈에 맞게 처리
        for (int i = 0; i < histories.size(); i += BATCH_SIZE) {
            int endIndex = Math.min(i + BATCH_SIZE, histories.size());
            List<StockPriceHistory> batch = histories.subList(i, endIndex);

            saveAll(batch);
        }
    }

    @Override
    // 단일 INSERT 문으로 모든 레코드 삽입
    public void saveAll(List<StockPriceHistory> histories) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO stock_price_histories (stock_price_history_id, price, stock_id, timestamp) VALUES ");

        // 값 목록 구성 (?, ?, ?), (?, ?, ?), ...
        for (int i = 0; i < histories.size(); i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append("(?, ?, ?, ?)");
        }

        Query query = entityManager.createNativeQuery(sb.toString());

        // 모든 파라미터 설정
        int paramIndex = 1;
        for (StockPriceHistory history : histories) {
            query.setParameter(paramIndex++, history.getId());
            query.setParameter(paramIndex++, history.getPrice());
            query.setParameter(paramIndex++, history.getStock().getId());
            query.setParameter(paramIndex++, history.getTimestamp());
        }

        query.executeUpdate();
    }
}
