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
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Proizvod>> getProizvodZaFirmu(@RequestBody Long fid) throws Exception {

        System.out.println(fid);
        Firma firma = firmaService.findById(fid);
        System.out.println(firma.getId());
        if (firma != null) {

            List<Proizvod> proizvodi = proizvodService.findByFirma(firma);
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
    /* primer
        {
            "kupacID":2,
            "proizvodi":
            [
                {"id":1,"tip":"USLUGA","naziv":"Donji ves","mera":"kg","cena":100.0,"rabat":10.0,"kolicina":20,"firma":{"id":1,"name":"Best firma1 ever","adresa":"Djure Danicica 45","brojRacuna":"111-1111111111111-11","pozivNaBroj":"11111111111111111111","model":97,"pib":"123","version":1,"bankPort":"8080"},"version":1},
                {"id":2,"tip":"ROBA","naziv":"Lizalice","mera":"g","cena":150.0,"rabat":5.0,"kolicina":34,"firma":{"id":1,"name":"Best firma1 ever","adresa":"Djure Danicica 45","brojRacuna":"111-1111111111111-11","pozivNaBroj":"11111111111111111111","model":97,"pib":"123","version":1,"bankPort":"8080"},"version":1}

            ],
            "kolicine" : [2,3],
            "oznakaValute":"RSD"
        }

     */
    @RequestMapping(
            value = "/kupiProizvode",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity kupiProizvod(@RequestBody Kupovina kupovina) throws DatatypeConfigurationException {

        try {
            Firma prodavac = firmaService.findById(kupovina.getProizvodi().get(0).getFirma().getId());
            Firma kupac = firmaService.findById(kupovina.getKupacID());

            if (prodavac != null && kupac != null) {
                Zaglavlje zaglavlje = new Zaglavlje();
                zaglavlje.setIdPoruke(UUID.randomUUID().toString());
                zaglavlje.setNazivDobavljaca(prodavac.getName());
                zaglavlje.setAdresaDobavljaca(prodavac.getAdresa());
                zaglavlje.setPibDobavljaca(prodavac.getPib());
                zaglavlje.setNazivKupca(kupac.getName());
                zaglavlje.setAdresaKupca(kupac.getAdresa());
                zaglavlje.setPibKupca(kupac.getPib());
                zaglavlje.setBrojRacuna(new Random().nextInt(999999));

                zaglavlje.setDatumRacuna(new java.sql.Date(System.currentTimeMillis()));

                List<Stavka> stavke = new ArrayList<>();

                double roba = 0;
                double usluge = 0;
                double rabat = 0;
                int i = 0;
                for (Proizvod p : kupovina.getProizvodi()) {

                    if (p.ifRoba()) {
                        roba += p.getCena() * kupovina.getKolicine().get(i);
                    } else if (p.ifUsluga()) {
                        usluge += p.getCena() * kupovina.getKolicine().get(i);
                    }

                    Stavka stavka = new Stavka();
                    stavka.setRedniBroj(++i);
                    stavka.setNazivRobeIliUsluge(p.getNaziv());

                    long kolicina = kupovina.getKolicine().get(i-1);
                    stavka.setKolicina(BigDecimal.valueOf(kolicina));
                    stavka.setJedinicaMere(p.getMera());
                    stavka.setJedinicnaCena(BigDecimal.valueOf(p.getCena()));

                    double vrednost = p.getCena() * kolicina;
                    stavka.setVrednost(BigDecimal.valueOf(vrednost));
                    stavka.setProcenatRabata(BigDecimal.valueOf(p.getRabat()));

                    double iznosRabata = (p.getRabat()/100) * vrednost;
                    rabat += iznosRabata;
                    stavka.setIznosRabata(BigDecimal.valueOf(iznosRabata));

                    double umanjenoZaRabat = vrednost - iznosRabata;
                    stavka.setUmanjenoZaRabat(BigDecimal.valueOf(umanjenoZaRabat));

                    double ukupanPorez = umanjenoZaRabat * 0.2;
                    stavka.setUkupanPorez(BigDecimal.valueOf(ukupanPorez));
                    stavka.setZaglavlje(zaglavlje);
                    stavka.setProizvod(p);
                    stavke.add(stavka);

                }

                double robaIUsluge = roba + usluge;
                zaglavlje.setVrednostRobe(BigDecimal.valueOf(roba));
                zaglavlje.setVrednostUsluga(BigDecimal.valueOf(usluge));
                zaglavlje.setUkupnoRobaIUsluge(BigDecimal.valueOf(robaIUsluge));
                zaglavlje.setUkupanRabat(BigDecimal.valueOf(rabat));

                double porez = (robaIUsluge - rabat) * 0.2;

                zaglavlje.setUkupanPorez(BigDecimal.valueOf(porez));
                zaglavlje.setOznakaValute(kupovina.getOznakaValute());

                double ukupnaUplata = robaIUsluge - rabat + porez;
                zaglavlje.setIznosZaUplatu(BigDecimal.valueOf(ukupnaUplata));

                zaglavlje.setUplataNaRacun(prodavac.getBrojRacuna());
                zaglavlje.setDatumValute(new java.sql.Date(System.currentTimeMillis()));

                Zaglavlje zaglavljeRet = zaglavljeService.dodajIliIzmeniZaglavlje(zaglavlje);
                if (zaglavljeRet != null) {
                    for (Stavka stavka : stavke) {
                        stavkaService.dodajIliIzmeniStavku(stavka);
                    }
                }
                System.out.println(Converter.getJSONString(zaglavljeRet));
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
