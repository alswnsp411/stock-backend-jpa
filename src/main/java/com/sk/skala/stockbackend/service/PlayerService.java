package com.sk.skala.stockbackend.service;

import com.sk.skala.stockbackend.domain.Player;
import com.sk.skala.stockbackend.domain.PlayerStock;
import com.sk.skala.stockbackend.domain.Stock;
import com.sk.skala.stockbackend.dto.request.BuyPlayerStockRequest;
import com.sk.skala.stockbackend.dto.request.LoginRequest;
import com.sk.skala.stockbackend.dto.request.SellPlayerStockRequest;
import com.sk.skala.stockbackend.dto.request.SignUpRequest;
import com.sk.skala.stockbackend.dto.response.PlayerResponse;
import com.sk.skala.stockbackend.dto.response.PlayerStockListResponse;
import com.sk.skala.stockbackend.dto.response.PlayerStockResponse;
import com.sk.skala.stockbackend.exception.CustomException;
import com.sk.skala.stockbackend.mapper.PlayerMapper;
import com.sk.skala.stockbackend.mapper.PlayerStockMapper;
import com.sk.skala.stockbackend.repository.PlayerRepository;
import com.sk.skala.stockbackend.repository.PlayerStockRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerMapper playerMapper;
    private final PlayerStockMapper playerStockMapper;

    private final PlayerRepository playerRepository;
    private final PlayerStockRepository playerStockRepository;

    private final StockService stockService;
    private final PlayerStockService playerStockService;

    private Player findByNickname(final String nickname) {
        return playerRepository.findByNickname(nickname)
                .orElseThrow(() -> new CustomException("플레이어가 존재하지 않습니다.", HttpStatus.NOT_FOUND));
    }

    private Player findByIdWithStocks(final UUID playerId) {
        return playerRepository.findByIdWithStocks(playerId)
                .orElseThrow(() -> new CustomException("플레이어가 존재하지 않습니다.", HttpStatus.NOT_FOUND));
    }

    //플레이어의 보유 주식 목록에서 stock을 찾음
    private PlayerStock findPlayerStock(final Player player, final Stock stock) {
        return player.getPlayerStockList().stream()
                .filter(ps -> ps.getStock().getId().equals(stock.getId()))
                .findFirst()
                .orElse(null);
    }

    /**
     * 로그인
     *
     * @param loginRequest 로그인 요청
     */
    public void login(final LoginRequest loginRequest) {
        Player player = findByNickname(loginRequest.getNickname());

        if (!player.getPassword().equals(loginRequest.getPassword())) {
            throw new CustomException("비밀번호가 일치하지 않습니다.", HttpStatus.CONFLICT);
        }
    }

    /**
     * 회원가입
     *
     * @param signUpRequest 회원가입 요청
     */
    @Transactional
    public PlayerResponse signUp(final SignUpRequest signUpRequest) {
        if (playerRepository.findByNickname(signUpRequest.getNickname()).isPresent()) {
            throw new CustomException("이미 존재하는 닉네임입니다.", HttpStatus.CONFLICT);
        }

        Player player = playerMapper.signUpToEntity(signUpRequest);
        player = playerRepository.save(player);

        return playerMapper.toResponse(player);
    }

    /**
     * 플레이어 보유 주식 목록 확인
     *
     * @param playerId 플레이어 pk
     * @return 플레이어 보유 주식 목록 리스트 response 객체
     */
    public List<PlayerStockListResponse> getPlayerStockList(UUID playerId) {
        Player player = findByIdWithStocks(playerId);
        if (player.getPlayerStockList().isEmpty()) {
            return null;
        }

        return player.getPlayerStockList().stream().map(playerStockMapper::toResponseForList).toList();
    }

    /**
     * 주식 매수
     *
     * @param request 주식 구매 요청 객체
     * @return
     */
    @Transactional
    public PlayerStockResponse buyStock(BuyPlayerStockRequest request) {
        Player player = findByIdWithStocks(request.getPlayerId());  // 주식 구매 플레이어
        Stock stock = stockService.findById(request.getStockId());  // 구매 대상 주식

        int totalInvestment = playerStockService.calculation(stock.getPrice(), request.getStockQuantity());
        if (player.getMoney() < totalInvestment) {
            throw new CustomException("잔액이 부족합니다.", HttpStatus.CONFLICT);
        }

        // 플레이어의 보유 주식 목록에서 해당 주식을 찾음
        PlayerStock playerStock = findPlayerStock(player, stock);

        if (playerStock == null) { // 새로 구매하는 경우
            playerStock = playerStockMapper.toEntity(player, stock, request.getStockQuantity(), totalInvestment);
            player.getPlayerStockList().add(playerStock);
        } else {  // 기존 보유 주식 수량 증가
            playerStock.addQuantity(request.getStockQuantity(), totalInvestment);
        }

        // 플레이어 보유 돈 차감
        player.subtractMoney(totalInvestment);

        return playerStockMapper.toResponse(player, request.getStockQuantity(), playerStock);
    }

    /**
     * 주식 매도
     *
     * @param request 주식 구매 요청 객체
     * @return
     */
    @Transactional
    public PlayerStockResponse sellPlayerStock(SellPlayerStockRequest request) {
        Player player = findByIdWithStocks(request.getPlayerId());  // 주식 구매 플레이어
        Stock stock = stockService.findById(request.getStockId());  // 판매 대상 주식

        PlayerStock playerStock = findPlayerStock(player, stock);
        if (playerStock == null) {
            throw new CustomException("보유중인 주식이 아닙니다.", HttpStatus.NOT_FOUND);
        }
        if (playerStock.getQuantity() < request.getStockQuantity()) {
            throw new CustomException("매도 수량이 보유 수량보다 많습니다.", HttpStatus.BAD_REQUEST);
        }

        int totalPrice = playerStockService.calculation(stock.getPrice(), request.getStockQuantity());

        // 보유 주식 수량 차감 또는 삭제
        if (playerStock.getQuantity() == request.getStockQuantity()) {
            player.getPlayerStockList().remove(playerStock);  // 보유 주식에서 제거
        } else {
            playerStock.subtractQuantity(request.getStockQuantity(), totalPrice);  // 수량만 감소
        }
        // 돈 증가
        player.addMoney(totalPrice);

        return playerStockMapper.toResponse(player, request.getStockQuantity(), playerStock);
    }

}
