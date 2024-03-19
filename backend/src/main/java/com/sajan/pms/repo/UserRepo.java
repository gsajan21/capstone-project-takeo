package com.sajan.pms.repo;

import com.sajan.pms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> deleteUserByUserId(Integer userId);
    Optional<User> getUserByUserId(Integer userId);

}
