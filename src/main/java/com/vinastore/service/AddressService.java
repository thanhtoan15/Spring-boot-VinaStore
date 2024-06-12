package com.vinastore.service;

import com.vinastore.entities.Address;
import org.springframework.http.ResponseEntity;

public interface AddressService {

    ResponseEntity<?> getAllAddress();

    ResponseEntity<?> getAddressById(Integer id);

    ResponseEntity<?> createAddress(Address address);

    ResponseEntity<?> updateAddress(Address address, Integer id);

    ResponseEntity<?> deleteAddress(Integer id);

    ResponseEntity<?> changeAddress(Integer id, Integer idAddress);
}
