package com.xmlwebservisi2016.firma.controller;

import com.xmlwebservisi2016.firma.dto.ZahtevDTO;
import com.xmlwebservisi2016.firma.model.database_entities.Firma;
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

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Svetozar Stojkovic on 6/8/2017.
 */
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

            Izvod outputIzvod = new Izvod();
            outputIzvod.getZaglavlje().setUkupnoUKorist(BigDecimal.valueOf(0));
            outputIzvod.getZaglavlje().setUkupnoNaTeret(BigDecimal.valueOf(0));
            outputIzvod.getZaglavlje().setBrojPromenaNaTeret(0);
            outputIzvod.getZaglavlje().setBrojPromenaUKorist(0);
            Izvod izvod = new Izvod();
            int i=0;
            while (izvod != null) {
                ZahtevZaIzvod zahtevZaIzvod = new ZahtevZaIzvod();

                zahtevZaIzvod.setBrojRacuna(firma.getBrojRacuna());
                zahtevZaIzvod.setDatum(Converter.fromDateToXMLGregorianCalendar(zahtevDTO.getDate().getTime()));
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

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);

    }

}
