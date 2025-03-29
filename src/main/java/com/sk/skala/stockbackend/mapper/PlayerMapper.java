package com.sk.skala.stockbackend.mapper;

import com.sk.skala.stockbackend.domain.Player;
import com.sk.skala.stockbackend.dto.request.SignUpRequest;
import java.util.ArrayList;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PlayerMapper {

    public Player signUpToEntity(SignUpRequest signUpRequest) {
        return new Player(
                UUID.randomUUID(),
                signUpRequest.getNickname(),
                signUpRequest.getPassword(),
                signUpRequest.getMoney(),
                new ArrayList<>()
        );
    }
}
