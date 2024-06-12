package com.vinastore.repositories;

import com.vinastore.entities.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepositories extends JpaRepository<Products, Integer> {

    @Query("select o from Products o where o.category_id.id = :id")
    List<Products> getAllProductByCategories(Integer id);

    @Query("SELECT o FROM Products o WHERE LOWER(o.name_product) LIKE '%' + :name_product + '%' AND o.deleted_at IS NULL")
    List<Products> findProductByName(String name_product);

    @Query("SELECT p FROM Products p WHERE p.price BETWEEN :priceMin AND :priceMax ORDER BY p.price ASC")
    List<Products> getProductByPrice(Double priceMin, Double priceMax);

    @Query("SELECT p FROM Products p WHERE " +
            "(:name IS NULL OR LOWER(p.name_product) LIKE %:name%) " +
            "AND (:category IS NULL OR p.category_id.name = :category) " +
            "AND (:priceMin IS NULL OR p.price >= :priceMin) " +
            "AND (:priceMax IS NULL OR p.price <= :priceMax)")
    Page<Products>findFiltedProduct(
            @Param("name") String name,
            @Param("category") String category,
            @Param("priceMin") Integer priceMin,
            @Param("priceMax") Integer priceMax,
            Pageable pageable);
}
