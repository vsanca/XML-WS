
package com.xml2017.schema.mt102;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import com.xml2017.schema.tipovi_podataka.TBanka;
import com.xml2017.schema.tipovi_podataka.TPojedinacnoPlacanje;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IDPoruke" type="{http://www.xml2017.com/schema/tipovi_podataka}TID"/>
 *         &lt;element name="BankaDuznika" type="{http://www.xml2017.com/schema/tipovi_podataka}TBanka"/>
 *         &lt;element name="BankaPoverioca" type="{http://www.xml2017.com/schema/tipovi_podataka}TBanka"/>
 *         &lt;element name="UkupanIznos" type="{http://www.xml2017.com/schema/tipovi_podataka}TDecimal_15_2"/>
 *         &lt;element name="SifraValute" type="{http://www.xml2017.com/schema/tipovi_podataka}TSifraValute"/>
 *         &lt;element name="DatumValute" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Datum" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="PojedinacnaPlacanja">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence maxOccurs="unbounded" minOccurs="0">
 *                   &lt;element name="PojedinacnoPlacanje" type="{http://www.xml2017.com/schema/tipovi_podataka}TPojedinacnoPlacanje"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "idPoruke",
    "bankaDuznika",
    "bankaPoverioca",
    "ukupanIznos",
    "sifraValute",
    "datumValute",
    "datum",
    "pojedinacnaPlacanja"
})
@XmlRootElement(name = "mt102")
public class Mt102 {

    @XmlElement(name = "IDPoruke", required = true)
    protected String idPoruke;
    @XmlElement(name = "BankaDuznika", required = true)
    protected TBanka bankaDuznika;
    @XmlElement(name = "BankaPoverioca", required = true)
    protected TBanka bankaPoverioca;
    @XmlElement(name = "UkupanIznos", required = true)
    protected BigDecimal ukupanIznos;
    @XmlElement(name = "SifraValute", required = true)
    protected String sifraValute;
    @XmlElement(name = "DatumValute", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumValute;
    @XmlElement(name = "Datum", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datum;
    @XmlElement(name = "PojedinacnaPlacanja", required = true)
    protected Mt102 .PojedinacnaPlacanja pojedinacnaPlacanja;

    /**
     * Gets the value of the idPoruke property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDPoruke() {
        return idPoruke;
    }

    /**
     * Sets the value of the idPoruke property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDPoruke(String value) {
        this.idPoruke = value;
    }

    /**
     * Gets the value of the bankaDuznika property.
     * 
     * @return
     *     possible object is
     *     {@link TBanka }
     *     
     */
    public TBanka getBankaDuznika() {
        return bankaDuznika;
    }

    /**
     * Sets the value of the bankaDuznika property.
     * 
     * @param value
     *     allowed object is
     *     {@link TBanka }
     *     
     */
    public void setBankaDuznika(TBanka value) {
        this.bankaDuznika = value;
    }

    /**
     * Gets the value of the bankaPoverioca property.
     * 
     * @return
     *     possible object is
     *     {@link TBanka }
     *     
     */
    public TBanka getBankaPoverioca() {
        return bankaPoverioca;
    }

    /**
     * Sets the value of the bankaPoverioca property.
     * 
     * @param value
     *     allowed object is
     *     {@link TBanka }
     *     
     */
    public void setBankaPoverioca(TBanka value) {
        this.bankaPoverioca = value;
    }

    /**
     * Gets the value of the ukupanIznos property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getUkupanIznos() {
        return ukupanIznos;
    }

    /**
     * Sets the value of the ukupanIznos property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setUkupanIznos(BigDecimal value) {
        this.ukupanIznos = value;
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
     * Gets the value of the datum property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatum() {
        return datum;
    }

    /**
     * Sets the value of the datum property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatum(XMLGregorianCalendar value) {
        this.datum = value;
    }

    /**
     * Gets the value of the pojedinacnaPlacanja property.
     * 
     * @return
     *     possible object is
     *     {@link Mt102 .PojedinacnaPlacanja }
     *     
     */
    public Mt102 .PojedinacnaPlacanja getPojedinacnaPlacanja() {
        return pojedinacnaPlacanja;
    }

    /**
     * Sets the value of the pojedinacnaPlacanja property.
     * 
     * @param value
     *     allowed object is
     *     {@link Mt102 .PojedinacnaPlacanja }
     *     
     */
    public void setPojedinacnaPlacanja(Mt102 .PojedinacnaPlacanja value) {
        this.pojedinacnaPlacanja = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence maxOccurs="unbounded" minOccurs="0">
     *         &lt;element name="PojedinacnoPlacanje" type="{http://www.xml2017.com/schema/tipovi_podataka}TPojedinacnoPlacanje"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "pojedinacnoPlacanje"
    })
    public static class PojedinacnaPlacanja {

        @XmlElement(name = "PojedinacnoPlacanje")
        protected List<TPojedinacnoPlacanje> pojedinacnoPlacanje;

        /**
         * Gets the value of the pojedinacnoPlacanje property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the pojedinacnoPlacanje property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPojedinacnoPlacanje().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TPojedinacnoPlacanje }
         * 
         * 
         */
        public List<TPojedinacnoPlacanje> getPojedinacnoPlacanje() {
            if (pojedinacnoPlacanje == null) {
                pojedinacnoPlacanje = new ArrayList<TPojedinacnoPlacanje>();
            }
            return this.pojedinacnoPlacanje;
        }

    }

}
