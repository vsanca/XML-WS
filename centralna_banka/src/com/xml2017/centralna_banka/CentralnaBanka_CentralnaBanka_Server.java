
package com.xml2017.centralna_banka;

import javax.xml.ws.Endpoint;

/**
 * This class was generated by Apache CXF 2.6.5
 * 2017-06-09T11:28:29.042+02:00
 * Generated source version: 2.6.5
 * 
 */
 
public class CentralnaBanka_CentralnaBanka_Server{

    protected CentralnaBanka_CentralnaBanka_Server() throws java.lang.Exception {
        System.out.println("Starting Server");
        Object implementor = new CentralnaBankaImpl();
        String address = "http://localhost:8080/centralna_banka";
        Endpoint.publish(address, implementor);
    }
    
    public static void main(String args[]) throws java.lang.Exception { 
        new CentralnaBanka_CentralnaBanka_Server();
        System.out.println("Server ready..."); 
        
        Thread.sleep(5 * 60 * 1000); 
        System.out.println("Server exiting");
        System.exit(0);
    }
}
