package com.xmlwebservisi2016.firma.service.impl;

import com.xmlwebservisi2016.firma.model.Firma;
import com.xmlwebservisi2016.firma.repository.FirmaRepository;
import com.xmlwebservisi2016.firma.service.FirmaService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Svetozar Stojkovic on 6/9/2017.
 */
public class JpaFirmaService implements FirmaService {

    @Autowired
    private FirmaRepository firmaRepository;

    @Override
    public Firma findById(Long id) {
        return null;
    }

    @Override
    public List<Firma> findByUsername(String username) {
        return firmaRepository.findByUsername(username);
    }

    @Override
    public List<Firma> findAll() {
        return firmaRepository.findAll();
    }

    @Override
    public Firma dodajFirmu(Firma firma) {
        return firmaRepository.save(firma);
    }
}
