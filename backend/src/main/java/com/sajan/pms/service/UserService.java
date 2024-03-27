package com.sajan.pms.service;

import com.sajan.pms.dto.UserRequest;
import com.sajan.pms.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
//    Optional<User> addUser(UserDetails userDetails);
    Optional<User> getUserById(Integer userId);
    Optional<List<User>> getAllUsers();
    Optional<User> updateUser(Integer userId, UserRequest userRequest);
    Optional<User> deleteUserById(Integer userId);

}
