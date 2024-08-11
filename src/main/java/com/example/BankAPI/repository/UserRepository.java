package com.example.BankAPI.repository;

import com.example.BankAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(Long userId);
}
