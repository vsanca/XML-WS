package com.xmlwebservisi2016.firma.service;

import com.xmlwebservisi2016.firma.model.jaxb.izvod.Izvod;

import java.util.List;

/**
 * Created by Svetozar Stojkovic on 6/8/2017.
 */
public interface IzvodService {

    List<Izvod> saljiZahtevZaIzvod();

    List<Izvod> findAll();
}
