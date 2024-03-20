package com.sajan.pms.controller;

import com.sajan.pms.dto.UserDetails;
import com.sajan.pms.model.User;
import com.sajan.pms.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody UserDetails userDetails){
        return userService.addUser(userDetails).map(user -> new ResponseEntity<>(user, HttpStatus.CREATED))
                .orElse(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        return userService.getAllUsers().map(ResponseEntity::ok).orElseGet(() ->
                ResponseEntity.noContent().build());
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Integer userId){
        return userService.getUserById(userId).map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<User> updateUserById(@PathVariable Integer userId, @RequestBody UserDetails userDetails){
        return userService.updateUser(userId, userDetails).map(user -> ResponseEntity.ok().body(user))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable Integer userId){
        return userService.deleteUserById(userId).map(user-> ResponseEntity.ok().build())
                .orElse(ResponseEntity.notFound().build());
    }


}
