package com.vinastore.repositories;

import com.vinastore.entities.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewsRepositories extends JpaRepository<Reviews,Integer> {
}
