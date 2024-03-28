package com.sajan.pms.controller;

import com.sajan.pms.model.Address;
import com.sajan.pms.service.implementation.AddressServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressServiceImpl addressService;

    @PostMapping("/add")
     public ResponseEntity<Address> addAddress(@RequestBody Address addressRequest){
         return addressService.addAddress(addressRequest).map(ResponseEntity::ok)
                 .orElseGet(() -> ResponseEntity.badRequest().build());
     }
     @GetMapping("/all")
    public ResponseEntity<List<Address>> getAllAddresses(){
        return addressService.getAllAddresses().map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
     }
     @GetMapping("/{addressId}")
    public ResponseEntity<Address> getAddressById(@PathVariable Integer addressId){
        return addressService.getAddressById(addressId).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
     }
     @PutMapping("/{addressId}")
    public ResponseEntity<Address> updateAddressById(@PathVariable Integer addressId, @RequestBody Address addressRequest){
        System.out.println(addressRequest);
        return addressService.updateAddressById(addressId, addressRequest).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
     }
     @DeleteMapping("/{addressId}")
    public ResponseEntity<Address> deleteAddressById(@PathVariable Integer addressId){
        return addressService.deleteAddressById(addressId).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
     }
}
