package com.xmlwebservisi2016.firma.model.database_entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.xmlwebservisi2016.firma.model.jaxb.faktura_zaglavlje.FakturaZaglavlje;
import com.xmlwebservisi2016.firma.model.jaxb.prenos.NalogZaPrenos;
import com.xmlwebservisi2016.firma.model.jaxb.tipovi_podataka.TOsobaPrenos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Svetozar Stojkovic on 6/9/2017.
 */
@Entity(name = "firma")
@Table(name = "firma")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Firma implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "fid")
    private long id;

    @Column(name = "banka_port")
    private String bankaPort;

    @Column(name = "fname")
    private String name;

    @Column(name = "fadresa")
    private String adresa;

    @Column(name = "fbroj_racuna")
    private String brojRacuna;

    @Column(name = "fpoziv_na_broj")
    private String pozivNaBroj;

    @Column(name = "fmodel")
    private long model;

    @Column(name = "fpib")
    private String pib;

    @Version
    private int version;

    public Firma() {}

    public Firma(String bankaPort, String name, String adresa, String brojRacuna, String pozivNaBroj, long model, String pib, int version) {
        this.bankaPort = bankaPort;
        this.name = name;
        this.adresa = adresa;
        this.brojRacuna = brojRacuna;
        this.pozivNaBroj = pozivNaBroj;
        this.model = model;
        this.pib = pib;
        this.version = version;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBankPort() {
        return bankaPort;
    }

    public void setBankaPort(String banka_port) {
        this.bankaPort = banka_port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getBrojRacuna() {
        return brojRacuna;
    }

    public void setBrojRacuna(String brojRacuna) {
        this.brojRacuna = brojRacuna;
    }

    public String getPozivNaBroj() {
        return pozivNaBroj;
    }

    public void setPozivNaBroj(String pozivNaBroj) {
        this.pozivNaBroj = pozivNaBroj;
    }

    public long getModel() {
        return model;
    }

    public void setModel(long model) {
        this.model = model;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getPib() {
        return pib;
    }

    public void setPib(String pib) {
        this.pib = pib;
    }

    public void kupiProizvod(FakturaZaglavlje fakturaZaglavlje) {
        NalogZaPrenos nalogZaPrenos = new NalogZaPrenos();
        nalogZaPrenos.setIdPoruke(UUID.randomUUID().toString());
        nalogZaPrenos.setSvrhaPlacanja("kupovina");
        nalogZaPrenos.setDuznikNalogodavac(fakturaZaglavlje.getNazivKupca());
        NalogZaPrenos.PodaciOPrenosu podaciOPrenosu = new NalogZaPrenos.PodaciOPrenosu();
        TOsobaPrenos duznik = new TOsobaPrenos();
    }
}
