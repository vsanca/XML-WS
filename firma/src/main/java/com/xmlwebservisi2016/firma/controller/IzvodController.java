package com.xmlwebservisi2016.firma.controller;

import com.xmlwebservisi2016.firma.model.LoginAttempt;
import com.xmlwebservisi2016.firma.model.User;
import com.xmlwebservisi2016.firma.model.jaxb.izvod.Izvod;
import com.xmlwebservisi2016.firma.model.jaxb.zahtev.ZahtevZaIzvod;
import com.xmlwebservisi2016.firma.service.IzvodService;
import com.xmlwebservisi2016.firma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Svetozar Stojkovic on 6/8/2017.
 */
public class IzvodController  {

    @Autowired
    private IzvodService izvodService;

    @RequestMapping(
            value = "/salji_zahtev_za_izvod",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Izvod>> sendZahtevZaIzvod(@RequestBody ZahtevZaIzvod zahtevZaIzvod) {

        List<Izvod> izvodi = izvodService.saljiZahtevZaIzvod();

        if (izvodi != null) {
            return new ResponseEntity<List<Izvod>>(izvodi, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }


}