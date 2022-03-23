package com.rabilmiraliyev.swagger.repository;

import com.rabilmiraliyev.swagger.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
