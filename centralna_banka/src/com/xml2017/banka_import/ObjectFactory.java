
package com.xml2017.banka_import;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.xml2017.banka package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _OdgovorNaPlacanje_QNAME = new QName("http://www.xml2017.com/banka", "odgovor-na-placanje");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.xml2017.banka
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ClearSettleBanka }
     * 
     */
    public ClearSettleBanka createClearSettleBanka() {
        return new ClearSettleBanka();
    }

    /**
     * Create an instance of {@link RtgsBanka }
     * 
     */
    public RtgsBanka createRtgsBanka() {
        return new RtgsBanka();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xml2017.com/banka", name = "odgovor-na-placanje")
    public JAXBElement<Boolean> createOdgovorNaPlacanje(Boolean value) {
        return new JAXBElement<Boolean>(_OdgovorNaPlacanje_QNAME, Boolean.class, null, value);
    }

}
