package com.vinastore.service;

import com.vinastore.entities.Accounts;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AccountsService {

    ResponseEntity<?> getAllAccount();

    Accounts login(String email, String password);

    Accounts register(String email, String username, String password);

    ResponseEntity<?> getAccountById(Integer id);

    ResponseEntity<?> updateAccounts(Accounts entity,Integer id);

    ResponseEntity<?> deleteAccountsById(Integer id);
}
