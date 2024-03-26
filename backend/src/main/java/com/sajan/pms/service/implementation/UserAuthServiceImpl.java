package com.sajan.pms.service.implementation;


import com.sajan.pms.dto.AuthenticationResponse;
import com.sajan.pms.dto.LoginRequest;
import com.sajan.pms.dto.UserDetails;
import com.sajan.pms.model.Token;
import com.sajan.pms.model.User;
import com.sajan.pms.repo.TokenRepo;
import com.sajan.pms.repo.UserRepo;
import com.sajan.pms.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAuthServiceImpl implements UserAuthService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final TokenRepo tokenRepo;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(UserDetails request){
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRole(request.getRole());
        User savedUser = userRepo.save(user);

        String jwt = jwtService.generateToken(savedUser);
        saveUserToken(jwt, savedUser);

        return new AuthenticationResponse(jwt, "User registration was successful.");
    }
    
    public AuthenticationResponse authenticate(LoginRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepo.findUserByEmail(request.getEmail()).orElseThrow();
        String jwt = jwtService.generateToken(user);

        revokeAllTokenByUser(user);
        saveUserToken(jwt, user);
        return new AuthenticationResponse(jwt, "User Login was successful");
    }


    private void revokeAllTokenByUser(User user) {
        List<Token> validTokens = tokenRepo.findAllTokensByUser(user.getUserId());
        if(validTokens.isEmpty()) {
            return;
        }

        validTokens.forEach(t-> {
            t.setLoggedOut(true);
        });

        tokenRepo.saveAll(validTokens);
    }
    private void saveUserToken(String jwt, User user) {
        Token token = new Token();
        token.setToken(jwt);
        token.setLoggedOut(false);
        token.setUser(user);
        tokenRepo.save(token);
    }

    @Override
    public boolean verifyUserNameAndPassword(String email, String password) {
        Optional<User> userByEmailAndPassword = userRepo.findUserByEmailAndPassword(email, password);
        return userByEmailAndPassword.isPresent();
    }

    @Override
    public String getPasswordByEmail(String email) {
        Optional<User> userByEmail = userRepo.findUserByEmail(email);
        return userByEmail.map(User::getPassword).orElse(null);
    }
}
