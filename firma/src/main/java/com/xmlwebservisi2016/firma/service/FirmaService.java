package com.xmlwebservisi2016.firma.service;

import com.xmlwebservisi2016.firma.model.database_entities.Firma;

import java.util.List;

/**
 * Created by Svetozar Stojkovic on 6/9/2017.
 */
public interface FirmaService {

    Firma findById(Long id);

    Firma findByPib(String pib);

    Firma findByBrojRacuna(String brojRacuna);

    List<Firma> findAll();

    Firma dodajFirmu(Firma firma);

}
