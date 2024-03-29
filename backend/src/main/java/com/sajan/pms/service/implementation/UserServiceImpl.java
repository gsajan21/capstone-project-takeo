package com.sajan.pms.service.implementation;

import com.sajan.pms.dto.UserRequest;
import com.sajan.pms.model.User;
import com.sajan.pms.repo.UserRepo;
import com.sajan.pms.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

//    @Override
//    public Optional<User> addUser(UserDetails userDetails) {
//        User newUser = new User();
//        BeanUtils.copyProperties(userDetails, newUser);
//        User savedUser = userRepo.save(newUser);
//
//        // Check if the user was successfully saved
//        if (savedUser.getUserId() != null) {
//            // If saved successfully, return an Optional containing the saved user
//            return Optional.of(savedUser);
//        } else {
//            // If not saved successfully, return an empty Optional
//            return Optional.empty();
//        }
//    }

    @Override
    public Optional<User> getUserById(Integer userId) {
        return userRepo.getUserByUserId(userId);
    }

    @Override
    public Optional<List<User>> getAllUsers() {
        List<User> allUsers = userRepo.findAll();
        return Optional.of(allUsers);
    }

    @Override
    public Optional<User> updateUser(Integer userId, UserRequest userRequest) {
        Optional<User> userByUserId = userRepo.getUserByUserId(userId);

        if(userByUserId.isPresent()) {
            User userToUpdate = userByUserId.get();

            BeanUtils.copyProperties(userRequest, userToUpdate);
            return Optional.of(userRepo.save(userToUpdate));
        } else
            return Optional.empty();

    }

    @Override
    public Optional<User> deleteUserById(Integer userId) {
//        Optional<User> userToDelete = userRepo.getUserByUserId(userId);
//        if(userToDelete.isPresent()) {
//            userRepo.deleteUserByUserId(userId);
//            return userToDelete;
//        }
//        else
//            return Optional.empty();

        Optional<User> userToDelete = userRepo.getUserByUserId(userId);
        if(userToDelete.isPresent()){
            User user = userToDelete.get();
            userRepo.delete(user);
            return userToDelete;
        } else
            return Optional.empty();
    }

}
