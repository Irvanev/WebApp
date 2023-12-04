package com.example.webapp.services.impl;

import com.example.webapp.dtos.roles.AddRoleDto;
import com.example.webapp.dtos.roles.ShowAllRolesDto;
import com.example.webapp.models.Role;
import com.example.webapp.repositories.RoleRepository;
import com.example.webapp.services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;

    public RoleServiceImpl(ModelMapper modelMapper, RoleRepository roleRepository) {
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
    }

    public void addRole(AddRoleDto addRoleDto) {
        Role role = modelMapper.map(addRoleDto, Role.class);
        roleRepository.saveAndFlush(role);
    }

    public List<ShowAllRolesDto> allRoles() {
        return roleRepository.findAll().stream().map(role -> modelMapper.map(role, ShowAllRolesDto.class))
                .collect(Collectors.toList());
    }
}
