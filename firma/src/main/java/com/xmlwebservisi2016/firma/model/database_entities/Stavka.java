package com.xmlwebservisi2016.firma.model.database_entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Svetozar Stojkovic on 6/12/2017.
 */
@Entity(name = "stavka")
@Table(name = "stavka")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Stavka implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "sid")
    protected long id;
    @Column(name = "redni_broj")
    protected int redniBroj;
    @Column(name = "naziv_robe_ili_usluge")
    protected String nazivRobeIliUsluge;
    @Column(name = "kolicina")
    protected BigDecimal kolicina;
    @Column(name = "jedinica_mere")
    protected String jedinicaMere;
    @Column(name = "jedinicna_cena")
    protected BigDecimal jedinicnaCena;
    @Column(name = "vrednost")
    protected BigDecimal vrednost;
    @Column(name = "procenat_rabata")
    protected BigDecimal procenatRabata;
    @Column(name = "iznos_rabata")
    protected BigDecimal iznosRabata;
    @Column(name = "umanjeno_za_rabat")
    protected BigDecimal umanjenoZaRabat;
    @Column(name = "ukupan_porez")
    protected BigDecimal ukupanPorez;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pid", referencedColumnName = "pid")
    protected Proizvod proizvod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_poruke", referencedColumnName = "id_poruke")
    protected Zaglavlje zaglavlje;

    public Stavka() {
    }

    public Stavka(int redniBroj, String nazivRobeIliUsluge, BigDecimal kolicina, String jedinicaMere, BigDecimal jedinicnaCena, BigDecimal vrednost, BigDecimal procenatRabata, BigDecimal iznosRabata, BigDecimal umanjenoZaRabat, BigDecimal ukupanPorez, Proizvod proizvod, Zaglavlje zaglavlje) {
        this.redniBroj = redniBroj;
        this.nazivRobeIliUsluge = nazivRobeIliUsluge;
        this.kolicina = kolicina;
        this.jedinicaMere = jedinicaMere;
        this.jedinicnaCena = jedinicnaCena;
        this.vrednost = vrednost;
        this.procenatRabata = procenatRabata;
        this.iznosRabata = iznosRabata;
        this.umanjenoZaRabat = umanjenoZaRabat;
        this.ukupanPorez = ukupanPorez;
        this.proizvod = proizvod;
        this.zaglavlje = zaglavlje;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRedniBroj() {
        return redniBroj;
    }

    public void setRedniBroj(int redniBroj) {
        this.redniBroj = redniBroj;
    }

    public String getNazivRobeIliUsluge() {
        return nazivRobeIliUsluge;
    }

    public void setNazivRobeIliUsluge(String nazivRobeIliUsluge) {
        this.nazivRobeIliUsluge = nazivRobeIliUsluge;
    }

    public BigDecimal getKolicina() {
        return kolicina;
    }

    public void setKolicina(BigDecimal kolicina) {
        this.kolicina = kolicina;
    }

    public String getJedinicaMere() {
        return jedinicaMere;
    }

    public void setJedinicaMere(String jedinicaMere) {
        this.jedinicaMere = jedinicaMere;
    }

    public BigDecimal getJedinicnaCena() {
        return jedinicnaCena;
    }

    public void setJedinicnaCena(BigDecimal jedinicnaCena) {
        this.jedinicnaCena = jedinicnaCena;
    }

    public BigDecimal getVrednost() {
        return vrednost;
    }

    public void setVrednost(BigDecimal vrednost) {
        this.vrednost = vrednost;
    }

    public BigDecimal getProcenatRabata() {
        return procenatRabata;
    }

    public void setProcenatRabata(BigDecimal procenatRabata) {
        this.procenatRabata = procenatRabata;
    }

    public BigDecimal getIznosRabata() {
        return iznosRabata;
    }

    public void setIznosRabata(BigDecimal iznosRabata) {
        this.iznosRabata = iznosRabata;
    }

    public BigDecimal getUmanjenoZaRabat() {
        return umanjenoZaRabat;
    }

    public void setUmanjenoZaRabat(BigDecimal umanjenoZaRabat) {
        this.umanjenoZaRabat = umanjenoZaRabat;
    }

    public BigDecimal getUkupanPorez() {
        return ukupanPorez;
    }

    public void setUkupanPorez(BigDecimal ukupanPorez) {
        this.ukupanPorez = ukupanPorez;
    }

    public Zaglavlje getZaglavlje() {
        return zaglavlje;
    }

    public void setZaglavlje(Zaglavlje zaglavlje) {
        this.zaglavlje = zaglavlje;
    }

    public Proizvod getProizvod() {
        return proizvod;
    }

    public void setProizvod(Proizvod proizvod) {
        this.proizvod = proizvod;
    }
}
