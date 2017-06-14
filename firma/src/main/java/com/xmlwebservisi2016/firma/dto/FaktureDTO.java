package com.xmlwebservisi2016.firma.dto;

import com.xmlwebservisi2016.firma.model.database_entities.Stavka;
import com.xmlwebservisi2016.firma.model.database_entities.Zaglavlje;
import com.xmlwebservisi2016.firma.model.jaxb.faktura_zaglavlje.FakturaZaglavlje;

import java.util.List;

/**
 * Created by Svetozar Stojkovic on 6/14/2017.
 */
public class FaktureDTO {

    private String tip;
    private List<ZaglavljeStavkeDTO> zaglavljeStavkeDTOS;

    public FaktureDTO() {
    }

    public FaktureDTO(String tip, List<ZaglavljeStavkeDTO> zaglavljeStavkeDTOS) {
        this.tip = tip;
        this.zaglavljeStavkeDTOS = zaglavljeStavkeDTOS;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public List<ZaglavljeStavkeDTO> getZaglavljeStavkeDTOS() {
        return zaglavljeStavkeDTOS;
    }

    public void setZaglavljeStavkeDTOS(List<ZaglavljeStavkeDTO> zaglavljeStavkeDTOS) {
        this.zaglavljeStavkeDTOS = zaglavljeStavkeDTOS;
    }
}
