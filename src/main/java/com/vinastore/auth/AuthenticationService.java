package com.vinastore.auth;

import com.vinastore.configs.JwtService;
import com.vinastore.entities.Accounts;
import com.vinastore.repositories.AccountsRepositories;
import com.vinastore.service.RolesService;
import com.vinastore.utils.PasswordUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Autowired
    private final RolesService rolesService;

    private final AccountsRepositories accountsRepositories;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request){

        var user = Accounts.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(PasswordUtils.hashPassword(request.getPassword()))
                .role_id(rolesService.findRoleById("user"))
                .build();
        accountsRepositories.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authentication(AuthenticationRepuest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = accountsRepositories.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
