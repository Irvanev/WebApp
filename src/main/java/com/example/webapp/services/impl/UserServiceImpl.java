package com.example.webapp.services.impl;

import com.example.webapp.dtos.users.AddUserDto;
import com.example.webapp.dtos.users.ShowAllUsersDto;
import com.example.webapp.models.Users;
import com.example.webapp.repositories.RoleRepository;
import com.example.webapp.repositories.UserRepository;
import com.example.webapp.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository, RoleRepository roleRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public void addUser(AddUserDto addUserDto) {
        addUserDto.setCreated(LocalDateTime.now());
        addUserDto.setModified(LocalDateTime.now());
        addUserDto.setActive(false);
        Users users = modelMapper.map(addUserDto, Users.class);
        users.setRole(roleRepository.findByRoleEnum(addUserDto.getRoleName()).orElse(null));

        userRepository.saveAndFlush(users);
    }
    public List<ShowAllUsersDto> allUsers() {
        return userRepository.findAll().stream().map(users -> modelMapper.map(users, ShowAllUsersDto.class))
                .collect(Collectors.toList());
    }
    public void removeUser(String userName) {
        userRepository.deleteByUserName(userName);
    }
}
