package com.xmlwebservisi2016.firma.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xmlwebservisi2016.firma.dto.Kupovina;
import com.xmlwebservisi2016.firma.dto.UserFirmaDTO;
import com.xmlwebservisi2016.firma.dto.ZahtevDTO;
import com.xmlwebservisi2016.firma.model.database_entities.*;
import com.xmlwebservisi2016.firma.model.LoginAttempt;
import com.xmlwebservisi2016.firma.service.*;
import com.xmlwebservisi2016.firma.util.Converter;
import com.xmlwebservisi2016.firma.websockets.FirmaWebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Hp on 6/5/2017.
 */

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProizvodService proizvodService;

    @Autowired
    private StavkaService stavkaService;

    @Autowired
    private ZaglavljeService zaglavljeService;

    @RequestMapping(
            value = "/login",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserFirmaDTO> login(@RequestBody LoginAttempt loginAttempt) {

        User foundUser = userService.login(loginAttempt);

        if (foundUser != null) {
            Firma firma = foundUser.getFirma();
//            Proizvod proizvod = new Proizvod();
//            proizvod.setId(1);
//            proizvod.setNaziv("Novi proizvod");
//            proizvod.setFirma(firma);
//            proizvod.setVersion(1);
//            proizvodService.dodajIliIzmeniProizvod(proizvod);
//            new FirmaWebSocket().displayMessageToActiveUsers();
            //Firma firma = firmaService.findById(foundUser.getFirma().getId());
            //stavkaService.findAll();

//            Zaglavlje zaglavlje = zaglavljeService.findByIdPoruke("1");
//
//            Stavka stavka = new Stavka();
//            stavka.setId(3);
//            stavka.setZaglavlje(zaglavlje);
//            stavkaService.dodajIliIzmeniStavku(stavka);

//            Kupovina kupovina = new Kupovina();
//            kupovina.setKupacID(1);
//            kupovina.setOznakaValute("RSD");
//            List<Proizvod> proizvodLongMap = new HashMap<>();
//            proizvodLongMap.put(proizvodService.findByNazivAndFirma("Donji ves", firma), new Long(2));
//            proizvodLongMap.put(proizvodService.findByNazivAndFirma("Lizalice", firma), new Long(3));
//            kupovina.setProizvodi(proizvodLongMap);
//
//            System.out.println(Converter.getJSONString(proizvodService.findByNazivAndFirma("Donji ves", firma)));
//            System.out.println(Converter.getJSONString(proizvodService.findByNazivAndFirma("Lizalice", firma)));
//            System.out.println(Converter.getJSONString(kupovina));

            if (firma != null) {
                UserFirmaDTO ufDTO = new UserFirmaDTO(foundUser, firma);
                return new ResponseEntity<UserFirmaDTO>(ufDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }
        else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }



}
