package com.vinastore.service;

import com.vinastore.entities.Roles;

import java.util.List;

public interface RolesService {
    List<Roles> getAllRole();

    Roles findRoleById(String id);
}
