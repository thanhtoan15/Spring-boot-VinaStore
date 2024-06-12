package com.vinastore.repositories;

import com.vinastore.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepositories extends JpaRepository<Address, Integer> {

    @Query("select o from Address o where o.account_id.id = :id and o.account_id.deleted_at = null")
    List<Address> findAddressByAccountId(Integer id);

    @Query("select o from Address o where o.is_default = TRUE and o.account_id.id = :id")
    Address findAddressDefaultById(Integer id);
}
