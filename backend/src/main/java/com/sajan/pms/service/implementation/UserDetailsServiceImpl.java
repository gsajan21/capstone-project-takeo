package com.sajan.pms.service.implementation;

import com.sajan.pms.model.User;
import com.sajan.pms.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findUserByEmail(username).orElseThrow( () ->
                new UsernameNotFoundException("User not found"));
    }

//    public UserDetailsService userDetailsService(){
//        return new UserDetailsService(){
//            @Override
//            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//                return userRepo.findUserByEmail(username).orElseThrow( () ->
//                        new UsernameNotFoundException("User not found"));
//            }
//        };
//    }
//
//    public User save(User newUser){
//        if(newUser.getUserId() == null){
//            newUser.setCreatedAt(LocalDateTime.now());
//        }
//        newUser.setUpdatedAt(LocalDateTime.now());
//        return userRepo.save(newUser);
//    }

}
