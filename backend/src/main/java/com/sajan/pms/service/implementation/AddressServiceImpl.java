package com.sajan.pms.service.implementation;

import com.sajan.pms.model.Address;
import com.sajan.pms.repo.AddressRepo;
import com.sajan.pms.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepo addressRepo;

    @Override
    public Optional<Address> addAddress(Address addressRequest) {
        if(addressRequest != null){
            Address newAddress = new Address();
            BeanUtils.copyProperties(addressRequest, newAddress);
            addressRepo.save(newAddress);
            return Optional.of(newAddress);
        } else return Optional.empty();
    }

    @Override
    public Optional<List<Address>> getAllAddresses() {
        List<Address> addressList = addressRepo.findAll();
        return Optional.of(addressList);
    }

    @Override
    public Optional<Address> getAddressById(Integer addressId) {
        return addressRepo.findById(addressId);
    }

    @Override
    public Optional<Address> updateAddressById(Integer addressId, Address addressRequest) {
        Optional<Address> byId = addressRepo.findById(addressId);
        if(byId.isPresent()){
            Address updateAddress = byId.get();
            BeanUtils.copyProperties(addressRequest, updateAddress, "addressId");
            addressRepo.save(updateAddress);
            return Optional.of(updateAddress);
        } else return Optional.empty();
    }

    @Override
    public Optional<Address> deleteAddressById(Integer addressId) {

        Optional<Address> byId = addressRepo.findById(addressId);
        if(byId.isPresent()){
            addressRepo.delete(byId.get());
            return byId;
        } else return Optional.empty();
    }
}
