package com.sk.skala.stockbackend.repository;

import com.sk.skala.stockbackend.domain.Player;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, UUID> {
    Optional<Player> findByNickname(String nickname);

    @Query("SELECT p FROM Player p LEFT JOIN FETCH p.playerStockList WHERE p.id = :playerId")
    Optional<Player> findByIdWithStocks(@Param("playerId") UUID playerId);

    Player save(Player player);

    Page<Player> findAll(Pageable pageable);

}
