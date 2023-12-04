package com.example.webapp.dtos.roles;

import com.example.webapp.constants.RoleEnum;
import jakarta.validation.constraints.NotNull;

public class AddRoleDto {
    private RoleEnum roleEnum;

    @NotNull(message = "Please choose an role!")
    public RoleEnum getRoleEnum() {
        return roleEnum;
    }

    public void setRoleEnum(RoleEnum roleEnum) {
        this.roleEnum = roleEnum;
    }
}
