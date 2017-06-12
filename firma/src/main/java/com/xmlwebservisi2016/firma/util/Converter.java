package com.xmlwebservisi2016.firma.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xmlwebservisi2016.firma.model.database_entities.Stavka;
import com.xmlwebservisi2016.firma.model.database_entities.Zaglavlje;
import com.xmlwebservisi2016.firma.model.jaxb.faktura_zaglavlje.FakturaZaglavlje;
import com.xmlwebservisi2016.firma.model.jaxb.tipovi_podataka.TFakturaStavka;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Svetozar Stojkovic on 6/12/2017.
 */
public class Converter {

    private Converter() {
        throw new AssertionError("This class should not be instantiated");
    }

    public static FakturaZaglavlje fromZaglavljeToFakturaZaglavlje(Zaglavlje zaglavlje) {
        FakturaZaglavlje fakturaZaglavlje = new FakturaZaglavlje();
        fakturaZaglavlje.setIdPoruke(zaglavlje.getIdPoruke());

        fakturaZaglavlje.setNazivDobavljaca(zaglavlje.getNazivDobavljaca());
        fakturaZaglavlje.setAdresaDobavljaca(zaglavlje.getAdresaDobavljaca());
        fakturaZaglavlje.setPibDobavljaca(zaglavlje.getPibDobavljaca());

        fakturaZaglavlje.setNazivKupca(zaglavlje.getNazivKupca());
        fakturaZaglavlje.setAdresaKupca(zaglavlje.getAdresaKupca());
        fakturaZaglavlje.setPibKupca(zaglavlje.getPibKupca());

        fakturaZaglavlje.setBrojRacuna(zaglavlje.getBrojRacuna());
        fakturaZaglavlje.setDatumRacuna(fromDateToXMLGregorianCalendar(zaglavlje.getDatumRacuna().getTime()));
        fakturaZaglavlje.setVrednostRobe(zaglavlje.getVrednostRobe());
        fakturaZaglavlje.setVrednostUsluga(zaglavlje.getVrednostUsluga());
        fakturaZaglavlje.setUkupnoRobaIUsluge(zaglavlje.getUkupnoRobaIUsluge());
        fakturaZaglavlje.setUkupanRabat(zaglavlje.getUkupanRabat());
        fakturaZaglavlje.setUkupanPorez(zaglavlje.getUkupanPorez());
        fakturaZaglavlje.setOznakaValute(zaglavlje.getOznakaValute());
        fakturaZaglavlje.setIznosZaUplatu(zaglavlje.getIznosZaUplatu());
        fakturaZaglavlje.setUplataNaRacun(zaglavlje.getUplataNaRacun());
        fakturaZaglavlje.setDatumValute(Converter.fromDateToXMLGregorianCalendar(zaglavlje.getDatumValute().getTime()));

        return fakturaZaglavlje;
    }

    public static Zaglavlje fromFakturaZaglavljeToZaglavlje(FakturaZaglavlje fakturaZaglavlje) {
        Zaglavlje zaglavlje = new Zaglavlje();
        zaglavlje.setIdPoruke(fakturaZaglavlje.getIdPoruke());

        zaglavlje.setNazivDobavljaca(fakturaZaglavlje.getNazivDobavljaca());
        zaglavlje.setAdresaDobavljaca(fakturaZaglavlje.getAdresaDobavljaca());
        zaglavlje.setPibDobavljaca(fakturaZaglavlje.getPibDobavljaca());

        zaglavlje.setNazivKupca(fakturaZaglavlje.getNazivKupca());
        zaglavlje.setAdresaKupca(fakturaZaglavlje.getAdresaKupca());
        zaglavlje.setPibKupca(fakturaZaglavlje.getPibKupca());

        zaglavlje.setBrojRacuna(fakturaZaglavlje.getBrojRacuna());
        zaglavlje.setDatumRacuna(new java.sql.Date(fakturaZaglavlje.getDatumRacuna().getMillisecond()));
        zaglavlje.setVrednostRobe(fakturaZaglavlje.getVrednostRobe());
        zaglavlje.setVrednostUsluga(fakturaZaglavlje.getVrednostUsluga());
        zaglavlje.setUkupnoRobaIUsluge(fakturaZaglavlje.getUkupnoRobaIUsluge());
        zaglavlje.setUkupanRabat(fakturaZaglavlje.getUkupanRabat());
        zaglavlje.setUkupanPorez(fakturaZaglavlje.getUkupanPorez());
        zaglavlje.setOznakaValute(fakturaZaglavlje.getOznakaValute());
        zaglavlje.setIznosZaUplatu(fakturaZaglavlje.getIznosZaUplatu());
        zaglavlje.setUplataNaRacun(fakturaZaglavlje.getUplataNaRacun());
        zaglavlje.setDatumValute(new java.sql.Date(fakturaZaglavlje.getDatumRacuna().getMillisecond()));
        zaglavlje.setZavrseno(false);

        return zaglavlje;
    }

    public static List<Stavka> fromFakturaZaglavljeToStavka(FakturaZaglavlje fakturaZaglavlje) {
        List<Stavka> stavke = new ArrayList<>();
        for (TFakturaStavka fakturaStavka : fakturaZaglavlje.getFakturaStavka()) {
            Stavka stavka = new Stavka();

            stavka.setRedniBroj(fakturaStavka.getRedniBroj());
            stavka.setNazivRobeIliUsluge(fakturaStavka.getNazivRobeIliUsluge());
            stavka.setKolicina(fakturaStavka.getKolicina());
            stavka.setJedinicaMere(fakturaStavka.getJedinicaMere());
            stavka.setJedinicnaCena(fakturaStavka.getJedinicnaCena());
            stavka.setVrednost(fakturaStavka.getVrednost());
            stavka.setProcenatRabata(fakturaStavka.getProcenatRabata());
            stavka.setIznosRabata(fakturaStavka.getIznosRabata());
            stavka.setUmanjenoZaRabat(fakturaStavka.getUmanjenoZaRabat());
            stavka.setUkupanPorez(fakturaStavka.getUkupanPorez());

            stavke.add(stavka);
        }
        return stavke;
    }

    public static TFakturaStavka fromStavkaToTFakturaStavka(Stavka stavka) {
        TFakturaStavka fakturaStavka = new TFakturaStavka();
        fakturaStavka.setRedniBroj(stavka.getRedniBroj());
        fakturaStavka.setNazivRobeIliUsluge(stavka.getNazivRobeIliUsluge());
        fakturaStavka.setKolicina(stavka.getKolicina());
        fakturaStavka.setJedinicaMere(stavka.getJedinicaMere());
        fakturaStavka.setJedinicnaCena(stavka.getJedinicnaCena());
        fakturaStavka.setVrednost(stavka.getVrednost());
        fakturaStavka.setProcenatRabata(stavka.getProcenatRabata());
        fakturaStavka.setIznosRabata(stavka.getIznosRabata());
        fakturaStavka.setUmanjenoZaRabat(stavka.getUmanjenoZaRabat());
        fakturaStavka.setUkupanPorez(stavka.getUkupanPorez());

        return  fakturaStavka;
    }

    public static XMLGregorianCalendar fromDateToXMLGregorianCalendar(long time) {
        try {
            GregorianCalendar c = new GregorianCalendar();
            c.setTime(new Date(time));
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getJSONString(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
