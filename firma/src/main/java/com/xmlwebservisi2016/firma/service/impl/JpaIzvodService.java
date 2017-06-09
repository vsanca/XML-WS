package com.xmlwebservisi2016.firma.service.impl;

import com.sun.media.jfxmedia.logging.Logger;
import com.xmlwebservisi2016.firma.model.jaxb.izvod.Izvod;
import com.xmlwebservisi2016.firma.repository.IzvodRepository;
import com.xmlwebservisi2016.firma.service.IzvodService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Svetozar Stojkovic on 6/8/2017.
 */
public class JpaIzvodService implements IzvodService {
    @Autowired
    private IzvodRepository izvodRepository;

    @Override
    public List<Izvod> saljiZahtevZaIzvod() {
        Logger.logMsg(Logger.INFO, "salji zahtev za izvod");

        for (int i=0; i<100; i++) {

        }
        return null;
    }

    @Override
    public List<Izvod> findAll() {
        return izvodRepository.findAll();
    }
}
