package com.xmlwebservisi2016.firma.service;

import com.xmlwebservisi2016.firma.model.database_entities.Stavka;
import com.xmlwebservisi2016.firma.model.database_entities.Zaglavlje;

import java.util.List;

/**
 * Created by Svetozar Stojkovic on 6/12/2017.
 */
public interface StavkaService {

    Stavka findByZaglavljeAndRedniBroj(Zaglavlje zaglavlje, int redniBroj);

    List<Stavka> findAll();

    List<Stavka> findByZaglavlje(Zaglavlje zaglavlje);

    Stavka dodajIliIzmeniStavku(Stavka stavka);

    void deleteByZaglavlje(Zaglavlje zaglavlje);
}
