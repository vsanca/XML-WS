package com.xmlwebservisi2016.firma.dto;

import com.xmlwebservisi2016.firma.model.database_entities.Stavka;
import com.xmlwebservisi2016.firma.model.database_entities.Zaglavlje;

import java.util.List;

/**
 * Created by Svetozar Stojkovic on 6/14/2017.
 */
public class ZaglavljeStavkeDTO {
    private Zaglavlje zaglavlje;
    private List<Stavka> stavkaList;

    public ZaglavljeStavkeDTO() {
    }

    public ZaglavljeStavkeDTO(Zaglavlje zaglavlje, List<Stavka> stavkaList) {
        this.zaglavlje = zaglavlje;
        this.stavkaList = stavkaList;
    }

    public Zaglavlje getZaglavlje() {
        return zaglavlje;
    }

    public void setZaglavlje(Zaglavlje zaglavlje) {
        this.zaglavlje = zaglavlje;
    }

    public List<Stavka> getStavkaList() {
        return stavkaList;
    }

    public void setStavkaList(List<Stavka> stavkaList) {
        this.stavkaList = stavkaList;
    }
}
