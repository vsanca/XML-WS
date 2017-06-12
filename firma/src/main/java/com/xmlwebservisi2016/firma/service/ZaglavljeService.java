package com.xmlwebservisi2016.firma.service;

import com.xmlwebservisi2016.firma.model.database_entities.Zaglavlje;

import java.util.List;

/**
 * Created by Svetozar Stojkovic on 6/12/2017.
 */
public interface ZaglavljeService {

    Zaglavlje findByIdPoruke(String idPoruke);

    List<Zaglavlje> findAll();

    List<Zaglavlje> findByPibDobavljacaAndZavrsenoIsFalse(String pib);

    List<Zaglavlje> findByPibDobavljacaAndPibKupca(String pibDobavljaca, String pibKupca);

    List<Zaglavlje> findByPibDobavljacaOrPibKupca(String pibDobavljaca, String pibKupca);

    Zaglavlje dodajIliIzmeniZaglavlje(Zaglavlje zaglavlje);

    void deleteByIdPoruke(String idPoruke);


}
