package com.merin.newboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.merin.newboot.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByEmail(String email);
}
