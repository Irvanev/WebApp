package com.example.webapp.services;

import com.example.webapp.dtos.roles.AddRoleDto;
import com.example.webapp.dtos.roles.ShowAllRolesDto;

import java.util.List;

public interface RoleService {
    public void addRole(AddRoleDto addRoleDto);
    public List<ShowAllRolesDto> allRoles();
}