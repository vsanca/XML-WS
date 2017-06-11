package com.xmlwebservisi2016.firma.service;

import com.xmlwebservisi2016.firma.model.Firma;
import com.xmlwebservisi2016.firma.model.LoginAttempt;
import com.xmlwebservisi2016.firma.model.User;

import java.util.List;

/**
 * Created by Svetozar Stojkovic on 6/9/2017.
 */
public interface FirmaService {

    Firma findById(Long id);

    List<Firma> findByUsername(String username);

    List<Firma> findAll();

    Firma dodajFirmu(Firma firma);

}
