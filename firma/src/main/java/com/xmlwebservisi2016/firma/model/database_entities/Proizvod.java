package com.xmlwebservisi2016.firma.model.database_entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Svetozar Stojkovic on 6/10/2017.
 */
@Entity(name = "proizvod")
@Table(name = "proizvod")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Proizvod implements Serializable {

    private static String USLUGA = "USLUGA";
    private static String ROBA = "ROBA";

    @Id
    @GeneratedValue
    @Column(name = "pid")
    private long id;

    @Column(name = "ptip")
    private String tip;


    @Column(name = "pname")
    private String naziv;

    @Column(name = "pmera")
    private String mera;

    @Column(name = "pcena")
    private double cena;

    @Column(name = "prabat")
    private double rabat;

    @Column(name = "pkolicina")
    private long kolicina;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fid", referencedColumnName = "fid")
    private Firma firma;


    @Version
    private int version;

    public Proizvod() {}

    public Proizvod(String tip, String naziv, String mera, double cena, double rabat, long kolicina, Firma firma, int version) {
        this.tip = tip;
        this.naziv = naziv;
        this.mera = mera;
        this.cena = cena;
        this.rabat = rabat;
        this.kolicina = kolicina;
        this.firma = firma;
        this.version = version;
    }

    public static String getUSLUGA() {
        return USLUGA;
    }

    public static void setUSLUGA(String USLUGA) {
        Proizvod.USLUGA = USLUGA;
    }

    public static String getROBA() {
        return ROBA;
    }

    public static void setROBA(String ROBA) {
        Proizvod.ROBA = ROBA;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getMera() {
        return mera;
    }

    public void setMera(String mera) {
        this.mera = mera;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public double getRabat() {
        return rabat;
    }

    public void setRabat(double rabat) {
        this.rabat = rabat;
    }

    public long getKolicina() {
        return kolicina;
    }

    public void setKolicina(long kolicina) {
        this.kolicina = kolicina;
    }

    public Firma getFirma() {
        return firma;
    }

    public void setFirma(Firma firma) {
        this.firma = firma;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public boolean ifRoba() {
        if (ROBA.equals(getTip())) {
            return true;
        }
        return false;
    }

    public boolean ifUsluga() {
        if (USLUGA.equals(getTip())) {
            return true;
        }
        return false;
    }
}
