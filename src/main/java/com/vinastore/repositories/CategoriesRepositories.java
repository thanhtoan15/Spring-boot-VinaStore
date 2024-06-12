package com.vinastore.repositories;

import com.vinastore.entities.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriesRepositories extends JpaRepository<Categories, Integer> {

    @Query("select o from Categories o where o.deleted_at is null")
    List<Categories> getAllCategories();

    @Query("select o from Categories o where  o.id= :id and o.deleted_at is null ")
    Categories getCategoriesById(Integer id);
}
