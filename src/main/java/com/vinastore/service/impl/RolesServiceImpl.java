package com.vinastore.service.impl;

import com.vinastore.entities.Roles;
import com.vinastore.repositories.RolesRepositories;
import com.vinastore.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesServiceImpl implements RolesService {

    @Autowired
    RolesRepositories rolesRepositories;

    @Override
    public List<Roles> getAllRole() {
        return rolesRepositories.findAll();
    }

    @Override
    public Roles findRoleById(String id) {
        return rolesRepositories.findById(id).orElse(null);
    }
}
