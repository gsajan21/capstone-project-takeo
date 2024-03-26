package com.sajan.pms.controller;

import com.sajan.pms.dto.ForgotPasswordRequest;
import com.sajan.pms.dto.LoginRequest;
import com.sajan.pms.dto.UserDetails;
import com.sajan.pms.model.User;
import com.sajan.pms.service.UserLoginServiceImpl;
import com.sajan.pms.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserLoginController {

    private final UserLoginServiceImpl userLoginService;
    private final UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity<User> userRegister(@RequestBody UserDetails userDetails){
        return userService.addUser(userDetails).map(user -> new ResponseEntity<>(user, HttpStatus.CREATED))
                .orElse(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
    @PostMapping("/login")
    public ResponseEntity<String> userLogin(@RequestBody LoginRequest loginRequest){
        boolean isAuthenticated = userLoginService.verifyUserNameAndPassword(loginRequest.getEmail(), loginRequest.getPassword());

        if(isAuthenticated)
            return ResponseEntity.ok("Login successful.");
        else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");

    }
    @PostMapping("/forgot-password")
    public ResponseEntity<String> userForgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest){
        String email = forgotPasswordRequest.getEmail();
        String password = userLoginService.getPasswordByEmail(email);
        if (password != null) {
            return ResponseEntity.ok("Your password is: " + password);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Password not found for the provided email.");
        }
    }
}
