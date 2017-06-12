package com.xmlwebservisi2016.firma.model.database_entities;

import java.io.Serializable;

/**
 * Created by Svetozar Stojkovic on 6/12/2017.
 */
public class StavkaPK implements Serializable {

    private int redniBroj;
    private Zaglavlje zaglavlje;

    public StavkaPK() {
    }

    public StavkaPK(int redniBroj, Zaglavlje zaglavlje) {
        this.redniBroj = redniBroj;
        this.zaglavlje = zaglavlje;
    }

    public int getRedniBroj() {
        return redniBroj;
    }

    public void setRedniBroj(int redniBroj) {
        this.redniBroj = redniBroj;
    }

    public Zaglavlje getZaglavlje() {
        return zaglavlje;
    }

    public void setZaglavlje(Zaglavlje zaglavlje) {
        this.zaglavlje = zaglavlje;
    }
}
