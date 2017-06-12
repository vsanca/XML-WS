package com.xmlwebservisi2016.firma.model.database_entities;

import java.io.Serializable;

/**
 * Created by Svetozar Stojkovic on 6/12/2017.
 */
public class ProizvodPK implements Serializable {

    private String naziv;
    private Firma firma;

    public ProizvodPK() {
    }

    public ProizvodPK(String naziv, Firma firma) {
        this.naziv = naziv;
        this.firma = firma;
    }

    public String getPnaziv() {
        return naziv;
    }

    public void setPnaziv(String naziv) {
        this.naziv = naziv;
    }

    public Firma getFirma() {
        return firma;
    }

    public void setFirma(Firma firma) {
        this.firma = firma;
    }
}
