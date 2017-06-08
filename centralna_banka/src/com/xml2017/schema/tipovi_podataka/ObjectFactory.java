
package com.xml2017.schema.tipovi_podataka;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.xml2017.schema.tipovi_podataka package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.xml2017.schema.tipovi_podataka
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TUplata }
     * 
     */
    public TUplata createTUplata() {
        return new TUplata();
    }

    /**
     * Create an instance of {@link TBanka }
     * 
     */
    public TBanka createTBanka() {
        return new TBanka();
    }

    /**
     * Create an instance of {@link TFakturaStavka }
     * 
     */
    public TFakturaStavka createTFakturaStavka() {
        return new TFakturaStavka();
    }

    /**
     * Create an instance of {@link TPlacanje }
     * 
     */
    public TPlacanje createTPlacanje() {
        return new TPlacanje();
    }

    /**
     * Create an instance of {@link TPojedinacnoPlacanje }
     * 
     */
    public TPojedinacnoPlacanje createTPojedinacnoPlacanje() {
        return new TPojedinacnoPlacanje();
    }

    /**
     * Create an instance of {@link TOsobaPrenos }
     * 
     */
    public TOsobaPrenos createTOsobaPrenos() {
        return new TOsobaPrenos();
    }

}
