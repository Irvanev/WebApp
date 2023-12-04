package com.example.webapp.services;

import com.example.webapp.dtos.users.AddUserDto;
import com.example.webapp.dtos.users.ShowAllUsersDto;

import java.util.List;

public interface UserService {
    public void addUser(AddUserDto addUserDto);
    public List<ShowAllUsersDto> allUsers();
    public void removeUser(String userName);
}