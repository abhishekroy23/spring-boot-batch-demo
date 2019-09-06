package com.demo.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.batch.entity.User;

public interface UsersRepository extends JpaRepository<User, Long> {
}
