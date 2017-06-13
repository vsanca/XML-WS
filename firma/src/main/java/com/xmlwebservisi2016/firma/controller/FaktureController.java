package com.xmlwebservisi2016.firma.controller;

import com.xmlwebservisi2016.firma.dto.PibsDTO;
import com.xmlwebservisi2016.firma.dto.UserFirmaDTO;
import com.xmlwebservisi2016.firma.model.LoginAttempt;
import com.xmlwebservisi2016.firma.model.database_entities.*;
import com.xmlwebservisi2016.firma.model.jaxb.faktura_zaglavlje.FakturaZaglavlje;
import com.xmlwebservisi2016.firma.model.jaxb.prenos.NalogZaPrenos;
import com.xmlwebservisi2016.firma.model.jaxb.tipovi_podataka.TFakturaStavka;
import com.xmlwebservisi2016.firma.model.jaxb.tipovi_podataka.TOsobaPrenos;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by Svetozar Stojkovic on 6/12/2017.
 */
@RestController
public class FaktureController {

    @Autowired
    private ZaglavljeService zaglavljeService;

    @Autowired
    private StavkaService stavkaService;

    @Autowired
    private FirmaService firmaService;

    @Autowired
    private BankaService bankaService;

    @Autowired
    private ProizvodService proizvodService;


