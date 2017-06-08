
package com.xml2017.schema.tipovi_podataka;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TPlacanje complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TPlacanje">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="racun" type="{http://www.xml2017.com/schema/tipovi_podataka}TBrojRacunaBanke"/>
 *         &lt;element name="model" type="{http://www.xml2017.com/schema/tipovi_podataka}TModel"/>
 *         &lt;element name="poziv_na_broj" type="{http://www.xml2017.com/schema/tipovi_podataka}TPozivNaBroj"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TPlacanje", propOrder = {
    "racun",
    "model",
    "pozivNaBroj"
})
public class TPlacanje {

    @XmlElement(required = true)
    protected String racun;
    protected long model;
    @XmlElement(name = "poziv_na_broj", required = true)
    protected String pozivNaBroj;

    /**
     * Gets the value of the racun property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRacun() {
        return racun;
    }

    /**
     * Sets the value of the racun property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRacun(String value) {
        this.racun = value;
    }

    /**
     * Gets the value of the model property.
     * 
     */
    public long getModel() {
        return model;
    }

    /**
     * Sets the value of the model property.
     * 
     */
    public void setModel(long value) {
        this.model = value;
    }

    /**
     * Gets the value of the pozivNaBroj property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPozivNaBroj() {
        return pozivNaBroj;
    }

    /**
     * Sets the value of the pozivNaBroj property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPozivNaBroj(String value) {
        this.pozivNaBroj = value;
    }

}
