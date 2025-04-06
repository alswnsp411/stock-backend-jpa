package com.sk.skala.stockbackend.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.Map;
import java.util.UUID;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public class StockPriceBatchRepositoryImpl implements StockPriceBatchRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void updatePricesAll(Map<UUID, Integer> stockPriceUpdates) {
        if (stockPriceUpdates.isEmpty()) {
            return;
        }

        // CASE WHEN 구문을 사용한 단일 UPDATE 쿼리 구성
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE stocks SET price = CASE stock_id ");

        for (Map.Entry<UUID, Integer> entry : stockPriceUpdates.entrySet()) {
            sb.append("WHEN '").append(entry.getKey()).append("' THEN ").append(entry.getValue()).append(" ");
        }

        sb.append("ELSE price END WHERE stock_id IN (");

        boolean first = true;
        for (UUID id : stockPriceUpdates.keySet()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append("'").append(id).append("'");
            first = false;
        }

        sb.append(")");

        // 쿼리 실행
        Query query = entityManager.createNativeQuery(sb.toString());
        query.executeUpdate();
    }

}
