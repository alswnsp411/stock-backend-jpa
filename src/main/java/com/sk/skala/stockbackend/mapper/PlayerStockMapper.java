package com.sk.skala.stockbackend.mapper;

import com.sk.skala.stockbackend.domain.Player;
import com.sk.skala.stockbackend.domain.PlayerStock;
import com.sk.skala.stockbackend.domain.Stock;
import com.sk.skala.stockbackend.dto.response.PlayerStockListResponse;
import com.sk.skala.stockbackend.dto.response.PlayerStockResponse;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class PlayerStockMapper {

    public PlayerStock toEntity(final Player player, final Stock stock, final int stockQuantity,
                                final int totalInvestment) {
        return new PlayerStock(
                UUID.randomUUID(),
                player,
                stock,
                stockQuantity,
                0,
                totalInvestment
        );
    }

    public PlayerStockResponse toResponse(final Player player, final int stockQueantity,
                                          final PlayerStock playerStock) {
        return new PlayerStockResponse(
                player.getNickname(),
                player.getMoney(),
                playerStock.getStock().getName(),
                playerStock.getStock().getPrice(),
                stockQueantity,
                playerStock.getQuantity()
        );
    }

    public PlayerStockListResponse toResponseForList(final PlayerStock playerStock) {
        return new PlayerStockListResponse(
                playerStock.getStock().getName(),
                playerStock.getStock().getPrice(),
                playerStock.getQuantity(),
                playerStock.getProfitRate(),
                playerStock.getTotalInvestment()
        );
    }
}
