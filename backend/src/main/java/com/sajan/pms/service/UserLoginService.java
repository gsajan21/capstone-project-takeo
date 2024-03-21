package com.sajan.pms.service;

import org.springframework.stereotype.Service;

public interface UserLoginService {

    boolean verifyUserNameAndPassword(String email, String password);
    String getPasswordByEmail(String email);
}
