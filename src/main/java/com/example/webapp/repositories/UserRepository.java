package com.example.webapp.repositories;

import com.example.webapp.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, String> {
    Optional<Users> findByUserName(String userName);

    @Modifying
    @Transactional
    void deleteByUserName(String name);
}