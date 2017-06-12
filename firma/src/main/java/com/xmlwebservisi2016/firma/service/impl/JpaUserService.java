package com.xmlwebservisi2016.firma.service.impl;

import com.xmlwebservisi2016.firma.model.LoginAttempt;
import com.xmlwebservisi2016.firma.model.database_entities.User;
import com.xmlwebservisi2016.firma.repository.UserRepository;
import com.xmlwebservisi2016.firma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Hp on 6/5/2017.
 */

@Service
@Transactional
public class JpaUserService implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User login(LoginAttempt loginAttempt) {

        User user = findById(Long.getLong("1"));

        List<User> users = findAll();

        List<User> foundUsers = findByUsername(loginAttempt.getUsername());
        if (foundUsers.size() != 1) {
            return null;
        }

        if (foundUsers.get(0).getPassword().equals(loginAttempt.getPassword())) {
            return foundUsers.get(0);
        }
        else {
            return null;
        }
    }
}
