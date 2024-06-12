package com.vinastore.apis;

import com.vinastore.entities.Reviews;
import com.vinastore.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("public/api/v1/reviews")
public class ReviewsApi {

    @Autowired
    private ReviewService reviewService;

    @GetMapping()
    public ResponseEntity<?> getAllreviews(){
        ResponseEntity<?> reviews = reviewService.getAllReviews();
        return ResponseEntity.status(200).body(reviews);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReviewsById(@PathVariable("id")Integer id){
        ResponseEntity<?> reviews = reviewService.getReviewsById(id);
        return ResponseEntity.status(200).body(reviews);
    }

    @PostMapping()
    public ResponseEntity<?> createReviews(@RequestBody Reviews entity){
        ResponseEntity<?> reviews = reviewService.createReview(entity);
        return ResponseEntity.status(200).body(reviews);
    }
}
