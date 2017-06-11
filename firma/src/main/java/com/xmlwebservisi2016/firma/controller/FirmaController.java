package com.xmlwebservisi2016.firma.controller;

import com.xmlwebservisi2016.firma.model.Firma;
import com.xmlwebservisi2016.firma.model.User;
import com.xmlwebservisi2016.firma.model.jaxb.izvod.Izvod;
import com.xmlwebservisi2016.firma.model.jaxb.zahtev.ZahtevZaIzvod;
import com.xmlwebservisi2016.firma.service.FirmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Svetozar Stojkovic on 6/9/2017.
 */
public class FirmaController {

    @Autowired
    private FirmaService firmaService;

    @RequestMapping(
            value = "/dodaj_firmu",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Firma> dodajFirmu(@RequestBody Firma firma) {

        Firma firmaRet = firmaService.dodajFirmu(firma);

        if (firmaRet != null) {
            return new ResponseEntity<Firma>(firmaRet, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }
}
