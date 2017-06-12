package com.xmlwebservisi2016.firma.dto;

import com.xmlwebservisi2016.firma.model.database_entities.Proizvod;

import java.util.Map;

/**
 * Created by Svetozar Stojkovic on 6/10/2017.
 */
public class Kupovina {

    private long kupacID;
    private Map<Proizvod, Long> proizvodi;
    private String oznakaValute;


    public Kupovina() {
    }

    public Kupovina(long kupacID, Map<Proizvod, Long> proizvodi, String oznakaValute) {
        this.kupacID = kupacID;
        this.proizvodi = proizvodi;
        this.oznakaValute = oznakaValute;
    }

    public long getKupacID() {
        return kupacID;
    }

    public void setKupacID(long kupacID) {
        this.kupacID = kupacID;
    }

    public Map<Proizvod, Long> getProizvodi() {
        return proizvodi;
    }

    public void setProizvodi(Map<Proizvod, Long> proizvodi) {
        this.proizvodi = proizvodi;
    }

    public String getOznakaValute() {
        return oznakaValute;
    }

    public void setOznakaValute(String oznakaValute) {
        this.oznakaValute = oznakaValute;
    }
}
