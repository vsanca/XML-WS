package com.xmlwebservisi2016.firma.dto;

import com.xmlwebservisi2016.firma.model.database_entities.Firma;
import com.xmlwebservisi2016.firma.model.database_entities.User;

/**
 * Created by Svetozar Stojkovic on 6/9/2017.
 */
public class UserFirmaDTO {

    private User user;
    private Firma firma;

    public UserFirmaDTO() {

    }

    public UserFirmaDTO(User user, Firma firma) {
        this.user = user;
        this.firma = firma;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Firma getFirma() {
        return firma;
    }

    public void setFirma(Firma firma) {
        this.firma = firma;
    }
}
