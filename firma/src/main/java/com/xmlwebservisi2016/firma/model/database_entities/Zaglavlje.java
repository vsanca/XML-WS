package com.xmlwebservisi2016.firma.model.database_entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by Svetozar Stojkovic on 6/12/2017.
 */
@Entity(name = "zaglavlje")
@Table(name = "zaglavlje")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Zaglavlje {

    @Id
    @Column(name = "id_poruke")
    protected String idPoruke;
    @Column(name = "naziv_dobavljaca")
    protected String nazivDobavljaca;
    @Column(name = "adresa_dobavljaca")
    protected String adresaDobavljaca;
    @Column(name = "pib_dobavljaca")
    protected String pibDobavljaca;
    @Column(name = "naziv_kupca")
    protected String nazivKupca;
    @Column(name = "adresa_kupca")
    protected String adresaKupca;
    @Column(name = "pib_kupca")
    protected String pibKupca;
    @Column(name = "broj_racuna")
    protected long brojRacuna;
    @Column(name = "datum_racuna")
    protected Date datumRacuna;
    @Column(name = "vrednost_robe")
    protected BigDecimal vrednostRobe;
    @Column(name = "vrednost_usluga")
    protected BigDecimal vrednostUsluga;
    @Column(name = "ukupno_roba_i_usluge")
    protected BigDecimal ukupnoRobaIUsluge;
    @Column(name = "ukupan_rabat")
    protected BigDecimal ukupanRabat;
    @Column(name = "ukupan_porez")
    protected BigDecimal ukupanPorez;
    @Column(name = "oznaka_valute")
    protected String oznakaValute;
    @Column(name = "iznos_za_uplatu")
    protected BigDecimal iznosZaUplatu;
    @Column(name = "uplata_na_racun")
    protected String uplataNaRacun;
    @Column(name = "datum_valute")
    protected Date datumValute;
    @Column(name = "zavrseno")
    protected boolean zavrseno;

    public Zaglavlje() {
    }

    public Zaglavlje(String idPoruke, String nazivDobavljaca, String adresaDobavljaca, String pibDobavljaca, String nazivKupca, String adresaKupca, String pibKupca, long brojRacuna, Date datumRacuna, BigDecimal vrednostRobe, BigDecimal vrednostUsluga, BigDecimal ukupnoRobaIUsluge, BigDecimal ukupanRabat, BigDecimal ukupanPorez, String oznakaValute, BigDecimal iznosZaUplatu, String uplataNaRacun, Date datumValute, boolean zavrseno) {
        this.idPoruke = idPoruke;
        this.nazivDobavljaca = nazivDobavljaca;
        this.adresaDobavljaca = adresaDobavljaca;
        this.pibDobavljaca = pibDobavljaca;
        this.nazivKupca = nazivKupca;
        this.adresaKupca = adresaKupca;
        this.pibKupca = pibKupca;
        this.brojRacuna = brojRacuna;
        this.datumRacuna = datumRacuna;
        this.vrednostRobe = vrednostRobe;
        this.vrednostUsluga = vrednostUsluga;
        this.ukupnoRobaIUsluge = ukupnoRobaIUsluge;
        this.ukupanRabat = ukupanRabat;
        this.ukupanPorez = ukupanPorez;
        this.oznakaValute = oznakaValute;
        this.iznosZaUplatu = iznosZaUplatu;
        this.uplataNaRacun = uplataNaRacun;
        this.datumValute = datumValute;
        this.zavrseno = zavrseno;
    }

    public String getIdPoruke() {
        return idPoruke;
    }

    public void setIdPoruke(String idPoruke) {
        this.idPoruke = idPoruke;
    }

    public String getNazivDobavljaca() {
        return nazivDobavljaca;
    }

    public void setNazivDobavljaca(String nazivDobavljaca) {
        this.nazivDobavljaca = nazivDobavljaca;
    }

    public String getAdresaDobavljaca() {
        return adresaDobavljaca;
    }

    public void setAdresaDobavljaca(String adresaDobavljaca) {
        this.adresaDobavljaca = adresaDobavljaca;
    }

    public String getPibDobavljaca() {
        return pibDobavljaca;
    }

    public void setPibDobavljaca(String pibDobavljaca) {
        this.pibDobavljaca = pibDobavljaca;
    }

    public String getNazivKupca() {
        return nazivKupca;
    }

    public void setNazivKupca(String nazivKupca) {
        this.nazivKupca = nazivKupca;
    }

    public String getAdresaKupca() {
        return adresaKupca;
    }

    public void setAdresaKupca(String adresaKupca) {
        this.adresaKupca = adresaKupca;
    }

    public String getPibKupca() {
        return pibKupca;
    }

    public void setPibKupca(String pibKupca) {
        this.pibKupca = pibKupca;
    }

    public long getBrojRacuna() {
        return brojRacuna;
    }

    public void setBrojRacuna(long brojRacuna) {
        this.brojRacuna = brojRacuna;
    }

    public Date getDatumRacuna() {
        return datumRacuna;
    }

    public void setDatumRacuna(Date datumRacuna) {
        this.datumRacuna = datumRacuna;
    }

    public BigDecimal getVrednostRobe() {
        return vrednostRobe;
    }

    public void setVrednostRobe(BigDecimal vrednostRobe) {
        this.vrednostRobe = vrednostRobe;
    }

    public BigDecimal getVrednostUsluga() {
        return vrednostUsluga;
    }

    public void setVrednostUsluga(BigDecimal vrednostUsluga) {
        this.vrednostUsluga = vrednostUsluga;
    }

    public BigDecimal getUkupnoRobaIUsluge() {
        return ukupnoRobaIUsluge;
    }

    public void setUkupnoRobaIUsluge(BigDecimal ukupnoRobaIUsluge) {
        this.ukupnoRobaIUsluge = ukupnoRobaIUsluge;
    }

    public BigDecimal getUkupanRabat() {
        return ukupanRabat;
    }

    public void setUkupanRabat(BigDecimal ukupanRabat) {
        this.ukupanRabat = ukupanRabat;
    }

    public BigDecimal getUkupanPorez() {
        return ukupanPorez;
    }

    public void setUkupanPorez(BigDecimal ukupanPorez) {
        this.ukupanPorez = ukupanPorez;
    }

    public String getOznakaValute() {
        return oznakaValute;
    }

    public void setOznakaValute(String oznakaValute) {
        this.oznakaValute = oznakaValute;
    }

    public BigDecimal getIznosZaUplatu() {
        return iznosZaUplatu;
    }

    public void setIznosZaUplatu(BigDecimal iznosZaUplatu) {
        this.iznosZaUplatu = iznosZaUplatu;
    }

    public String getUplataNaRacun() {
        return uplataNaRacun;
    }

    public void setUplataNaRacun(String uplataNaRacun) {
        this.uplataNaRacun = uplataNaRacun;
    }

    public Date getDatumValute() {
        return datumValute;
    }

    public void setDatumValute(Date datumValute) {
        this.datumValute = datumValute;
    }

    public boolean isZavrseno() {
        return zavrseno;
    }

    public void setZavrseno(boolean zavrseno) {
        this.zavrseno = zavrseno;
    }
}
