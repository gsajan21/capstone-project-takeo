package com.sajan.pms.controller;

import com.sajan.pms.dto.UserRequest;
import com.sajan.pms.model.User;
import com.sajan.pms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserApiController {

    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(){
        return userService.getAllUsers().map(ResponseEntity::ok).orElseGet(() ->
                ResponseEntity.noContent().build());
    }
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Integer userId){
        return userService.getUserById(userId).map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUserById(@PathVariable Integer userId, @RequestBody UserRequest userRequest){
        return userService.updateUser(userId, userRequest).map(user -> ResponseEntity.ok().body(user))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable Integer userId){
        return userService.deleteUserById(userId).map(user-> ResponseEntity.ok().build())
                .orElse(ResponseEntity.notFound().build());
    }
//    @PostMapping("/forgot-password")
//    public ResponseEntity<String> userForgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest){
//        String email = forgotPasswordRequest.getEmail();
//        String password = .getPasswordByEmail(email);
//        if (password != null) {
//            return ResponseEntity.ok("Your password is: " + password);
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Password not found for the provided email.");
//        }
//    }


}
