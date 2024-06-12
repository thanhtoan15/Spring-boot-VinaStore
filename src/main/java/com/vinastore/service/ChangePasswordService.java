package com.vinastore.service;

import com.vinastore.dto.ChangePasswordDTO;
import org.springframework.http.ResponseEntity;

public interface ChangePasswordService {
    ResponseEntity<?> isValidPasswordChange(ChangePasswordDTO changePasswordDTO);
}
