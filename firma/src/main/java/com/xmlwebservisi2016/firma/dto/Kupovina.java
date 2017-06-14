package com.xmlwebservisi2016.firma.dto;

import com.xmlwebservisi2016.firma.model.database_entities.Proizvod;

import java.util.List;
import java.util.Map;

/**
 * Created by Svetozar Stojkovic on 6/10/2017.
 */
public class Kupovina {

    private long kupacID;
    private List<Proizvod> proizvodi;
    private List<Long> kolicine;
    private String oznakaValute;


    public Kupovina() {
    }

    public Kupovina(long kupacID, List<Proizvod> proizvodi, List<Long> kolicine, String oznakaValute) {
        this.kupacID = kupacID;
        this.proizvodi = proizvodi;
        this.kolicine = kolicine;
        this.oznakaValute = oznakaValute;
    }

    public long getKupacID() {
        return kupacID;
    }

    public void setKupacID(long kupacID) {
        this.kupacID = kupacID;
    }

    public List<Proizvod> getProizvodi() {
        return proizvodi;
    }

    public void setProizvodi(List<Proizvod> proizvodi) {
        this.proizvodi = proizvodi;
    }

    public String getOznakaValute() {
        return oznakaValute;
    }

    public void setOznakaValute(String oznakaValute) {
        this.oznakaValute = oznakaValute;
    }

    public List<Long> getKolicine() {
        return kolicine;
    }

    public void setKolicine(List<Long> kolicine) {
        this.kolicine = kolicine;
    }
}
