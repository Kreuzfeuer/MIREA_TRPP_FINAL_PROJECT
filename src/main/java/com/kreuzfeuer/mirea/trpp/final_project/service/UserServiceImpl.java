package com.kreuzfeuer.mirea.trpp.final_project.service;

import com.kreuzfeuer.mirea.trpp.final_project.entity.User;
import com.kreuzfeuer.mirea.trpp.final_project.entity.enums.Role;
import com.kreuzfeuer.mirea.trpp.final_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private  UserRepository repository;

    /**
     * Search by id. Using the default JpaRepository implementation .
     *
     * @param id - User id
     * @return User
     */

    @Override
    public User findById(Long id) {
        User user = repository.findById(id).get();
        return user;
    }

    @Override
    public boolean createUser(User user) {
        String login = user.getLogin();
        if (repository.findByLogin(login).isPresent()) {
            return false;
        }
        user.setActive(true);
        user.getRoles().add(Role.ROLE_USER);
        user.setPassword(new BCryptPasswordEncoder(8).encode(user.getPassword()));
        log.info("Saving new User with login: {}", login);
        repository.save(user);
        return true;
    }

    @Override
    public User findByLogin(String login) {
        return repository.findByLogin(login).get();
    }
}
