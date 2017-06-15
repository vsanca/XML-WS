package com.xmlwebservisi2016.firma.controller;

import com.xmlwebservisi2016.firma.dto.ZahtevDTO;
import com.xmlwebservisi2016.firma.model.database_entities.Firma;
import com.xmlwebservisi2016.firma.model.jaxb.tipovi_podataka.TOsobaPrenos;
import com.xmlwebservisi2016.firma.model.jaxb.zahtev.ZahtevZaIzvod;
import com.xmlwebservisi2016.firma.model.jaxb.izvod.Izvod;
import com.xmlwebservisi2016.firma.service.BankaService;
import com.xmlwebservisi2016.firma.service.FirmaService;
import com.xmlwebservisi2016.firma.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Svetozar Stojkovic on 6/8/2017.
 */
@RestController
public class IzvodController  {

    @Autowired
    private BankaService bankaService;

    @Autowired
    private FirmaService firmaService;

    @RequestMapping(
            value = "/salji_zahtev_za_izvod",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Izvod> saljiZahtevZaIzvod(@RequestBody ZahtevDTO zahtevDTO) {

        Firma firma = firmaService.findById(zahtevDTO.getFid());
        if (firma != null) {

            GregorianCalendar gregor = new GregorianCalendar();
            Date date = new Date(zahtevDTO.getDate().getTime());
            gregor.setTime(date);
            XMLGregorianCalendar xmlGregor = null;
            try {
                xmlGregor = DatatypeFactory.newInstance().newXMLGregorianCalendarDate(
                        gregor.get(Calendar.YEAR), gregor.get(Calendar.MONTH)+1,
                        gregor.get(Calendar.DAY_OF_MONTH), DatatypeConstants.FIELD_UNDEFINED);
            } catch (DatatypeConfigurationException e) {
                e.printStackTrace();
            }

            Izvod outputIzvod = new Izvod();
            outputIzvod.setZaglavlje(new Izvod.Zaglavlje());
            outputIzvod.getZaglavlje().setUkupnoUKorist(BigDecimal.valueOf(0));
            outputIzvod.getZaglavlje().setUkupnoNaTeret(BigDecimal.valueOf(0));
            outputIzvod.getZaglavlje().setBrojPromenaNaTeret(0);
            outputIzvod.getZaglavlje().setBrojPromenaUKorist(0);

            Izvod.Presek presek = new Izvod.Presek();
            outputIzvod.setPresek(presek);
            Izvod izvod = new Izvod();

            int i=0;
            while (izvod != null) {
                ZahtevZaIzvod zahtevZaIzvod = new ZahtevZaIzvod();

                zahtevZaIzvod.setBrojRacuna(firma.getBrojRacuna());

                System.out.println("Date is: "+xmlGregor.toString());

                zahtevZaIzvod.setDatum(xmlGregor);
                zahtevZaIzvod.setRedniBrojPreseka(i);

                izvod = bankaService.preuzimanjeIzvoda(zahtevZaIzvod, firma);

                if (izvod != null) {
                    outputIzvod.getZaglavlje().setBrojPreseka(i);
                    outputIzvod.getZaglavlje().setBrojRacuna(izvod.getZaglavlje().getBrojRacuna());
                    outputIzvod.getZaglavlje().setDatumNaloga(izvod.getZaglavlje().getDatumNaloga());
                    outputIzvod.getZaglavlje().setNovoStanje(izvod.getZaglavlje().getNovoStanje());
                    outputIzvod.getZaglavlje().setPrethodnoStanje(izvod.getZaglavlje().getPrethodnoStanje());

                    outputIzvod.getZaglavlje().setUkupnoUKorist(outputIzvod.getZaglavlje().getUkupnoUKorist().add(izvod.getZaglavlje().getUkupnoUKorist()));
                    outputIzvod.getZaglavlje().setUkupnoNaTeret(outputIzvod.getZaglavlje().getUkupnoNaTeret().add(izvod.getZaglavlje().getUkupnoNaTeret()));
                    outputIzvod.getZaglavlje().setBrojPromenaUKorist(outputIzvod.getZaglavlje().getBrojPromenaUKorist() + izvod.getZaglavlje().getBrojPromenaUKorist());
                    outputIzvod.getZaglavlje().setBrojPromenaUKorist(outputIzvod.getZaglavlje().getBrojPromenaNaTeret() + izvod.getZaglavlje().getBrojPromenaNaTeret());

                    outputIzvod.getPresek().getStavkaPreseka().addAll(izvod.getPresek().getStavkaPreseka());
                }
            }

            return new ResponseEntity<Izvod>(outputIzvod, HttpStatus.OK);
        }

        // ovo zakomentarisi - odavde
//        Izvod outputIzvod = new Izvod();
//        outputIzvod.setZaglavlje(new Izvod.Zaglavlje());
//        outputIzvod.getZaglavlje().setUkupnoUKorist(BigDecimal.valueOf(0));
//        outputIzvod.getZaglavlje().setUkupnoNaTeret(BigDecimal.valueOf(0));
//        outputIzvod.getZaglavlje().setBrojPromenaNaTeret(0);
//        outputIzvod.getZaglavlje().setBrojPromenaUKorist(0);
//
//        outputIzvod.setPresek(new Izvod.Presek());
//
//        for (int i = 0; i < 10; i++) {
//            outputIzvod.getZaglavlje().setBrojPreseka(i);
//            outputIzvod.getZaglavlje().setBrojRacuna("111-1111111111111-11");
//            outputIzvod.getZaglavlje().setDatumNaloga(Converter.fromDateToXMLGregorianCalendar(System.currentTimeMillis()));
//            outputIzvod.getZaglavlje().setNovoStanje(BigDecimal.valueOf(123.2));
//            outputIzvod.getZaglavlje().setPrethodnoStanje(BigDecimal.valueOf(1234.2));
//
//            outputIzvod.getZaglavlje().setUkupnoUKorist(BigDecimal.valueOf(21));
//            outputIzvod.getZaglavlje().setUkupnoNaTeret(BigDecimal.valueOf(12));
//            outputIzvod.getZaglavlje().setBrojPromenaUKorist(1221);
//            outputIzvod.getZaglavlje().setBrojPromenaUKorist(123);
//
//
//            List<Izvod.Presek.StavkaPreseka> stavke = new ArrayList<>();
//
//            for (int j = 0; j < 5; j++) {
//                Izvod.Presek.StavkaPreseka stavkaPreseka = new Izvod.Presek.StavkaPreseka();
//                Izvod.Presek.StavkaPreseka.PodaciOPrenosu pop = new Izvod.Presek.StavkaPreseka.PodaciOPrenosu();
//                pop.setDatumValute(Converter.fromDateToXMLGregorianCalendar(System.currentTimeMillis()));
//
//                TOsobaPrenos osobaPrenos = new TOsobaPrenos();
//                osobaPrenos.setModel(13);
//                osobaPrenos.setPozivNaBroj("123");
//                osobaPrenos.setBrojRacuna("111-11111111111111-11");
//                pop.setDuznikPrenos(osobaPrenos);
//                pop.setPoverilacPrenos(osobaPrenos);
//
//                pop.setIznos(BigDecimal.valueOf(4567));
//
//                stavkaPreseka.setPodaciOPrenosu(pop);
//                stavkaPreseka.setDatumNaloga(Converter.fromDateToXMLGregorianCalendar(System.currentTimeMillis()));
//                stavkaPreseka.setDuznikNalogodavac("firma bla bla");
//                stavkaPreseka.setPrimalacPoverilac("firma bla bluc");
//                stavkaPreseka.setSmer("asdasd");
//                stavkaPreseka.setSvrhaPlacanja("ja kupila na pijaci");
//
//                stavke.add(stavkaPreseka);
//            }
//            outputIzvod.getPresek().getStavkaPreseka().addAll(stavke);
//        }
//
//        return new ResponseEntity<Izvod>(outputIzvod,HttpStatus.OK);
//        // - dovde
        return new ResponseEntity<Izvod>(HttpStatus.FORBIDDEN);  // otkomentarisi ovu liniju

    }

}
