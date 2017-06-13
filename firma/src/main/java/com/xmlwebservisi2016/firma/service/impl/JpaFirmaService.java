package com.xmlwebservisi2016.firma.service.impl;

import com.xmlwebservisi2016.firma.model.database_entities.Firma;
import com.xmlwebservisi2016.firma.repository.FirmaRepository;
import com.xmlwebservisi2016.firma.service.FirmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Svetozar Stojkovic on 6/9/2017.
 */
@Service
@Transactional
public class JpaFirmaService implements FirmaService {

    @Autowired
    private FirmaRepository firmaRepository;

    @Override
    public Firma findById(Long id) {
        return firmaRepository.findById(id);
    }

    @Override
    public Firma findByPib(String pib) {
        return firmaRepository.findByPib(pib);
    }

    @Override
    public Firma findByBrojRacuna(String brojRacuna) {
        return firmaRepository.findByBrojRacuna(brojRacuna);
    }

    @Override
    public List<Firma> findAll() {
        return firmaRepository.findAll();
    }

    @Override
    public Firma dodajFirmu(Firma firma) {
        return firmaRepository.saveAndFlush(firma);
    }
}
