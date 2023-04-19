package com.kreuzfeuer.mirea.trpp.final_project.repository;

import com.kreuzfeuer.mirea.trpp.final_project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByLogin(String login);

}
