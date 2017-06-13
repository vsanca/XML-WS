package com.xmlwebservisi2016.firma.controller;

import com.xmlwebservisi2016.firma.dto.Kupovina;
import com.xmlwebservisi2016.firma.model.database_entities.Firma;
import com.xmlwebservisi2016.firma.model.database_entities.Proizvod;
import com.xmlwebservisi2016.firma.model.database_entities.Stavka;
import com.xmlwebservisi2016.firma.model.database_entities.Zaglavlje;
import com.xmlwebservisi2016.firma.model.jaxb.faktura_zaglavlje.FakturaZaglavlje;
import com.xmlwebservisi2016.firma.model.jaxb.prenos.NalogZaPrenos;
import com.xmlwebservisi2016.firma.model.jaxb.tipovi_podataka.TFakturaStavka;
import com.xmlwebservisi2016.firma.model.jaxb.tipovi_podataka.TOsobaPrenos;
import com.xmlwebservisi2016.firma.service.FirmaService;
import com.xmlwebservisi2016.firma.service.ProizvodService;
import com.xmlwebservisi2016.firma.service.StavkaService;
import com.xmlwebservisi2016.firma.service.ZaglavljeService;
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

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Svetozar Stojkovic on 6/10/2017.
 */
@RestController
public class ProizvodController {

    @Autowired
    private ProizvodService proizvodService;

    @Autowired
    private FirmaService firmaService;

    @Autowired
    private ZaglavljeService zaglavljeService;

    @Autowired
    private StavkaService stavkaService;

