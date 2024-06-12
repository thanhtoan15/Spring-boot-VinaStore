package com.vinastore.service.impl;

import com.vinastore.entities.Reviews;
import com.vinastore.repositories.ReviewsRepositories;
import com.vinastore.service.ReviewService;
import com.vinastore.utils.ResponseBodyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewsRepositories reviewsRepositories;
    @Override
    public ResponseEntity<?> getAllReviews() {
        List<Reviews> reviews = reviewsRepositories.findAll();
        ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Get All Reviews Success")
                .payload(reviews).build();
        return ResponseEntity.status(200).body(bodyServer);
    }

    @Override
    public ResponseEntity<?> getReviewsById(Integer id) {
        if(id == null){
            ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(500).message("Hava a issue!!!")
                    .payload(null).build();
            return ResponseEntity.status(200).body(bodyServer);
        }else {
            Reviews reviews = reviewsRepositories.findById(id).orElse(null);
            if(reviews != null){
                ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Get Review By Id Successfully ")
                        .payload(reviews).build();
                return ResponseEntity.status(200).body(bodyServer);
            }else {
                ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(500).message("Review is not exit")
                        .payload(null).build();
                return ResponseEntity.status(200).body(bodyServer);
            }
        }
    }

    @Override
    public ResponseEntity<?> createReview(Reviews entity) {
        Reviews reviews = new Reviews();
        reviews.setComment(entity.getComment());
        reviews.setCreated_at(new Date());
        reviews.setAccount_id(entity.getAccount_id());
        reviews.setProduct_id(entity.getProduct_id());
        reviewsRepositories.save(reviews);
        ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Create Reviews Successfully ")
                .payload(reviews).build();
        return ResponseEntity.status(200).body(bodyServer);
    }
}
