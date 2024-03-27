package com.sajan.pms.controller;

import com.sajan.pms.dto.AuthenticationResponse;
import com.sajan.pms.dto.LoginRequest;
import com.sajan.pms.dto.UserRequest;
import com.sajan.pms.service.implementation.UserAuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/v1/auth")
public class UserAuthController {

    private final UserAuthServiceImpl userAuthService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> userRegister(@RequestBody UserRequest request){
        System.out.println(request.getFirstName());
        return ResponseEntity.ok(userAuthService.register(request));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> userLogin(@RequestBody LoginRequest request){
       return ResponseEntity.ok(userAuthService.authenticate(request));

    }
}
