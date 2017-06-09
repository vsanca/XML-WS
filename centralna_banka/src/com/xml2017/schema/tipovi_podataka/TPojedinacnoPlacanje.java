
package com.xml2017.schema.tipovi_podataka;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for TPojedinacnoPlacanje complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TPojedinacnoPlacanje">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IDNalogaZaPlacanje" type="{http://www.xml2017.com/schema/tipovi_podataka}TID"/>
 *         &lt;element name="Duznik-Nalogodavac" type="{http://www.xml2017.com/schema/tipovi_podataka}TString255"/>
 *         &lt;element name="SvrhaPlacanja" type="{http://www.xml2017.com/schema/tipovi_podataka}TString255"/>
 *         &lt;element name="Primalac-Poverilac" type="{http://www.xml2017.com/schema/tipovi_podataka}TString255"/>
 *         &lt;element name="DatumNaloga" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="RacunDuznika" type="{http://www.xml2017.com/schema/tipovi_podataka}TBrojRacunaBanke"/>
 *         &lt;element name="ModelZaduzenja" type="{http://www.xml2017.com/schema/tipovi_podataka}TModel"/>
 *         &lt;element name="PozivNaBrojZaduzenja" type="{http://www.xml2017.com/schema/tipovi_podataka}TPozivNaBroj"/>
 *         &lt;element name="RacunPoverioca" type="{http://www.xml2017.com/schema/tipovi_podataka}TBrojRacunaBanke"/>
 *         &lt;element name="ModelOdobrenja" type="{http://www.xml2017.com/schema/tipovi_podataka}TModel"/>
 *         &lt;element name="PozivNaBrojOdobrenja" type="{http://www.xml2017.com/schema/tipovi_podataka}TPozivNaBroj"/>
 *         &lt;element name="Iznos" type="{http://www.xml2017.com/schema/tipovi_podataka}TDecimal_15_2"/>
 *         &lt;element name="SifraValute" type="{http://www.xml2017.com/schema/tipovi_podataka}TSifraValute"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TPojedinacnoPlacanje", propOrder = {
    "idNalogaZaPlacanje",
    "duznikNalogodavac",
    "svrhaPlacanja",
    "primalacPoverilac",
    "datumNaloga",
    "racunDuznika",
    "modelZaduzenja",
    "pozivNaBrojZaduzenja",
    "racunPoverioca",
    "modelOdobrenja",
    "pozivNaBrojOdobrenja",
    "iznos",
    "sifraValute"
})
public class TPojedinacnoPlacanje {

    @XmlElement(name = "IDNalogaZaPlacanje", required = true)
    protected String idNalogaZaPlacanje;
    @XmlElement(name = "Duznik-Nalogodavac", required = true)
    protected String duznikNalogodavac;
    @XmlElement(name = "SvrhaPlacanja", required = true)
    protected String svrhaPlacanja;
    @XmlElement(name = "Primalac-Poverilac", required = true)
    protected String primalacPoverilac;
    @XmlElement(name = "DatumNaloga", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumNaloga;
    @XmlElement(name = "RacunDuznika", required = true)
    protected String racunDuznika;
    @XmlElement(name = "ModelZaduzenja")
    protected long modelZaduzenja;
    @XmlElement(name = "PozivNaBrojZaduzenja", required = true)
    protected String pozivNaBrojZaduzenja;
    @XmlElement(name = "RacunPoverioca", required = true)
    protected String racunPoverioca;
    @XmlElement(name = "ModelOdobrenja")
    protected long modelOdobrenja;
    @XmlElement(name = "PozivNaBrojOdobrenja", required = true)
    protected String pozivNaBrojOdobrenja;
    @XmlElement(name = "Iznos", required = true)
    protected BigDecimal iznos;
    @XmlElement(name = "SifraValute", required = true)
    protected String sifraValute;

    /**
     * Gets the value of the idNalogaZaPlacanje property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDNalogaZaPlacanje() {
        return idNalogaZaPlacanje;
    }

    /**
     * Sets the value of the idNalogaZaPlacanje property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDNalogaZaPlacanje(String value) {
        this.idNalogaZaPlacanje = value;
    }

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
     * Gets the value of the racunDuznika property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRacunDuznika() {
        return racunDuznika;
    }

    /**
     * Sets the value of the racunDuznika property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRacunDuznika(String value) {
        this.racunDuznika = value;
    }

    /**
     * Gets the value of the modelZaduzenja property.
     * 
     */
    public long getModelZaduzenja() {
        return modelZaduzenja;
    }

    /**
     * Sets the value of the modelZaduzenja property.
     * 
     */
    public void setModelZaduzenja(long value) {
        this.modelZaduzenja = value;
    }

    /**
     * Gets the value of the pozivNaBrojZaduzenja property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPozivNaBrojZaduzenja() {
        return pozivNaBrojZaduzenja;
    }

    /**
     * Sets the value of the pozivNaBrojZaduzenja property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPozivNaBrojZaduzenja(String value) {
        this.pozivNaBrojZaduzenja = value;
    }

    /**
     * Gets the value of the racunPoverioca property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRacunPoverioca() {
        return racunPoverioca;
    }

    /**
     * Sets the value of the racunPoverioca property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRacunPoverioca(String value) {
        this.racunPoverioca = value;
    }

    /**
     * Gets the value of the modelOdobrenja property.
     * 
     */
    public long getModelOdobrenja() {
        return modelOdobrenja;
    }

    /**
     * Sets the value of the modelOdobrenja property.
     * 
     */
    public void setModelOdobrenja(long value) {
        this.modelOdobrenja = value;
    }

    /**
     * Gets the value of the pozivNaBrojOdobrenja property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPozivNaBrojOdobrenja() {
        return pozivNaBrojOdobrenja;
    }

    /**
     * Sets the value of the pozivNaBrojOdobrenja property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPozivNaBrojOdobrenja(String value) {
        this.pozivNaBrojOdobrenja = value;
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
