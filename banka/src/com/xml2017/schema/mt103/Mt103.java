
package com.xml2017.schema.mt103;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.xml2017.schema.tipovi_podataka.TBanka;
import com.xml2017.schema.tipovi_podataka.TUplata;


/**
 * <p>Java class for mt103 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="mt103">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id_poruke" type="{http://www.xml2017.com/schema/tipovi_podataka}TID"/>
 *         &lt;element name="banke">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="banka_duznika" type="{http://www.xml2017.com/schema/tipovi_podataka}TBanka"/>
 *                   &lt;element name="banka_poverioca" type="{http://www.xml2017.com/schema/tipovi_podataka}TBanka"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="uplata" type="{http://www.xml2017.com/schema/tipovi_podataka}TUplata"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mt103", propOrder = {
    "idPoruke",
    "banke",
    "uplata"
})
public class Mt103 {

    @XmlElement(name = "id_poruke", required = true)
    protected String idPoruke;
    @XmlElement(required = true)
    protected Mt103 .Banke banke;
    @XmlElement(required = true)
    protected TUplata uplata;

    /**
     * Gets the value of the idPoruke property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdPoruke() {
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
    public void setIdPoruke(String value) {
        this.idPoruke = value;
    }

    /**
     * Gets the value of the banke property.
     * 
     * @return
     *     possible object is
     *     {@link Mt103 .Banke }
     *     
     */
    public Mt103 .Banke getBanke() {
        return banke;
    }

    /**
     * Sets the value of the banke property.
     * 
     * @param value
     *     allowed object is
     *     {@link Mt103 .Banke }
     *     
     */
    public void setBanke(Mt103 .Banke value) {
        this.banke = value;
    }

    /**
     * Gets the value of the uplata property.
     * 
     * @return
     *     possible object is
     *     {@link TUplata }
     *     
     */
    public TUplata getUplata() {
        return uplata;
    }

    /**
     * Sets the value of the uplata property.
     * 
     * @param value
     *     allowed object is
     *     {@link TUplata }
     *     
     */
    public void setUplata(TUplata value) {
        this.uplata = value;
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
     *       &lt;sequence>
     *         &lt;element name="banka_duznika" type="{http://www.xml2017.com/schema/tipovi_podataka}TBanka"/>
     *         &lt;element name="banka_poverioca" type="{http://www.xml2017.com/schema/tipovi_podataka}TBanka"/>
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
        "bankaDuznika",
        "bankaPoverioca"
    })
    public static class Banke {

        @XmlElement(name = "banka_duznika", required = true)
        protected TBanka bankaDuznika;
        @XmlElement(name = "banka_poverioca", required = true)
        protected TBanka bankaPoverioca;

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

    }

}
