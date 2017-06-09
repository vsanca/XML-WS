
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package com.xml2017.banka;

import java.util.logging.Logger;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;

/**
 * This class was generated by Apache CXF 2.6.5
 * 2017-06-09T02:11:16.227+02:00
 * Generated source version: 2.6.5
 * 
 */

@javax.jws.WebService(
                      serviceName = "BankaService",
                      portName = "Banka",
                      targetNamespace = "http://www.xml2017.com/banka",
//                      wsdlLocation = "file:/D:/Git/XML-WS/banka/WEB-INF/wsdl/Bank.wsdl",
                      endpointInterface = "com.xml2017.banka.Banka")
                      
public class BankaImpl implements Banka {

    private static final Logger LOG = Logger.getLogger(BankaImpl.class.getName());

    /* (non-Javadoc)
     * @see com.xml2017.banka.Banka#preuzimanjeIzvoda(com.xml2017.schema.zahtev.ZahtevZaIzvod  zahtev )*
     */
    public com.xml2017.schema.izvod.Izvod preuzimanjeIzvoda(com.xml2017.schema.zahtev.ZahtevZaIzvod zahtev) { 
        LOG.info("Executing operation preuzimanjeIzvoda");
        System.out.println(zahtev);
        try {
            com.xml2017.schema.izvod.Izvod _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see com.xml2017.banka.Banka#slanjeNalogaZaPlacanje(com.xml2017.schema.prenos.NalogZaPrenos  nalog )*
     */
    public boolean slanjeNalogaZaPlacanje(com.xml2017.schema.prenos.NalogZaPrenos nalog) { 
        LOG.info("Executing operation slanjeNalogaZaPlacanje");
        System.out.println(nalog);
        try {
            boolean _return = false;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see com.xml2017.banka.Banka#rtgsBanka(com.xml2017.schema.mt103.Mt103  mt103 ,)com.xml2017.schema.mt910.Mt910  mt910 )*
     */
    public void rtgsBanka(com.xml2017.schema.mt103.Mt103 mt103,com.xml2017.schema.mt910.Mt910 mt910) { 
        LOG.info("Executing operation rtgsBanka");
        System.out.println(mt103);
        System.out.println(mt910);
        try {
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see com.xml2017.banka.Banka#clearSettleBanka(com.xml2017.schema.mt102.Mt102  mt102 ,)com.xml2017.schema.mt910.Mt910  mt910 )*
     */
    public void clearSettleBanka(com.xml2017.schema.mt102.Mt102 mt102,com.xml2017.schema.mt910.Mt910 mt910) { 
        LOG.info("Executing operation clearSettleBanka");
        System.out.println(mt102);
        System.out.println(mt910);
        try {
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}
