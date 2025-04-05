package com.sk.skala.stockbackend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "stock_price_histories")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class StockPriceHistory {

    @Id
    @Column(name = "stock_price_history_id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private LocalDateTime timestamp;
} 