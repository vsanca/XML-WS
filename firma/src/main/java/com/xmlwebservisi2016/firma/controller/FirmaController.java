package com.xmlwebservisi2016.firma.controller;

import com.xmlwebservisi2016.firma.model.database_entities.Firma;
import com.xmlwebservisi2016.firma.service.FirmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Svetozar Stojkovic on 6/9/2017.
 */
@RestController
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

    @RequestMapping(
            value = "/getAllFirme",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Firma>> getFirme() {

        List<Firma> firme = firmaService.findAll();

        if (firme != null) {
            return new ResponseEntity<List<Firma>>(firme, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }
}
