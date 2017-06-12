package com.xmlwebservisi2016.firma.controller;

import com.xmlwebservisi2016.firma.dto.PibsDTO;
import com.xmlwebservisi2016.firma.dto.UserFirmaDTO;
import com.xmlwebservisi2016.firma.model.LoginAttempt;
import com.xmlwebservisi2016.firma.model.database_entities.Firma;
import com.xmlwebservisi2016.firma.model.database_entities.Stavka;
import com.xmlwebservisi2016.firma.model.database_entities.User;
import com.xmlwebservisi2016.firma.model.database_entities.Zaglavlje;
import com.xmlwebservisi2016.firma.model.jaxb.faktura_zaglavlje.FakturaZaglavlje;
import com.xmlwebservisi2016.firma.service.StavkaService;
import com.xmlwebservisi2016.firma.service.ZaglavljeService;
import com.xmlwebservisi2016.firma.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Svetozar Stojkovic on 6/12/2017.
 */
@RestController
public class FaktureController {

    @Autowired
    private ZaglavljeService zaglavljeService;

    @Autowired
    private StavkaService stavkaService;

    @RequestMapping(
            value = "/moje_fakture",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<FakturaZaglavlje>> mojeFakture(@RequestBody PibsDTO pibsDTO) {
        try {
            List<FakturaZaglavlje> fakture = getMojeFakture(pibsDTO.getMojPib(), pibsDTO.getDrugiPib());
            return new ResponseEntity<List<FakturaZaglavlje>>(fakture, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    private List<FakturaZaglavlje> getMojeFakture(String mojPib, String drugiPib) {
        Objects.requireNonNull(mojPib);
        Objects.requireNonNull(drugiPib);
        List<Zaglavlje> mojeFakture;
        if (mojPib.equals(drugiPib)) {
            mojeFakture = zaglavljeService.findByPibDobavljacaOrPibKupca(mojPib, drugiPib);
        } else {
            mojeFakture = zaglavljeService.findByPibDobavljacaAndPibKupca(mojPib, drugiPib);
        }

        List<FakturaZaglavlje> fakture = new ArrayList<>();

        for (Zaglavlje zaglavlje : mojeFakture) {
            FakturaZaglavlje fakturaZaglavlje = Converter.fromZaglavljeToFakturaZaglavlje(zaglavlje);
            List <Stavka> stavke = stavkaService.findByZaglavlje(zaglavlje);
            for (Stavka stavka : stavke) {
                fakturaZaglavlje.getFakturaStavka().add(Converter.fromStavkaToTFakturaStavka(stavka));
            }
            fakture.add(fakturaZaglavlje);
        }
        return fakture;
    }
}
