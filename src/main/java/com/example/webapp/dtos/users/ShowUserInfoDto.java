package com.example.webapp.dtos.users;

import com.example.webapp.constants.RoleEnum;
import com.example.webapp.dtos.offers.ShowAllOffersDto;

import java.time.LocalDateTime;
import java.util.List;

public class ShowUserInfoDto {
    private String imageURL;
    private String userName;
    private String firstName;
    private String lastName;
    private RoleEnum roleEnum;
    private String password;
    private boolean isActive;
    private LocalDateTime created;
    private LocalDateTime modified;
    private List<ShowAllOffersDto> offersDto;

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public RoleEnum getRoleEnum() {
        return roleEnum;
    }

    public void setRoleEnum(RoleEnum roleEnum) {
        this.roleEnum = roleEnum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public List<ShowAllOffersDto> getOffersDto() {
        return offersDto;
    }

    public void setOffersDto(List<ShowAllOffersDto> offersDto) {
        this.offersDto = offersDto;
    }
}
