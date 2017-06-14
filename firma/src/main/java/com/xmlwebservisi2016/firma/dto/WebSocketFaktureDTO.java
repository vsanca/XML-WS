package com.xmlwebservisi2016.firma.dto;

import com.xmlwebservisi2016.firma.model.jaxb.faktura_zaglavlje.FakturaZaglavlje;

import java.util.List;

/**
 * Created by Svetozar Stojkovic on 6/14/2017.
 */
public class WebSocketFaktureDTO {

    private String tip;
    private List<FakturaZaglavlje> fakturaZaglavljeList;

    public WebSocketFaktureDTO() {
    }

    public WebSocketFaktureDTO(String tip, List<FakturaZaglavlje> fakturaZaglavljeList) {
        this.tip = tip;
        this.fakturaZaglavljeList = fakturaZaglavljeList;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public List<FakturaZaglavlje> getFakturaZaglavljeList() {
        return fakturaZaglavljeList;
    }

    public void setFakturaZaglavljeList(List<FakturaZaglavlje> fakturaZaglavljeList) {
        this.fakturaZaglavljeList = fakturaZaglavljeList;
    }
}
