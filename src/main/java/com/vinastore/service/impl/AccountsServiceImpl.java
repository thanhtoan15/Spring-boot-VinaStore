package com.vinastore.service.impl;

import com.vinastore.entities.Accounts;
import com.vinastore.entities.Products;
import com.vinastore.repositories.AccountsRepositories;
import com.vinastore.service.AccountsService;
import com.vinastore.service.RolesService;
import com.vinastore.utils.PasswordUtils;
import com.vinastore.utils.ResponseBodyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AccountsServiceImpl implements AccountsService {

    @Autowired
    private AccountsRepositories accountsRepositories;

    @Autowired
    private RolesService rolesService;
    @Override
    public ResponseEntity<?> getAllAccount() {
        List<Accounts> accounts = accountsRepositories.findAll();
        ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Get All Accounts Successfully ")
                .payload(accounts).build();
        return ResponseEntity.status(200).body(bodyServer);
    }

    @Override
    public Accounts login(String email, String password) {
        return accountsRepositories.getAccountsByEmailAndPassword(email, password);
    }

    @Override
    public Accounts register(String email, String username, String password) {
        Accounts accounts = new Accounts();
        accounts.setEmail(email);
        accounts.setUsername(username);
        String hashPassword = PasswordUtils.hashPassword(password);
        accounts.setPassword(hashPassword);

        accounts.setCreated_at(new Date());

        accounts.setRole_id(rolesService.findRoleById("user"));
        return accountsRepositories.save(accounts);
    }

    @Override
    public ResponseEntity<?> getAccountById(Integer id) {
        if(id == null){
            ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(500).message("Need enter id")
                    .payload(null).build();
            return ResponseEntity.status(200).body(bodyServer);
        }else {
            Accounts accounts = accountsRepositories.findById(id).orElse(null);
            if(accounts != null){
                ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Get Accounts By Id Success")
                        .payload(accounts).build();
                return ResponseEntity.status(200).body(bodyServer);
            }else {
                ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(500).message("Account not exit")
                        .payload(null).build();
                return ResponseEntity.status(200).body(bodyServer);
            }
        }
    }

    @Override
    public ResponseEntity<?> updateAccounts(Accounts entity, Integer id) {
        Optional<Accounts> optionalAccounts = accountsRepositories.findById(id);
        if(optionalAccounts.isPresent()){
            Accounts accounts = optionalAccounts.get();
            accounts.setUsername(entity.getUsername());
            accounts.setFullname(entity.getFullname());
            accounts.setPhone(entity.getPhone());
            accounts.setGender(entity.getGender());
            accounts.setDate_of_birth(entity.getDate_of_birth());
            accounts.setImg(entity.getImg());
            accounts.setUpdated_at(new Date());
            accountsRepositories.save(accounts);
            ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Update Account Successfully")
                    .payload(accounts).build();
            return ResponseEntity.status(200).body(bodyServer);
        }
        ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(500).message("Account not exit").build();
        return ResponseEntity.status(200).body(bodyServer);
    }

    @Override
    public ResponseEntity<?> deleteAccountsById(Integer id){
        Optional<Accounts> optionalAccounts = accountsRepositories.findById(id);
        if(optionalAccounts.isPresent()){
            Accounts accounts = optionalAccounts.get();
            accounts.setDeleted_at(new Date());
            accountsRepositories.save(accounts);
            ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Deleted Account Successfully")
                    .payload(accounts).build();
            return ResponseEntity.status(200).body(bodyServer);
        }
        ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(500).message("Account not exit").build();
        return ResponseEntity.status(200).body(bodyServer);
    }
}
