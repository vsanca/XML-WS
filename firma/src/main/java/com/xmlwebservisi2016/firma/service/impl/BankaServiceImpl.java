package com.xmlwebservisi2016.firma.service.impl;

import com.xmlwebservisi2016.firma.banka_import.Banka;
import com.xmlwebservisi2016.firma.model.database_entities.Firma;
import com.xmlwebservisi2016.firma.model.jaxb.izvod.Izvod;
import com.xmlwebservisi2016.firma.model.jaxb.prenos.NalogZaPrenos;
import com.xmlwebservisi2016.firma.model.jaxb.zahtev.ZahtevZaIzvod;
import com.xmlwebservisi2016.firma.service.BankaService;
import com.xmlwebservisi2016.firma.service.FirmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;

/**
 * Created by Hp on 6/13/2017.
 */

@Service
@Transactional
public class BankaServiceImpl implements BankaService {

    @Override
    public Izvod preuzimanjeIzvoda(ZahtevZaIzvod zahtev, Firma firma) {

        try {
            URL wsdlLocation;
            try {
                wsdlLocation = new URL("http://localhost:" + firma.getBankPort() + "/banka/services/Banka?wsdl");
            } catch (MalformedURLException e1) {
                e1.printStackTrace();
                return null;
            }
            QName serviceName = new QName("http://www.xml2017.com/banka", "BankaService");
            QName portName = new QName("http://www.xml2017.com/banka", "Banka");

            javax.xml.ws.Service service = javax.xml.ws.Service.create(wsdlLocation, serviceName);

            Banka banka = service.getPort(portName, Banka.class);

            Izvod izvod = banka.preuzimanjeIzvoda(zahtev);

            return izvod;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean slanjeNalogaZaPlacanje(NalogZaPrenos nalog, Firma firma) {

        try {
            URL wsdlLocation;
            try {
                wsdlLocation = new URL("http://localhost:" + firma.getBankPort() + "/banka/services/Banka?wsdl");
            } catch (MalformedURLException e1) {
                e1.printStackTrace();
                return false;
            }
            QName serviceName = new QName("http://www.xml2017.com/banka", "BankaService");
            QName portName = new QName("http://www.xml2017.com/banka", "Banka");

            javax.xml.ws.Service service = javax.xml.ws.Service.create(wsdlLocation, serviceName);

            Banka banka = service.getPort(portName, Banka.class);

            boolean rezultat = banka.slanjeNalogaZaPlacanje(nalog);

            return rezultat;
        } catch (Exception e) {
            return false;
        }
    }
}