    /**
     *  Ovu metodu poziva kupac firma nakon sto je dobio signal da je kupovina potvrdjena
     * @param fakturaZaglavlje
     * @return
     */
    @RequestMapping(
            value = "/get_nalog_za_prenos",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<NalogZaPrenos> dobijNalogZaPrenos(@RequestBody FakturaZaglavlje fakturaZaglavlje) {

        try {
            Firma prodavac = firmaService.findByPib(fakturaZaglavlje.getPibDobavljaca());
            Firma kupac = firmaService.findByPib(fakturaZaglavlje.getPibKupca());

            if (prodavac != null && kupac != null) {
                NalogZaPrenos nalogZaPrenos = new NalogZaPrenos();
                nalogZaPrenos.setIdPoruke(UUID.randomUUID().toString());
                nalogZaPrenos.setSvrhaPlacanja("kupovina");
                NalogZaPrenos.PodaciOPrenosu podaciOPrenosu = new NalogZaPrenos.PodaciOPrenosu();

                TOsobaPrenos duznik = new TOsobaPrenos();
                nalogZaPrenos.setDuznikNalogodavac(kupac.getName());
                duznik.setBrojRacuna(kupac.getBrojRacuna());
                duznik.setPozivNaBroj(kupac.getPozivNaBroj());
                duznik.setModel(kupac.getModel());

                TOsobaPrenos primalac = new TOsobaPrenos();
                nalogZaPrenos.setPrimalacPoverilac(prodavac.getName());
                primalac.setBrojRacuna(prodavac.getBrojRacuna());
                primalac.setPozivNaBroj(prodavac.getPozivNaBroj());
                primalac.setModel(prodavac.getModel());

                podaciOPrenosu.setDuznikPrenos(duznik);
                podaciOPrenosu.setPoverilacPrenos(primalac);
                podaciOPrenosu.setDatumValute(fakturaZaglavlje.getDatumValute());
                podaciOPrenosu.setOznakaValute(fakturaZaglavlje.getOznakaValute());
                podaciOPrenosu.setIznos(fakturaZaglavlje.getIznosZaUplatu());

                nalogZaPrenos.setPodaciOPrenosu(podaciOPrenosu);

                nalogZaPrenos.setDatumNaloga(fakturaZaglavlje.getDatumRacuna());

                Zaglavlje zaglavlje = zaglavljeService.findByIdPoruke(fakturaZaglavlje.getIdPoruke());
                zaglavlje.setZavrseno(true);

                zaglavljeService.dodajIliIzmeniZaglavlje(zaglavlje);

                new FirmaWebSocket().displayMessageToActiveUsers();

                return new ResponseEntity<NalogZaPrenos>(nalogZaPrenos, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }


    @RequestMapping(
            value = "/posalji_nalog_za_prenos",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Boolean> posaljiNalogZaPrenosBanci(@RequestBody NalogZaPrenos nalogZaPrenos) {
        Objects.requireNonNull(nalogZaPrenos);
        try {
            Firma firma = firmaService.findByBrojRacuna(nalogZaPrenos.getPodaciOPrenosu().getDuznikPrenos().getBrojRacuna());
            boolean retVal = false;
            if (firma != null) {
                retVal = bankaService.slanjeNalogaZaPlacanje(nalogZaPrenos, firma);
            }

            return new ResponseEntity<Boolean>(retVal, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }

    /**
     * Ako je poslat nalog za prenos ispravno stanje proizvoda se menja
     * @param fakturaZaglavlje
     * @return
     */
    @RequestMapping(
            value = "/novo_stanje_proizvoda",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Boolean> novoStanjeProizvoda(@RequestBody FakturaZaglavlje fakturaZaglavlje) {
        try {
            for (TFakturaStavka fakturaStavka : fakturaZaglavlje.getFakturaStavka()) {
                Zaglavlje zaglavlje = zaglavljeService.findByIdPoruke(fakturaZaglavlje.getIdPoruke());
                if (zaglavlje != null) {
                    Stavka stavka = stavkaService.findByZaglavljeAndRedniBroj(zaglavlje, fakturaStavka.getRedniBroj());
                    if (stavka != null) {
                        BigDecimal kolicina = stavka.getKolicina();
                        Proizvod proizvod = stavka.getProizvod();
                        proizvod.setKolicina(proizvod.getKolicina() - kolicina.longValue());
                        Firma kupac = firmaService.findByPib(fakturaZaglavlje.getPibKupca());
                        if (kupac != null) {
                            if (proizvodService.dodajIliIzmeniProizvod(proizvod) != null) {
                                Proizvod kupacProizvod = proizvodService.findByNazivAndFirma(proizvod.getNaziv(), kupac);
                                if (kupacProizvod != null) {
                                    kupacProizvod.setKolicina(kupacProizvod.getKolicina() + kolicina.longValue());
                                    proizvodService.dodajIliIzmeniProizvod(kupacProizvod);
                                } else {
                                    proizvod.setKolicina(kolicina.longValue());
                                    proizvod.setFirma(kupac);
                                    proizvodService.dodajIliIzmeniProizvod(proizvod);
                                }
                            }
                        }


                    }
                }

            }
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Boolean>(false, HttpStatus.FORBIDDEN);
        }
    }

    /**
     *  Ovu metodu prodavac poziva kada zeli da odobri kupovinu izabranih proizvoda
     * @param fakturaZaglavlje
     * @return
     */
    @RequestMapping(
            value = "/potvrdiKupovinu",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<FakturaZaglavlje> potvrdiKupovinu(@RequestBody FakturaZaglavlje fakturaZaglavlje) {

        try {
            Zaglavlje zaglavlje = zaglavljeService.findByIdPoruke(fakturaZaglavlje.getIdPoruke());
            zaglavlje.setPotvrdjeno(true);

            zaglavljeService.dodajIliIzmeniZaglavlje(zaglavlje);

            new FirmaWebSocket().displayMessageToActiveUsers();

            return new ResponseEntity<FakturaZaglavlje>(fakturaZaglavlje, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

    }

    /**
     * Odbijanje kupovine od strane kupca ili prodavca
     * @param fakturaZaglavlje
     * @return
     */
    @RequestMapping(
            value = "/odbijKupovinu",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity odbijKupovinu(@RequestBody FakturaZaglavlje fakturaZaglavlje) {
        try {
            Zaglavlje zaglavlje = zaglavljeService.findByIdPoruke(fakturaZaglavlje.getIdPoruke());
            stavkaService.deleteByZaglavlje(zaglavlje);
            zaglavljeService.deleteByIdPoruke(zaglavlje.getIdPoruke());

            new FirmaWebSocket().displayMessageToActiveUsers();

            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

    }

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
