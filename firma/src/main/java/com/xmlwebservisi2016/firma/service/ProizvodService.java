package com.xmlwebservisi2016.firma.service;

import com.xmlwebservisi2016.firma.model.database_entities.Firma;
import com.xmlwebservisi2016.firma.model.database_entities.Proizvod;

import java.util.List;

/**
 * Created by Svetozar Stojkovic on 6/10/2017.
 */
public interface ProizvodService {

    Proizvod findByNazivAndFirma(String naziv, Firma firma);

    List<Proizvod> findAll();

    List<Proizvod> findByFirma(Firma firma);

    Proizvod dodajIliIzmeniProizvod(Proizvod proizvod);

    void ukoniProizvod(Proizvod proizvod);
}
