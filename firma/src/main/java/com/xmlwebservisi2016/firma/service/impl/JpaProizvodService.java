package com.xmlwebservisi2016.firma.service.impl;

import com.xmlwebservisi2016.firma.model.database_entities.Firma;
import com.xmlwebservisi2016.firma.model.database_entities.Proizvod;
import com.xmlwebservisi2016.firma.repository.ProizvodiRepository;
import com.xmlwebservisi2016.firma.service.ProizvodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Svetozar Stojkovic on 6/10/2017.
 */
@Service
@Transactional
public class JpaProizvodService implements ProizvodService {

    @Autowired
    private ProizvodiRepository proizvodiRepository;


    @Override
    public Proizvod findByNazivAndFirma(String naziv, Firma firma) {
        return proizvodiRepository.findByNazivAndFirma(naziv, firma);
    }

    @Override
    public List<Proizvod> findAll() {
        return proizvodiRepository.findAll();
    }

    @Override
    public List<Proizvod> getProizvodiZaFirmu(Firma fid) {
        return proizvodiRepository.findByFirma(fid);
    }

    @Override
    public Proizvod dodajIliIzmeniProizvod(Proizvod proizvod) {
        return proizvodiRepository.saveAndFlush(proizvod);
    }

    @Override
    public void ukoniProizvod(Proizvod proizvod) {
        proizvodiRepository.delete(proizvod);
    }
}
