package com.kreuzfeuer.mirea.trpp.final_project.service;

import com.kreuzfeuer.mirea.trpp.final_project.entity.User;

public interface UserService {
     User findById(Long id);
     boolean createUser(User user);

     User findByLogin(String login);

}
