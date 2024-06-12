package com.vinastore.apis;

import com.vinastore.entities.Discounts;
import com.vinastore.service.DiscountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("public/api/v1/discounts")
public class DiscountsApi {

    @Autowired
    private DiscountsService discountsService;

    @GetMapping
    public ResponseEntity<?> getAllDisscount(){
        ResponseEntity<?> discounts = discountsService.getAllDiscount();
        return ResponseEntity.status(200).body(discounts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDiscountsById(@PathVariable("id") Integer id){
        ResponseEntity<?> discount = discountsService.getDiscountById(id);
        return ResponseEntity.status(200).body(discount);
    }

    @GetMapping("/name-discount")
    public ResponseEntity<?> getDiscountById(@RequestParam("name")String name){
        ResponseEntity<?> discount = discountsService.getDiscountByName(name);
        return ResponseEntity.status(200).body(discount);
    }

    @PostMapping()
    public ResponseEntity<?> createDiscount(@RequestBody Discounts entity){
        ResponseEntity discount = discountsService.createDiscounts(entity);
        return ResponseEntity.status(200).body(discount);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDiscountById(@RequestBody Discounts enityt,
                                                @PathVariable("id")Integer id){
        ResponseEntity discount = discountsService.updateDiscounts(enityt, id);
        return ResponseEntity.status(200).body(discount);
    }


}
