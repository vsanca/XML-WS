package com.xmlwebservisi2016.firma.repository;

import com.xmlwebservisi2016.firma.model.User;
import com.xmlwebservisi2016.firma.model.jaxb.izvod.Izvod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Svetozar Stojkovic on 6/8/2017.
 */
public interface IzvodRepository extends JpaRepository<Izvod, Long> {

    List<Izvod> findAll();


}
