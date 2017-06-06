
package com.xml2017.centralna_banka;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.6.5
 * 2017-06-07T00:22:48.019+02:00
 * Generated source version: 2.6.5
 * 
 */
public final class CentralnaBankaPortType_CentralnaBankaPort_Client {

    private static final QName SERVICE_NAME = new QName("http://www.xml2017.com/centralna_banka", "CentralnaBankaService");

    private CentralnaBankaPortType_CentralnaBankaPort_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = CentralnaBankaService.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        CentralnaBankaService ss = new CentralnaBankaService(wsdlURL, SERVICE_NAME);
        CentralnaBankaPortType port = ss.getCentralnaBankaPort();  
        
        {
        System.out.println("Invoking mt103ReceiveCB...");
        com.xml2017.schema.mt103.Mt103 _mt103ReceiveCB_mt103 = null;
        try {
            com.xml2017.schema.mt900.Mt900 _mt103ReceiveCB__return = port.mt103ReceiveCB(_mt103ReceiveCB_mt103);
            System.out.println("mt103ReceiveCB.result=" + _mt103ReceiveCB__return);

        } catch (RTGSFault e) { 
            System.out.println("Expected exception: RTGSFault has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking mt102ReceiveCB...");
        com.xml2017.schema.mt102.Mt102 _mt102ReceiveCB_mt102 = null;
        try {
            com.xml2017.schema.mt900.Mt900 _mt102ReceiveCB__return = port.mt102ReceiveCB(_mt102ReceiveCB_mt102);
            System.out.println("mt102ReceiveCB.result=" + _mt102ReceiveCB__return);

        } catch (ClearingFault e) { 
            System.out.println("Expected exception: ClearingFault has occurred.");
            System.out.println(e.toString());
        }
            }

        System.exit(0);
    }

}
