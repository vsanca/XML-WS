package com.xmlwebservisi2016.firma.service;

import com.xmlwebservisi2016.firma.model.LoginAttempt;
import com.xmlwebservisi2016.firma.model.User;

import java.util.List;

/**
 * Created by Hp on 6/5/2017.
 */

public interface UserService {

    User findById(Long id);

    List<User> findByUsername(String username);

    List<User> findAll();

    User login(LoginAttempt loginAttempt);

}
