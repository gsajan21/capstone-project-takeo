package com.sajan.pms.service;


import com.sajan.pms.model.User;
import com.sajan.pms.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserLoginServiceImpl implements UserLoginService {

    private final UserRepo userRepo;

    public UserLoginServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
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
