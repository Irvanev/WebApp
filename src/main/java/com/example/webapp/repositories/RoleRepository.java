package com.example.webapp.repositories;

import com.example.webapp.constants.RoleEnum;
import com.example.webapp.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByRoleEnum(RoleEnum roleEnum);
}
