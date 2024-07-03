package com.vinastore.repositories;

import com.vinastore.entities.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepositories extends JpaRepository<Accounts, Integer> {

    @Query("SELECT o FROM Accounts o WHERE o.email = :email and o.password = :password AND o.deleted_at IS NULL")
    Accounts getAccountsByEmailAndPassword(String email,String password);

    @Query("SELECT o FROM Accounts o WHERE o.email = :email and o.deleted_at IS NULL")
    Optional<Accounts> findByEmail(String email);

    @Query("select o from Accounts o where o.id = :id and o.password = :password AND o.deleted_at IS NULL")
    Accounts findAccountByIdAndPassword(Integer id, String password);

}
