
package com.xml2017.banka;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.xml2017.schema.mt103.Tmt103;
import com.xml2017.schema.mt910.Tmt910;


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
 *         &lt;element name="mt103" type="{http://www.xml2017.com/schema/mt103}Tmt103"/>
 *         &lt;element name="mt910" type="{http://www.xml2017.com/schema/mt910}Tmt910"/>
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
    "mt103",
    "mt910"
})
@XmlRootElement(name = "rtgsBanka")
public class RtgsBanka {

    @XmlElement(required = true)
    protected Tmt103 mt103;
    @XmlElement(required = true)
    protected Tmt910 mt910;

    /**
     * Gets the value of the mt103 property.
     * 
     * @return
     *     possible object is
     *     {@link Tmt103 }
     *     
     */
    public Tmt103 getMt103() {
        return mt103;
    }

    /**
     * Sets the value of the mt103 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Tmt103 }
     *     
     */
    public void setMt103(Tmt103 value) {
        this.mt103 = value;
    }

    /**
     * Gets the value of the mt910 property.
     * 
     * @return
     *     possible object is
     *     {@link Tmt910 }
     *     
     */
    public Tmt910 getMt910() {
        return mt910;
    }

    /**
     * Sets the value of the mt910 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Tmt910 }
     *     
     */
    public void setMt910(Tmt910 value) {
        this.mt910 = value;
    }

}
