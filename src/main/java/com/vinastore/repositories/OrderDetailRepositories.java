package com.vinastore.repositories;

import com.vinastore.dto.OrderDetailDTO;
import com.vinastore.entities.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepositories extends JpaRepository<OrderDetails,Integer> {
}
