package com.sk.skala.stockbackend.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "stocks")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Stock {

    @Id
    @Column(name = "stock_id")
    private UUID id;

    @Column(nullable = false)
    private String name;        // 주식 이름

    @Column(nullable = false)
    private int price;       // 현재 주식 가격

    @Column(length = 1000)
    private String description; // 주식 설명

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL)
    private List<StockPriceHistory> priceHistories;  //주식 이력

    //== 비즈니스 로직 ==//

    /**
     * 주식 가격 업데이트
     *
     * @param newPrice 변동된 가격
     */
    public void changePrice(int newPrice) {
        this.price = newPrice;
    }
}
