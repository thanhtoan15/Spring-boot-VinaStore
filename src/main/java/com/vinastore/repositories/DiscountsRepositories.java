package com.vinastore.repositories;

import com.vinastore.entities.Discounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountsRepositories extends JpaRepository<Discounts, Integer> {

    @Query("select o from Discounts o where o.name like '%' + :name + '%'")
    List<Discounts> getAllDiscountsByName(String name);
}
