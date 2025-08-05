package com.virtuallibrary.VirtualLibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtuallibrary.VirtualLibrary.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    boolean existsByEmail(String email);
}
