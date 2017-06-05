package com.xmlwebservisi2016.firma.controller;

import com.xmlwebservisi2016.firma.model.LoginAttempt;
import com.xmlwebservisi2016.firma.model.User;
import com.xmlwebservisi2016.firma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Hp on 6/5/2017.
 */

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(
            value = "/login",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<User> login(@RequestBody LoginAttempt loginAttempt) {

        User foundUser = userService.login(loginAttempt);

        if (foundUser != null) {
            return new ResponseEntity<User>(foundUser, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }

}
