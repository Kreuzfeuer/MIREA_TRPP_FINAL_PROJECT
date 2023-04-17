package com.kreuzfeuer.mirea.trpp.final_project.service;

import com.kreuzfeuer.mirea.trpp.final_project.entity.User;
import com.kreuzfeuer.mirea.trpp.final_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository repository;

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
