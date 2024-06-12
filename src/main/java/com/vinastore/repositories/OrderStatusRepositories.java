package com.vinastore.repositories;

import com.vinastore.entities.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepositories extends JpaRepository<OrderStatus,Integer> {
}
