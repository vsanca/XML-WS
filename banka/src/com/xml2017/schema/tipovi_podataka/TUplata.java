
package com.xml2017.schema.tipovi_podataka;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for TUplata complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TUplata">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="duznik_nalogodavac" type="{http://www.xml2017.com/schema/tipovi_podataka}TString255"/>
 *         &lt;element name="svrha_placanja" type="{http://www.xml2017.com/schema/tipovi_podataka}TString255"/>
 *         &lt;element name="primalac_poverilac" type="{http://www.xml2017.com/schema/tipovi_podataka}TString255"/>
 *         &lt;element name="datum_naloga" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="datum_valute" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="duznik_zaduzenje" type="{http://www.xml2017.com/schema/tipovi_podataka}TPlacanje"/>
 *         &lt;element name="poverilac_odobrenje" type="{http://www.xml2017.com/schema/tipovi_podataka}TPlacanje"/>
 *         &lt;element name="iznos" type="{http://www.xml2017.com/schema/tipovi_podataka}TDecimal_15_2"/>
 *         &lt;element name="sifra_valute" type="{http://www.xml2017.com/schema/tipovi_podataka}TSifraValute"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TUplata", propOrder = {
    "duznikNalogodavac",
    "svrhaPlacanja",
    "primalacPoverilac",
    "datumNaloga",
    "datumValute",
    "duznikZaduzenje",
    "poverilacOdobrenje",
    "iznos",
    "sifraValute"
})
public class TUplata {

    @XmlElement(name = "duznik_nalogodavac", required = true)
    protected String duznikNalogodavac;
    @XmlElement(name = "svrha_placanja", required = true)
    protected String svrhaPlacanja;
    @XmlElement(name = "primalac_poverilac", required = true)
    protected String primalacPoverilac;
    @XmlElement(name = "datum_naloga", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumNaloga;
    @XmlElement(name = "datum_valute", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumValute;
    @XmlElement(name = "duznik_zaduzenje", required = true)
    protected TPlacanje duznikZaduzenje;
    @XmlElement(name = "poverilac_odobrenje", required = true)
    protected TPlacanje poverilacOdobrenje;
    @XmlElement(required = true)
    protected BigDecimal iznos;
    @XmlElement(name = "sifra_valute", required = true)
    protected String sifraValute;

    /**
     * Gets the value of the duznikNalogodavac property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDuznikNalogodavac() {
        return duznikNalogodavac;
    }

    /**
     * Sets the value of the duznikNalogodavac property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDuznikNalogodavac(String value) {
        this.duznikNalogodavac = value;
    }

    /**
     * Gets the value of the svrhaPlacanja property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSvrhaPlacanja() {
        return svrhaPlacanja;
    }

    /**
     * Sets the value of the svrhaPlacanja property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSvrhaPlacanja(String value) {
        this.svrhaPlacanja = value;
    }

    /**
     * Gets the value of the primalacPoverilac property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimalacPoverilac() {
        return primalacPoverilac;
    }

    /**
     * Sets the value of the primalacPoverilac property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimalacPoverilac(String value) {
        this.primalacPoverilac = value;
    }

    /**
     * Gets the value of the datumNaloga property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatumNaloga() {
        return datumNaloga;
    }

    /**
     * Sets the value of the datumNaloga property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumNaloga(XMLGregorianCalendar value) {
        this.datumNaloga = value;
    }

    /**
     * Gets the value of the datumValute property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatumValute() {
        return datumValute;
    }

    /**
     * Sets the value of the datumValute property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumValute(XMLGregorianCalendar value) {
        this.datumValute = value;
    }

    /**
     * Gets the value of the duznikZaduzenje property.
     * 
     * @return
     *     possible object is
     *     {@link TPlacanje }
     *     
     */
    public TPlacanje getDuznikZaduzenje() {
        return duznikZaduzenje;
    }

    /**
     * Sets the value of the duznikZaduzenje property.
     * 
     * @param value
     *     allowed object is
     *     {@link TPlacanje }
     *     
     */
    public void setDuznikZaduzenje(TPlacanje value) {
        this.duznikZaduzenje = value;
    }

    /**
     * Gets the value of the poverilacOdobrenje property.
     * 
     * @return
     *     possible object is
     *     {@link TPlacanje }
     *     
     */
    public TPlacanje getPoverilacOdobrenje() {
        return poverilacOdobrenje;
    }

    /**
     * Sets the value of the poverilacOdobrenje property.
     * 
     * @param value
     *     allowed object is
     *     {@link TPlacanje }
     *     
     */
    public void setPoverilacOdobrenje(TPlacanje value) {
        this.poverilacOdobrenje = value;
    }

    /**
     * Gets the value of the iznos property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIznos() {
        return iznos;
    }

    /**
     * Sets the value of the iznos property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIznos(BigDecimal value) {
        this.iznos = value;
    }

    /**
     * Gets the value of the sifraValute property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSifraValute() {
        return sifraValute;
    }

    /**
     * Sets the value of the sifraValute property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSifraValute(String value) {
        this.sifraValute = value;
    }

}
