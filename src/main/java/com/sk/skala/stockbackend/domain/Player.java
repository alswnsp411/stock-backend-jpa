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
@Table(name = "players")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Player {

    @Id
    @Column(name = "player_id")
    private UUID id;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private int money;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlayerStock> playerStockList;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private List<PlayerStockHistory> stockHistories;


    //== 비즈니스 로직 ==//
    public void subtractMoney(int totalPrice) {
        this.money -= totalPrice;
    }

    public void addMoney(int totalPrice) {
        this.money += totalPrice;
    }
}
