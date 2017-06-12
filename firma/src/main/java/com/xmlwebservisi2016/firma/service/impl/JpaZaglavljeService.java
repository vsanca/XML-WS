package com.xmlwebservisi2016.firma.service.impl;

import com.xmlwebservisi2016.firma.model.database_entities.Zaglavlje;
import com.xmlwebservisi2016.firma.repository.StavkaRepository;
import com.xmlwebservisi2016.firma.repository.ZaglavljeRepository;
import com.xmlwebservisi2016.firma.service.StavkaService;
import com.xmlwebservisi2016.firma.service.ZaglavljeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * Created by Svetozar Stojkovic on 6/12/2017.
 */
@Service
@Transactional
public class JpaZaglavljeService implements ZaglavljeService {


    @Autowired
    private ZaglavljeRepository zaglavljeRepository;


    @Override
    public Zaglavlje findByIdPoruke(String idPoruke) {
        return zaglavljeRepository.findByIdPoruke(idPoruke);
    }

    @Override
    public List<Zaglavlje> findAll() {
        return zaglavljeRepository.findAll();
    }

    @Override
    public List<Zaglavlje> findByPibDobavljacaAndZavrsenoIsFalse(String pib) {
        return zaglavljeRepository.findByPibDobavljacaAndZavrsenoIsFalse(pib);
    }

    @Override
    public List<Zaglavlje> findByPibDobavljacaAndPibKupca(String pibDobavljaca, String pibKupca) {

        List<Zaglavlje> zaglavlja = zaglavljeRepository.findByPibDobavljacaAndPibKupca(pibDobavljaca, pibKupca);
        zaglavlja.addAll(zaglavljeRepository.findByPibDobavljacaAndPibKupca(pibKupca, pibDobavljaca));
        return zaglavlja;

    }

    @Override
    public List<Zaglavlje> findByPibDobavljacaOrPibKupca(String pibDobavljaca, String pibKupca) {
        return zaglavljeRepository.findByPibDobavljacaOrPibKupca(pibDobavljaca, pibKupca);
    }

    @Override
    public Zaglavlje dodajIliIzmeniZaglavlje(Zaglavlje zaglavlje) {
        return zaglavljeRepository.saveAndFlush(zaglavlje);
    }

    @Override
    public void deleteByIdPoruke(String idPoruke) {
        zaglavljeRepository.deleteByIdPoruke(idPoruke);
    }

}
