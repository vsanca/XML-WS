package com.xmlwebservisi2016.firma.repository;

import com.xmlwebservisi2016.firma.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Hp on 6/5/2017.
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findById(Long id);

    List<User> findByUsername(String username);

    List<User> findAll();

}
