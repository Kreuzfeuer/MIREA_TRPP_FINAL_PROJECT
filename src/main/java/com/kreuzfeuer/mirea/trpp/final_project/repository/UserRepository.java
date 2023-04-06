package com.kreuzfeuer.mirea.trpp.final_project.repository;

import com.kreuzfeuer.mirea.trpp.final_project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
