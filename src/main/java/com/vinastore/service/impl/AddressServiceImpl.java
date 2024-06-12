package com.vinastore.service.impl;

import com.vinastore.entities.Address;
import com.vinastore.repositories.AddressRepositories;
import com.vinastore.service.AddressService;
import com.vinastore.utils.ResponseBodyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepositories addressRepositories;
    @Override
    public ResponseEntity<?> getAllAddress() {
        List<Address> addresses = addressRepositories.findAll();
        ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Get All Address Successfully ")
                .payload(addresses).build();
        return ResponseEntity.status(200).body(bodyServer);
    }

    @Override
    public ResponseEntity<?> getAddressById(Integer id) {
         if(id == null){
             ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Hava a issue")
                     .payload(null).build();
             return ResponseEntity.status(200).body(bodyServer);
         }else {
             List<Address> address = addressRepositories.findAddressByAccountId(id);
            if(address != null){
                ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Get Address By Id Successfully ")
                        .payload(address).build();
                return ResponseEntity.status(200).body(bodyServer);
            }else {
                ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(500).message("Address is not exit").build();
                return ResponseEntity.status(200).body(bodyServer);
            }
         }
    }

    @Override
    public ResponseEntity<?> createAddress(Address address) {
        Address address1 = addressRepositories.findAddressDefaultById(address.getAccount_id().getId());
        address1.setIs_default(address1 == null);
        addressRepositories.save(address1);
        ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Create Address Successfully ")
                .payload(address).build();
        return ResponseEntity.status(200).body(bodyServer);
    }

    @Override
    public ResponseEntity<?> updateAddress(Address address, Integer id) {
        Optional<Address> optionalAddress = addressRepositories.findById(id);
        if(optionalAddress.isPresent()){
            Address address1 = optionalAddress.get();
            address1.setFullname(address.getFullname());
            address1.setPhone(address.getPhone());
            address1.setCity(address.getCity());
            address1.setDistrict(address.getDistrict());
            address1.setWards(address.getWards());
            address1.setSpecific_address(address.getSpecific_address());
            addressRepositories.save(address1);
            ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Update Address Success")
                    .payload(address1).build();
            return ResponseEntity.status(200).body(bodyServer);
        }
        ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(500).message("Address not exit").build();
        return ResponseEntity.status(200).body(bodyServer);
    }

    @Override
    public ResponseEntity<?> deleteAddress(Integer id) {
       Optional<Address> optinalAddress = addressRepositories.findById(id);
        if(optinalAddress.isPresent()){
            Address address = optinalAddress.get();
            if(address.getIs_default()){
                ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(500).message("Can't delete default account").build();
                return ResponseEntity.status(200).body(bodyServer);
            }else {
                addressRepositories.deleteById(id);
                ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Delete address success").build();
                return ResponseEntity.status(200).body(bodyServer);
            }
        }
        ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(500).message("Address not exit").build();
        return ResponseEntity.status(200).body(bodyServer);
    }

    @Override
    public ResponseEntity<?> changeAddress(Integer id, Integer idAddress) {
        return null;
    }
}
