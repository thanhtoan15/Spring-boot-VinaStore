package com.vinastore.repositories;

import com.vinastore.dto.OrderAccountStatisticalDTO;
import com.vinastore.dto.OrderProductStatisticalDTO;
import com.vinastore.dto.OrderStatusStatisticalDTO;
import com.vinastore.dto.OrderYearStatisticalDTO;
import com.vinastore.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepositories extends JpaRepository<Orders,Integer> {
    @Query("select o from Orders o where o.status_id.id = :id")
    List<Orders> getOrdersByStatus(Integer id);

    @Query("select o from Orders o where o.account_id.id = :id")
    List<Orders> getOrderByAccount(Integer id);

    @Query("select o from Orders o where o.account_id.id = :id and o.status_id.id = :status")
    List<Orders> getAllOrderByIdAccountAndIdStatus(Integer id,String status);

    @Query("SELECT new com.vinastore.dto.OrderStatusStatisticalDTO(b.id, b.status, COUNT(a.id)) " +
            "FROM Orders a RIGHT JOIN OrderStatus b ON a.status_id.id = b.id " +
            "GROUP BY b.id, b.status")
    List<OrderStatusStatisticalDTO> getOrderByStatusAndCount();

    @Query("SELECT new com.vinastore.dto.OrderYearStatisticalDTO(MONTH(a.created_at), SUM(a.total_amount)) " +
            "FROM Orders a WHERE YEAR(a.created_at) = :year GROUP BY MONTH(a.created_at)")
    List<OrderYearStatisticalDTO> getAllOrderByYear(@Param("year") String year);

    @Query("SELECT new com.vinastore.dto.OrderProductStatisticalDTO(a.id, a.name_product, a.price, a.img, a.quantity, a.category_id, SUM(b.quantity)) " +
            "FROM Products a INNER JOIN OrderDetails b ON a.id = b.product_id.id " +
            "GROUP BY a.id, a.name_product, a.price, a.img, a.quantity, a.category_id " +
            "ORDER BY COUNT(b.quantity) DESC")
    List<OrderProductStatisticalDTO> getTopProduct();

    @Query("SELECT new com.vinastore.dto.OrderAccountStatisticalDTO(a.id, a.email, a.fullname, a.img, SUM(b.total_amount)) " +
            "FROM Accounts a INNER JOIN Orders b ON a.id = b.account_id.id " +
            "GROUP BY a.id, a.email, a.fullname, a.img " +
            "ORDER BY SUM(b.total_amount) DESC")
    List<OrderAccountStatisticalDTO> getTopAccount();
}
