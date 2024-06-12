package com.vinastore.service.impl;

import com.vinastore.dto.ChangePasswordDTO;
import com.vinastore.entities.Accounts;
import com.vinastore.repositories.AccountsRepositories;
import com.vinastore.service.ChangePasswordService;
import com.vinastore.utils.PasswordUtils;
import com.vinastore.utils.ResponseBodyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ChangePasswordServiceImpl implements ChangePasswordService {

    @Autowired
    private AccountsRepositories accountsRepositories;

    @Override
    public ResponseEntity<?> isValidPasswordChange(ChangePasswordDTO changePasswordDTO) {
        Integer id = changePasswordDTO.getId();
        String oldPassword = changePasswordDTO.getOldPassword();
        String newPassword = changePasswordDTO.getNewPassword();

        Accounts accounts =accountsRepositories.findAccountByIdAndPassword(id,oldPassword);
        if(accounts == null){
            ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(500).message("Account not exit!!!")
                    .payload(null).build();
            return ResponseEntity.status(200).body(bodyServer);
        }else {
            String hashPassword1 = PasswordUtils.decryptPassword(newPassword);
            accounts.setPassword(hashPassword1);
            accountsRepositories.save(accounts);
            ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(200).message("Update Account Successfully")
                    .payload(accounts).build();
            return ResponseEntity.status(200).body(bodyServer);
        }
    }
}
