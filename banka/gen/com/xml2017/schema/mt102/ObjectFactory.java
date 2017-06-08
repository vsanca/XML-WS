
package com.xml2017.schema.mt102;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.xml2017.schema.mt102 package. 
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

    private final static QName _Mt102_QNAME = new QName("http://www.xml2017.com/schema/mt102", "mt102");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.xml2017.schema.mt102
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Tmt102 }
     * 
     */
    public Tmt102 createTmt102() {
        return new Tmt102();
    }

    /**
     * Create an instance of {@link Tmt102 .PojedinacnaPlacanja }
     * 
     */
    public Tmt102 .PojedinacnaPlacanja createTmt102PojedinacnaPlacanja() {
        return new Tmt102 .PojedinacnaPlacanja();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Tmt102 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xml2017.com/schema/mt102", name = "mt102")
    public JAXBElement<Tmt102> createMt102(Tmt102 value) {
        return new JAXBElement<Tmt102>(_Mt102_QNAME, Tmt102 .class, null, value);
    }

}
