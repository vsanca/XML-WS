package com.xmlwebservisi2016.firma.repository;

import com.xmlwebservisi2016.firma.model.database_entities.Stavka;
import com.xmlwebservisi2016.firma.model.database_entities.StavkaPK;
import com.xmlwebservisi2016.firma.model.database_entities.Zaglavlje;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Svetozar Stojkovic on 6/12/2017.
 */
public interface StavkaRepository extends JpaRepository<Stavka, StavkaPK> {

    //Stavka findById(StavkaPK stavkaPK);
    Stavka findByZaglavljeAndRedniBroj(Zaglavlje zaglavlje, int redniBroj);

    List<Stavka> findAll();

    List<Stavka> findByZaglavlje(Zaglavlje zaglavlje);

    void deleteByZaglavlje(Zaglavlje zaglavlje);



}
