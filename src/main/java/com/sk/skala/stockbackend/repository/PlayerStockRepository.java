package com.sk.skala.stockbackend.repository;

import com.sk.skala.stockbackend.domain.PlayerStock;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerStockRepository extends JpaRepository<PlayerStock, UUID> {

}
