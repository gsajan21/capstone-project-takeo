package com.sajan.pms.service;

public interface UserAuthService {

    boolean verifyUserNameAndPassword(String email, String password);
    String getPasswordByEmail(String email);
}
