package com.sajan.pms.service;

import com.sajan.pms.model.Token;

import java.util.Optional;

public interface TokenService {
    Optional<Token> tokenExists(Integer userId);

}
