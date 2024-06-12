package com.vinastore.service;

import com.vinastore.entities.Reviews;
import org.springframework.http.ResponseEntity;

public interface ReviewService {

    ResponseEntity<?> getAllReviews();

    ResponseEntity<?> getReviewsById(Integer id);

    ResponseEntity<?> createReview(Reviews entity);
}
