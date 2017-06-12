package com.xmlwebservisi2016.firma.service.impl;

import com.xmlwebservisi2016.firma.model.database_entities.Stavka;
import com.xmlwebservisi2016.firma.model.database_entities.Zaglavlje;
import com.xmlwebservisi2016.firma.repository.StavkaRepository;
import com.xmlwebservisi2016.firma.service.StavkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Svetozar Stojkovic on 6/12/2017.
 */
@Service
@Transactional
public class JpaStavkaService implements StavkaService{


    @Autowired
    private StavkaRepository stavkaRepository;

    @Override
    public Stavka findByZaglavljeAndRedniBroj(Zaglavlje zaglavlje, int redniBroj) {
        return stavkaRepository.findByZaglavljeAndRedniBroj(zaglavlje, redniBroj);
    }

    @Override
    public List<Stavka> findAll() {
        return stavkaRepository.findAll();
    }

    @Override
    public List<Stavka> findByZaglavlje(Zaglavlje zaglavlje) {
        return stavkaRepository.findByZaglavlje(zaglavlje);
    }

    @Override
    public Stavka dodajIliIzmeniStavku(Stavka stavka) {
        return stavkaRepository.saveAndFlush(stavka);
    }

    @Override
    public void deleteByZaglavlje(Zaglavlje zaglavlje) {
        stavkaRepository.deleteByZaglavlje(zaglavlje);
    }
}
