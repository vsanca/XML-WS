package com.xmlwebservisi2016.firma.repository;

import com.xmlwebservisi2016.firma.model.database_entities.Zaglavlje;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Svetozar Stojkovic on 6/11/2017.
 */
public interface ZaglavljeRepository extends JpaRepository<Zaglavlje, String> {

    Zaglavlje findByIdPoruke(String idPoruke);

    List<Zaglavlje> findAll();

    List<Zaglavlje> findByPibDobavljacaAndZavrsenoIsFalse(String pib);

    List<Zaglavlje> findByPibKupcaAndPotvrdjenoIsFalse(String pib);

    List<Zaglavlje> findByPibDobavljacaAndPibKupca(String pibDobavljaca, String pibKupca);

    List<Zaglavlje> findByPibDobavljacaOrPibKupca(String pibDobavljaca, String pibKupca);

    void deleteByIdPoruke(String idPoruke);

}
