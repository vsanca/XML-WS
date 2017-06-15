package com.xmlwebservisi2016.firma.controller;

import com.xmlwebservisi2016.firma.dto.PibsDTO;
import com.xmlwebservisi2016.firma.dto.FaktureDTO;
import com.xmlwebservisi2016.firma.dto.ZaglavljeStavkeDTO;
import com.xmlwebservisi2016.firma.model.database_entities.*;
import com.xmlwebservisi2016.firma.model.jaxb.faktura_zaglavlje.FakturaZaglavlje;
import com.xmlwebservisi2016.firma.model.jaxb.prenos.NalogZaPrenos;
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

import javax.websocket.Session;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

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

    @Autowired
    private UserService userService;



    /**
     *  Ovu metodu poziva kupac firma nakon sto je dobio signal da je kupovina potvrdjena
     * @param zaglavlje
     * @return
     */
    @RequestMapping(
            value = "/get_nalog_za_prenos",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<NalogZaPrenos> dobijNalogZaPrenos(@RequestBody Zaglavlje zaglavlje) {

        try {
            Firma prodavac = firmaService.findByPib(zaglavlje.getPibDobavljaca());
            Firma kupac = firmaService.findByPib(zaglavlje.getPibKupca());

            if (prodavac != null && kupac != null && zaglavlje.isPotvrdjeno()) {

                GregorianCalendar gregor = new GregorianCalendar();
                Date date = new Date(System.currentTimeMillis());
                gregor.setTime(date);
                XMLGregorianCalendar xmlGregor = null;
                try {
                    xmlGregor = DatatypeFactory.newInstance().newXMLGregorianCalendarDate(
                            gregor.get(Calendar.YEAR), gregor.get(Calendar.MONTH)+1,
                            gregor.get(Calendar.DAY_OF_MONTH), DatatypeConstants.FIELD_UNDEFINED);
                } catch (DatatypeConfigurationException e) {
                    e.printStackTrace();
                }

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
                podaciOPrenosu.setDatumValute(xmlGregor);
                podaciOPrenosu.setOznakaValute(zaglavlje.getOznakaValute());
                podaciOPrenosu.setIznos(zaglavlje.getIznosZaUplatu());

                nalogZaPrenos.setPodaciOPrenosu(podaciOPrenosu);

                nalogZaPrenos.setDatumNaloga(xmlGregor);

                Zaglavlje zaglavljeRet = zaglavljeService.findByIdPoruke(zaglavlje.getIdPoruke());
                zaglavljeRet.setZavrseno(true);

                zaglavljeService.dodajIliIzmeniZaglavlje(zaglavljeRet);

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

        GregorianCalendar gregor = new GregorianCalendar();
        Date dateNaloga = new Date(nalogZaPrenos.getDatumNaloga().getMillisecond());
        gregor.setTime(dateNaloga);
        XMLGregorianCalendar xmlGregorNaloga = null;
        try {
            xmlGregorNaloga = DatatypeFactory.newInstance().newXMLGregorianCalendarDate(
                    gregor.get(Calendar.YEAR), gregor.get(Calendar.MONTH)+1,
                    gregor.get(Calendar.DAY_OF_MONTH), DatatypeConstants.FIELD_UNDEFINED);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }

        nalogZaPrenos.setDatumNaloga(xmlGregorNaloga);

        Date dateValute = new Date(nalogZaPrenos.getPodaciOPrenosu().getDatumValute().getMillisecond());
        gregor.setTime(dateValute);
        XMLGregorianCalendar xmlGregorValute = null;
        try {
            xmlGregorValute = DatatypeFactory.newInstance().newXMLGregorianCalendarDate(
                    gregor.get(Calendar.YEAR), gregor.get(Calendar.MONTH)+1,
                    gregor.get(Calendar.DAY_OF_MONTH), DatatypeConstants.FIELD_UNDEFINED);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }

        nalogZaPrenos.getPodaciOPrenosu().setDatumValute(xmlGregorValute);

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
     * @param zaglavlje
     * @return
     */
    @RequestMapping(
            value = "/novo_stanje_proizvoda",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Boolean> novoStanjeProizvoda(@RequestBody Zaglavlje zaglavlje) {
        try {
            List<Stavka> stavke = stavkaService.findByZaglavlje(zaglavlje);
            for (Stavka stavka : stavke) {
                BigDecimal kolicina = stavka.getKolicina();
                Proizvod proizvod = stavka.getProizvod();
                proizvod.setKolicina(proizvod.getKolicina() - kolicina.longValue());
                Firma kupac = firmaService.findByPib(zaglavlje.getPibKupca());
                if (kupac != null) {
                    if (proizvodService.dodajIliIzmeniProizvod(proizvod) != null) {
                        Proizvod kupacProizvod = proizvodService.findByNazivAndFirma(proizvod.getNaziv(), kupac);
                        if (kupacProizvod != null) {
                            kupacProizvod.setKolicina(kupacProizvod.getKolicina() + kolicina.longValue());
                            proizvodService.dodajIliIzmeniProizvod(kupacProizvod);
                        } else {
                            Proizvod proizvod1 = new Proizvod();
                            proizvod1.setVersion(1);
                            proizvod1.setNaziv(proizvod.getNaziv());
                            proizvod1.setCena(proizvod.getCena());
                            proizvod1.setMera(proizvod.getMera());
                            proizvod1.setRabat(proizvod.getRabat());
                            proizvod1.setTip(proizvod.getTip());
                            proizvod1.setKolicina(kolicina.longValue());
                            proizvod1.setFirma(kupac);
                            proizvodService.dodajIliIzmeniProizvod(proizvod1);
                        }
                    } else {
                        System.out.println("unable to change proizvod");
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
     * @param zaglavlje
     * @return
     */
    @RequestMapping(
            value = "/potvrdiKupovinu",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ZaglavljeStavkeDTO> potvrdiKupovinu(@RequestBody Zaglavlje zaglavlje) {

        try {
            Zaglavlje zaglavljeRet = zaglavljeService.findByIdPoruke(zaglavlje.getIdPoruke());
            if (zaglavljeRet != null) {
                zaglavljeRet.setPotvrdjeno(true);

                zaglavljeRet = zaglavljeService.dodajIliIzmeniZaglavlje(zaglavljeRet);
                if (zaglavljeRet != null) {
                    List<Stavka> stavke = stavkaService.findByZaglavlje(zaglavljeRet);
                    ZaglavljeStavkeDTO zaglavljeStavkeDTO = new ZaglavljeStavkeDTO(zaglavlje, stavke);
                    new FirmaWebSocket().displayMessageToActiveUsers();
                    return new ResponseEntity<ZaglavljeStavkeDTO>(zaglavljeStavkeDTO, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }

    /**
     * Odbijanje kupovine od strane kupca ili prodavca
     * @param zaglavlje
     * @return
     */
    @RequestMapping(
            value = "/odbijKupovinu",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity odbijKupovinu(@RequestBody Zaglavlje zaglavlje) {
        try {
            //Zaglavlje zaglavljeRet = zaglavljeService.findByIdPoruke(zaglavlje.getIdPoruke());
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
    public ResponseEntity<FaktureDTO> mojeFakture(@RequestBody PibsDTO pibsDTO) {
        try {
            FaktureDTO fakture = getMojeFakture(pibsDTO.getMojPib(), pibsDTO.getDrugiPib());
            return new ResponseEntity<FaktureDTO>(fakture, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    private FaktureDTO getMojeFakture(String mojPib, String drugiPib) {
        Objects.requireNonNull(mojPib);
        Objects.requireNonNull(drugiPib);
        List<Zaglavlje> mojeFakture;
        if (mojPib.equals(drugiPib)) {
            mojeFakture = zaglavljeService.findByPibDobavljacaOrPibKupca(mojPib, drugiPib);
        } else {
            mojeFakture = zaglavljeService.findByPibDobavljacaAndPibKupca(mojPib, drugiPib);
        }

        List<ZaglavljeStavkeDTO> zaglavljeStavkeDTOS = new ArrayList<>();

        for (Zaglavlje zaglavlje : mojeFakture) {
            List <Stavka> stavke = stavkaService.findByZaglavlje(zaglavlje);
            zaglavljeStavkeDTOS.add(new ZaglavljeStavkeDTO(zaglavlje, stavke));
        }

        FaktureDTO webSocketFaktureDTO = new FaktureDTO();
        webSocketFaktureDTO.setZaglavljeStavkeDTOS(zaglavljeStavkeDTOS);
        webSocketFaktureDTO.setTip("ALL");

        return webSocketFaktureDTO;
    }

    @RequestMapping(
            value = "/faktureZaPotvrdu",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public FaktureDTO faktureZaPotvrdu(@RequestBody Firma firma) {
        List<Zaglavlje> zaglavljaUToku = zaglavljeService.findByPibDobavljacaAndZavrsenoIsFalseAndPotvrdjenoIsFalse(firma.getPib()); // TODO [SVS] fix this

        List<ZaglavljeStavkeDTO> zaglavljeStavkeDTOS = new ArrayList<>();

        for (Zaglavlje zaglavlje : zaglavljaUToku) {
            List <Stavka> stavke = stavkaService.findByZaglavlje(zaglavlje);
            zaglavljeStavkeDTOS.add(new ZaglavljeStavkeDTO(zaglavlje, stavke));
        }

        FaktureDTO webSocketFaktureDTO = new FaktureDTO();
        webSocketFaktureDTO.setZaglavljeStavkeDTOS(zaglavljeStavkeDTOS);
        webSocketFaktureDTO.setTip("ZA_POTVRDU");

        return webSocketFaktureDTO;
    }

    @RequestMapping(
            value = "/potvrdjeneFakture",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public FaktureDTO potvrdjeneFakture(@RequestBody Firma firma) {
        List<Zaglavlje> zaglavljaUToku = zaglavljeService.findByPibKupcaAndPotvrdjenoIsTrueAndZavrsenoIsFalse(firma.getPib());

        List<ZaglavljeStavkeDTO> zaglavljeStavkeDTOS = new ArrayList<>();

        for (Zaglavlje zaglavlje : zaglavljaUToku) {
            List <Stavka> stavke = stavkaService.findByZaglavlje(zaglavlje);
            zaglavljeStavkeDTOS.add(new ZaglavljeStavkeDTO(zaglavlje, stavke));
        }

        FaktureDTO webSocketFaktureDTO = new FaktureDTO();
        webSocketFaktureDTO.setZaglavljeStavkeDTOS(zaglavljeStavkeDTOS);
        webSocketFaktureDTO.setTip("POTVRDJENE");

        return webSocketFaktureDTO;
    }



}
