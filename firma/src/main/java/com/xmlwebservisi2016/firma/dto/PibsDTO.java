package com.xmlwebservisi2016.firma.dto;

/**
 * Created by Svetozar Stojkovic on 6/12/2017.
 */
public class PibsDTO {

    private String mojPib;
    private String drugiPib;

    public PibsDTO() {
    }

    public PibsDTO(String mojPib, String drugiPib) {
        this.mojPib = mojPib;
        this.drugiPib = drugiPib;
    }

    public String getMojPib() {
        return mojPib;
    }

    public void setMojPib(String mojPib) {
        this.mojPib = mojPib;
    }

    public String getDrugiPib() {
        return drugiPib;
    }

    public void setDrugiPib(String drugiPib) {
        this.drugiPib = drugiPib;
    }
}
