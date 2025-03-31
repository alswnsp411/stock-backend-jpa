package com.sk.skala.stockbackend.mapper;

import com.sk.skala.stockbackend.domain.Player;
import com.sk.skala.stockbackend.dto.request.SignUpRequest;
import com.sk.skala.stockbackend.dto.response.PlayerResponse;
import java.util.ArrayList;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {

    public Player signUpToEntity(final SignUpRequest signUpRequest) {
        return new Player(
                UUID.randomUUID(),
                signUpRequest.getNickname(),
                signUpRequest.getPassword(),
                signUpRequest.getMoney(),
                new ArrayList<>(),
                new ArrayList<>()
        );
    }

    public PlayerResponse toResponse(final Player player) {
        return new PlayerResponse(
                player.getNickname(),
                player.getMoney());
    }
}
