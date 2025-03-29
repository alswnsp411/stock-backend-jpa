package com.sk.skala.stockbackend.service;

import com.sk.skala.stockbackend.domain.Player;
import com.sk.skala.stockbackend.dto.request.LoginRequest;
import com.sk.skala.stockbackend.dto.request.SignUpRequest;
import com.sk.skala.stockbackend.exception.CustomException;
import com.sk.skala.stockbackend.mapper.PlayerMapper;
import com.sk.skala.stockbackend.repository.PlayerRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;

    private final PlayerMapper playerMapper;

    private Player findByNickname(final String nickname) {
        return playerRepository.findByNickname(nickname)
                .orElseThrow(() -> new CustomException("닉네임이 존재하지 않습니다.", HttpStatus.NOT_FOUND));
    }

    /**
     * 로그인
     *
     * @param loginRequest 로그인 요청
     */
    public void login(LoginRequest loginRequest) {
        Player player= findByNickname(loginRequest.getNickname());

        if (!player.getPassword().equals(loginRequest.getPassword())) {
            throw new CustomException("비밀번호가 일치하지 않습니다.", HttpStatus.CONFLICT);
        }
    }

    /**
     * 회원가입
     *
     * @param signUpRequest 회원가입 요청
     */
    public void signUp(SignUpRequest signUpRequest) {
        Player player = playerMapper.signUpToEntity(signUpRequest);
        playerRepository.save(player);

    }
}
