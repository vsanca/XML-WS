package com.xmlwebservisi2016.firma.repository;

import com.xmlwebservisi2016.firma.model.database_entities.Firma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Svetozar Stojkovic on 6/9/2017.
 */
public interface FirmaRepository extends JpaRepository<Firma, Long> {

    Firma findById(Long id);

    Firma findByPib(String pib);

    List<Firma> findAll();


}
