//package com.vinastore.service.impl;
//
//import com.vinastore.entities.Accounts;
//import com.vinastore.repositories.AccountsRepositories;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class UserDetailServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private final AccountsRepositories accountsRepositories;
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Optional<Accounts> accounts = accountsRepositories.findByEmail(email);
//        if(accounts == null){
//            throw new UsernameNotFoundException("User not found with email" +email);
//        }
//        return accounts;
//    }
//}
