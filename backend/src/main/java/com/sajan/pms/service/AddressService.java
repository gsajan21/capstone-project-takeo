package com.sajan.pms.service;

import com.sajan.pms.model.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {

    Optional<Address> addAddress(Address address);
    Optional<List<Address>> getAllAddresses();
    Optional<Address> getAddressById(Integer addressId);
    Optional<Address> updateAddressById(Integer addressId, Address address);
    Optional<Address> deleteAddressById(Integer addressId);


}
