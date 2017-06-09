package com.xmlwebservisi2016.firma.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Svetozar Stojkovic on 6/9/2017.
 */
@Entity(name = "firma")
@Table(name = "firma")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Firma implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "fid")
    private long id;

    @Column(name = "fname")
    private String username;

}
