package com.kreuzfeuer.mirea.trpp.final_project.service;

import com.kreuzfeuer.mirea.trpp.final_project.entity.User;
import com.kreuzfeuer.mirea.trpp.final_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository repository;

    /**
     * Search by id. Using the default JpaRepository implementation .
     * @param id - User id
     * @return User
     */
    @Transactional
    @Override
    public User findById(Long id) {
        User user = repository.findById(id).get();
        return user;
    }
}
