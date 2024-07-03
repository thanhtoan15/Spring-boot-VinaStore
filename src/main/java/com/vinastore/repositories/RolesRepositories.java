package com.vinastore.repositories;

import com.vinastore.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepositories extends JpaRepository<Roles, String> {

    @Query("SELECT o FROM Roles o WHERE o.name = :name")
    Roles findByName(String name);
}
