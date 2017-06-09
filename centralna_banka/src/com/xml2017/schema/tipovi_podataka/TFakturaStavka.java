
package com.xml2017.schema.tipovi_podataka;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for T_faktura_stavka complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="T_faktura_stavka">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="redni_broj" type="{http://www.xml2017.com/schema/tipovi_podataka}TFaktura_redni_broj"/>
 *         &lt;element name="naziv_robe_ili_usluge" type="{http://www.xml2017.com/schema/tipovi_podataka}TString120"/>
 *         &lt;element name="kolicina" type="{http://www.xml2017.com/schema/tipovi_podataka}TDecimal_10_2"/>
 *         &lt;element name="jedinica_mere" type="{http://www.xml2017.com/schema/tipovi_podataka}TString6"/>
 *         &lt;element name="jedinicna_cena" type="{http://www.xml2017.com/schema/tipovi_podataka}TDecimal_10_2"/>
 *         &lt;element name="vrednost" type="{http://www.xml2017.com/schema/tipovi_podataka}TDecimal_12_2"/>
 *         &lt;element name="procenat_rabata" type="{http://www.xml2017.com/schema/tipovi_podataka}TDecimal_5_2"/>
 *         &lt;element name="iznos_rabata" type="{http://www.xml2017.com/schema/tipovi_podataka}TDecimal_12_2"/>
 *         &lt;element name="umanjeno_za_rabat" type="{http://www.xml2017.com/schema/tipovi_podataka}TDecimal_12_2"/>
 *         &lt;element name="ukupan_porez" type="{http://www.xml2017.com/schema/tipovi_podataka}TDecimal_12_2"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "T_faktura_stavka", propOrder = {
    "redniBroj",
    "nazivRobeIliUsluge",
    "kolicina",
    "jedinicaMere",
    "jedinicnaCena",
    "vrednost",
    "procenatRabata",
    "iznosRabata",
    "umanjenoZaRabat",
    "ukupanPorez"
})
public class TFakturaStavka {

    @XmlElement(name = "redni_broj")
    protected int redniBroj;
    @XmlElement(name = "naziv_robe_ili_usluge", required = true)
    protected String nazivRobeIliUsluge;
    @XmlElement(required = true)
    protected BigDecimal kolicina;
    @XmlElement(name = "jedinica_mere", required = true)
    protected String jedinicaMere;
    @XmlElement(name = "jedinicna_cena", required = true)
    protected BigDecimal jedinicnaCena;
    @XmlElement(required = true)
    protected BigDecimal vrednost;
    @XmlElement(name = "procenat_rabata", required = true)
    protected BigDecimal procenatRabata;
    @XmlElement(name = "iznos_rabata", required = true)
    protected BigDecimal iznosRabata;
    @XmlElement(name = "umanjeno_za_rabat", required = true)
    protected BigDecimal umanjenoZaRabat;
    @XmlElement(name = "ukupan_porez", required = true)
    protected BigDecimal ukupanPorez;

    /**
     * Gets the value of the redniBroj property.
     * 
     */
    public int getRedniBroj() {
        return redniBroj;
    }

    /**
     * Sets the value of the redniBroj property.
     * 
     */
    public void setRedniBroj(int value) {
        this.redniBroj = value;
    }

    /**
     * Gets the value of the nazivRobeIliUsluge property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNazivRobeIliUsluge() {
        return nazivRobeIliUsluge;
    }

    /**
     * Sets the value of the nazivRobeIliUsluge property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNazivRobeIliUsluge(String value) {
        this.nazivRobeIliUsluge = value;
    }

    /**
     * Gets the value of the kolicina property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getKolicina() {
        return kolicina;
    }

    /**
     * Sets the value of the kolicina property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setKolicina(BigDecimal value) {
        this.kolicina = value;
    }

    /**
     * Gets the value of the jedinicaMere property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJedinicaMere() {
        return jedinicaMere;
    }

    /**
     * Sets the value of the jedinicaMere property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJedinicaMere(String value) {
        this.jedinicaMere = value;
    }

    /**
     * Gets the value of the jedinicnaCena property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getJedinicnaCena() {
        return jedinicnaCena;
    }

    /**
     * Sets the value of the jedinicnaCena property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setJedinicnaCena(BigDecimal value) {
        this.jedinicnaCena = value;
    }

    /**
     * Gets the value of the vrednost property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVrednost() {
        return vrednost;
    }

    /**
     * Sets the value of the vrednost property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVrednost(BigDecimal value) {
        this.vrednost = value;
    }

    /**
     * Gets the value of the procenatRabata property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getProcenatRabata() {
        return procenatRabata;
    }

    /**
     * Sets the value of the procenatRabata property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setProcenatRabata(BigDecimal value) {
        this.procenatRabata = value;
    }

    /**
     * Gets the value of the iznosRabata property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIznosRabata() {
        return iznosRabata;
    }

    /**
     * Sets the value of the iznosRabata property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIznosRabata(BigDecimal value) {
        this.iznosRabata = value;
    }

    /**
     * Gets the value of the umanjenoZaRabat property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getUmanjenoZaRabat() {
        return umanjenoZaRabat;
    }

    /**
     * Sets the value of the umanjenoZaRabat property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setUmanjenoZaRabat(BigDecimal value) {
        this.umanjenoZaRabat = value;
    }

    /**
     * Gets the value of the ukupanPorez property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getUkupanPorez() {
        return ukupanPorez;
    }

    /**
     * Sets the value of the ukupanPorez property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setUkupanPorez(BigDecimal value) {
        this.ukupanPorez = value;
    }

}
