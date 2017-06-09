package com.xmlwebservisi2016.firma.repository;

import com.xmlwebservisi2016.firma.model.Firma;
import com.xmlwebservisi2016.firma.model.User;
import com.xmlwebservisi2016.firma.model.jaxb.izvod.Izvod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Svetozar Stojkovic on 6/9/2017.
 */
public interface FirmaRepository  extends JpaRepository<Firma, Long> {

    Firma findById(Long id);

    List<Firma> findByUsername(String username);

    List<Firma> findAll();
}
