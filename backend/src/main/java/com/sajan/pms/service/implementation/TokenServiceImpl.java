package com.sajan.pms.service.implementation;

import com.sajan.pms.model.Token;
import com.sajan.pms.model.User;
import com.sajan.pms.repo.TokenRepo;
import com.sajan.pms.repo.UserRepo;
import com.sajan.pms.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final TokenRepo tokenRepo;
    private final UserRepo userRepo;
    @Override
    public Optional<Token> tokenExists(Integer userId) {

        return Optional.empty();
    }
}
