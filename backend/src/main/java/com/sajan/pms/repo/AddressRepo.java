package com.sajan.pms.repo;

import com.sajan.pms.model.Address;
import com.sajan.pms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepo extends JpaRepository<Address, Integer> {

    Optional<Address> findAddressesByUserId(Integer userId);
}