    @RequestMapping(
            value = "/dodaj_proizvod",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Proizvod> dodajProizvod(@RequestBody Proizvod proizvod) {

        Proizvod proizvodRet = proizvodService.dodajIliIzmeniProizvod(proizvod);

        if (proizvodRet != null) {
            return new ResponseEntity<Proizvod>(proizvodRet, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }

    @RequestMapping(
            value = "/getAllProizvodi",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Proizvod>> getProizvod() {

        List<Proizvod> proizvodi = proizvodService.findAll();

        if (proizvodi != null) {
            return new ResponseEntity<List<Proizvod>>(proizvodi, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }

    @RequestMapping(
            value = "/getZaFirmu",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Proizvod>> getProizvodZaFirmu(@RequestBody Long fid) {

        Firma firma = firmaService.findById(fid);

        if (firma != null) {

            List<Proizvod> proizvodi = proizvodService.getProizvodiZaFirmu(firma);
            if (proizvodi != null) {
                return new ResponseEntity<List<Proizvod>>(proizvodi, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }
        else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }

    @RequestMapping(
            value = "/kupiProizvode",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity kupiProizvod(@RequestBody Kupovina kupovina) throws DatatypeConfigurationException {

        try {
            Firma prodavac = firmaService.findById(kupovina.getProizvodi().keySet().iterator().next().getFirma().getId());
            Firma kupac = firmaService.findById(kupovina.getKupacID());

            if (prodavac != null && kupac != null) {
                FakturaZaglavlje fakturaZaglavlje = new FakturaZaglavlje();
                fakturaZaglavlje.setIdPoruke(UUID.randomUUID().toString());
                fakturaZaglavlje.setNazivDobavljaca(prodavac.getName());
                fakturaZaglavlje.setAdresaDobavljaca(prodavac.getAdresa());
                fakturaZaglavlje.setPibDobavljaca(prodavac.getPib());
                fakturaZaglavlje.setNazivKupca(kupac.getName());
                fakturaZaglavlje.setAdresaKupca(kupac.getAdresa());
                fakturaZaglavlje.setPibKupca(kupac.getPib());
                fakturaZaglavlje.setBrojRacuna(new Random().nextInt(999999));

                GregorianCalendar c = new GregorianCalendar();
                c.setTime(new Date(System.currentTimeMillis()));
                XMLGregorianCalendar xmlGC = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
                fakturaZaglavlje.setDatumRacuna(xmlGC);

                BigDecimal roba = BigDecimal.valueOf(0);
                BigDecimal usluge = BigDecimal.valueOf(0);
                BigDecimal rabat = BigDecimal.valueOf(0);

                for (Proizvod p : kupovina.getProizvodi().keySet()) {
                    if (p.ifRoba()) {
                        roba.add(BigDecimal.valueOf(p.getCena()));
                    } else if (p.ifUsluga()) {
                        usluge.add(BigDecimal.valueOf(p.getCena()));
                    }
                    rabat.add(BigDecimal.valueOf(p.getRabat()).multiply(BigDecimal.valueOf(p.getCena())));
                }

                BigDecimal robaIUsluge = roba.add(usluge);
                fakturaZaglavlje.setVrednostRobe(roba);
                fakturaZaglavlje.setVrednostUsluga(usluge);
                fakturaZaglavlje.setUkupnoRobaIUsluge(robaIUsluge);

                fakturaZaglavlje.setUkupanRabat(rabat);

                BigDecimal porez = roba.add(usluge).multiply(BigDecimal.valueOf(0.2));
                fakturaZaglavlje.setUkupanPorez(porez);
                fakturaZaglavlje.setOznakaValute(kupovina.getOznakaValute());
                BigDecimal ukupnaUplata = robaIUsluge.subtract(rabat).add(porez);
                fakturaZaglavlje.setIznosZaUplatu(ukupnaUplata);
                fakturaZaglavlje.setUplataNaRacun(prodavac.getBrojRacuna());
                fakturaZaglavlje.setDatumValute(xmlGC);
                int i = 0;
                for (Proizvod p : kupovina.getProizvodi().keySet()) {
                    TFakturaStavka fakturaStavka = new TFakturaStavka();
                    fakturaStavka.setRedniBroj(++i);
                    fakturaStavka.setNazivRobeIliUsluge(p.getNaziv());
                    fakturaStavka.setKolicina(BigDecimal.valueOf(kupovina.getProizvodi().get(p)));
                    fakturaStavka.setJedinicaMere(p.getMera());
                    fakturaStavka.setJedinicnaCena(BigDecimal.valueOf(p.getCena()));
                    fakturaStavka.setVrednost(BigDecimal.valueOf(p.getCena()).multiply(BigDecimal.valueOf(kupovina.getProizvodi().get(p))));
                    fakturaStavka.setProcenatRabata(BigDecimal.valueOf(p.getRabat()));
                    BigDecimal iznosRabata = BigDecimal.valueOf(p.getRabat()).multiply(BigDecimal.valueOf(p.getCena()));
                    fakturaStavka.setIznosRabata(iznosRabata);
                    fakturaStavka.setUmanjenoZaRabat(BigDecimal.valueOf(p.getCena()).subtract(iznosRabata));
                    BigDecimal ukupanPorez = BigDecimal.valueOf(p.getCena()).multiply(BigDecimal.valueOf(0.2));
                    fakturaStavka.setUkupanPorez(ukupanPorez);

                    fakturaZaglavlje.getFakturaStavka().add(fakturaStavka);
                }

                Zaglavlje zaglavlje = Converter.fromFakturaZaglavljeToZaglavlje(fakturaZaglavlje);
                zaglavlje.setPotvrdjeno(false);
                zaglavlje.setZavrseno(false);

                Zaglavlje zaglavljeRet = zaglavljeService.dodajIliIzmeniZaglavlje(zaglavlje);

                if (zaglavljeRet != null) {
                    List<Stavka> stavke = Converter.fromFakturaZaglavljeToStavka(fakturaZaglavlje);
                    for (Stavka stavka : stavke) {
                        stavkaService.dodajIliIzmeniStavku(stavka);
                    }
                }
                new FirmaWebSocket().displayMessageToActiveUsers();
                return new ResponseEntity(HttpStatus.OK);

            } else {
                return new ResponseEntity(HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

    }



}
