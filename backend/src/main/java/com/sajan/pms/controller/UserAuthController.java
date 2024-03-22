package com.sajan.pms.controller;

import com.sajan.pms.dto.AuthenticationResponse;
import com.sajan.pms.dto.ForgotPasswordRequest;
import com.sajan.pms.dto.UserDetails;
import com.sajan.pms.service.implementation.UserAuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/user")
public class UserAuthController {

    private final UserAuthServiceImpl userAuthService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> userRegister(@RequestBody UserDetails request){
        System.out.println(request.getFirstName());
        return ResponseEntity.ok(userAuthService.register(request));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> userLogin(@RequestBody UserDetails request){
       return ResponseEntity.ok(userAuthService.authenticate(request));

    }
    @PostMapping("/forgot-password")
    public ResponseEntity<String> userForgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest){
        String email = forgotPasswordRequest.getEmail();
        String password = userAuthService.getPasswordByEmail(email);
        if (password != null) {
            return ResponseEntity.ok("Your password is: " + password);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Password not found for the provided email.");
        }
    }
}
