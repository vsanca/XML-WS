
package com.xml2017.schema.mt910;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import com.xml2017.schema.tipovi_podataka.TBanka;


/**
 * <p>Java class for mt910 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="mt910">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id_poruke" type="{http://www.xml2017.com/schema/tipovi_podataka}TID"/>
 *         &lt;element name="banka_poverilac" type="{http://www.xml2017.com/schema/tipovi_podataka}TBanka"/>
 *         &lt;element name="id_poruke_naloga" type="{http://www.xml2017.com/schema/tipovi_podataka}TID"/>
 *         &lt;element name="datum_valute" type="{http://www.w3.org/2001/XMLSchema}date"/>
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
@XmlType(name = "mt910", propOrder = {
    "idPoruke",
    "bankaPoverilac",
    "idPorukeNaloga",
    "datumValute",
    "iznos",
    "sifraValute"
})
@XmlRootElement(name = "mt910")
public class Mt910 {

    @XmlElement(name = "id_poruke", required = true)
    protected String idPoruke;
    @XmlElement(name = "banka_poverilac", required = true)
    protected TBanka bankaPoverilac;
    @XmlElement(name = "id_poruke_naloga", required = true)
    protected String idPorukeNaloga;
    @XmlElement(name = "datum_valute", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumValute;
    @XmlElement(required = true)
    protected BigDecimal iznos;
    @XmlElement(name = "sifra_valute", required = true)
    protected String sifraValute;

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
     * Gets the value of the bankaPoverilac property.
     * 
     * @return
     *     possible object is
     *     {@link TBanka }
     *     
     */
    public TBanka getBankaPoverilac() {
        return bankaPoverilac;
    }

    /**
     * Sets the value of the bankaPoverilac property.
     * 
     * @param value
     *     allowed object is
     *     {@link TBanka }
     *     
     */
    public void setBankaPoverilac(TBanka value) {
        this.bankaPoverilac = value;
    }

    /**
     * Gets the value of the idPorukeNaloga property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdPorukeNaloga() {
        return idPorukeNaloga;
    }

    /**
     * Sets the value of the idPorukeNaloga property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdPorukeNaloga(String value) {
        this.idPorukeNaloga = value;
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
