package com.sk.skala.stockbackend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "player_stocks")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PlayerStock {

    @Id
    @Column(name = "player_stock_id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @Column(nullable = false)
    private int quantity;           // 보유 수량

    @Column(nullable = false)
    private int profitRate;          // 수익률

    @Column(nullable = false)
    private int totalInvestment;     // 총 투자금액

//    @Column(nullable = false)
//    private Double currentValue;        // 현재 보유 가치

//    @Column(nullable = false)
//    private Double averagePrice;        // 평균 매수가

    //== 비즈니스 로직 ==//

    public void addQuantity(int stockQuantity, int totalInvestment) {
        this.quantity += stockQuantity;
        //수익률 계산 로직 추가
        this.totalInvestment += totalInvestment;
    }

    public void subtractQuantity(int stockQuantity, int totalPrice) {
        this.quantity -= stockQuantity;
        //수익률 계산 로직 추가
        this.totalInvestment -= totalPrice;
    }
}
