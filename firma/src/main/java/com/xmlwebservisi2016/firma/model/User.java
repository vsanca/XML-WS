package com.xmlwebservisi2016.firma.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Hp on 6/5/2017.
 */

@Entity(name = "appuser")
@Table(name = "appuser")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "uid")
    private long id;

    @Column(name = "uuname")
    private String username;

    @Column(name = "upword")
    private String password;

    @Version
    private int version;

    @Column(name = "fid")
    private String firma;

    public User() {}

    public User(String username, String password, int version) {
        this.username = username;
        this.password = password;
        this.version = version;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
