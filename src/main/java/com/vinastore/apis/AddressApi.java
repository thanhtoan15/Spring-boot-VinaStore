package com.vinastore.apis;

import com.vinastore.entities.Address;
import com.vinastore.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("public/api/v1/address")
public class AddressApi {

    @Autowired
    private AddressService addressService;

    @GetMapping
    public ResponseEntity<?> getAllAddress(){
        ResponseEntity<?> result = addressService.getAllAddress();
        return ResponseEntity.status(200).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAddressById(@PathVariable("id") Integer id){
        ResponseEntity<?> address = addressService.getAddressById(id);
        return ResponseEntity.status(200).body(address);
    }

    @PostMapping()
    public ResponseEntity<?> createAddress(@RequestBody Address addressEntity){
        ResponseEntity<?> address = addressService.createAddress(addressEntity);
        return ResponseEntity.status(200).body(address);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAddress(@PathVariable("id")Integer id,
                                           @RequestBody Address addressEntity){
        ResponseEntity<?> address = addressService.updateAddress(addressEntity,id);
        return ResponseEntity.status(200).body(address);
    }

    @PutMapping("/account/{id}/{idaddress}")    public ResponseEntity<?> changeDefault(@PathVariable("id")Integer id,
                                           @PathVariable("id")Integer idaddress){
        ResponseEntity<?> address = addressService.changeAddress(id,idaddress);
        return ResponseEntity.status(200).body(address);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable("id")Integer id){
        ResponseEntity<?> address = addressService.deleteAddress(id);
        return ResponseEntity.status(200).body(address);
    }
}
