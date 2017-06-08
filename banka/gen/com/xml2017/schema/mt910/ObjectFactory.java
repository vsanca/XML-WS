
package com.xml2017.schema.mt910;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.xml2017.schema.mt910 package. 
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

    private final static QName _Mt910_QNAME = new QName("http://www.xml2017.com/schema/mt910", "mt910");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.xml2017.schema.mt910
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Tmt910 }
     * 
     */
    public Tmt910 createTmt910() {
        return new Tmt910();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Tmt910 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xml2017.com/schema/mt910", name = "mt910")
    public JAXBElement<Tmt910> createMt910(Tmt910 value) {
        return new JAXBElement<Tmt910>(_Mt910_QNAME, Tmt910 .class, null, value);
    }

}
