package com.xml2017.banka;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import com.xml2017.schema.mt103.Mt103;
import com.xml2017.schema.mt103.Mt103.Banke;
import com.xml2017.schema.mt910.Mt910;
import com.xml2017.schema.tipovi_podataka.TBanka;
import com.xml2017.schema.tipovi_podataka.TPlacanje;
import com.xml2017.schema.tipovi_podataka.TUplata;

public class BankaClientTest {
	
public void testIt1() {
    	
		try {
			URL wsdlLocation = new URL("http://localhost:8080/banka/services/Banka?wsdl");
			QName serviceName = new QName("http://www.xml2017.com/banka", "BankaService");
			QName portName = new QName("http://www.xml2017.com/banka", "Banka");

			Service service = Service.create(wsdlLocation, serviceName);
			
			Banka banka = service.getPort(portName, Banka.class); 
			
			Mt103 mt103 = new Mt103();
			mt103.setIdPoruke("1");
			
			com.xml2017.schema.mt103.Mt103.Banke banke = new Banke();
			
			TBanka duznik = new TBanka();
			duznik.setBankAccountNumber("123-1231231231231-12");
			duznik.setSWIFT("AAAARS01");
			duznik.setId("111");
			
			TBanka poverilac = new TBanka();
			poverilac.setBankAccountNumber("321-3213213213213-32");
			poverilac.setSWIFT("BBBBRS01");
			poverilac.setId("222");
			
			banke.setBankaDuznika(duznik);
			banke.setBankaPoverioca(poverilac);
			mt103.setBanke(banke);
			
			TUplata u = new TUplata();
			u.setIznos(new BigDecimal(1234));
			
			TPlacanje poverilac1 = new TPlacanje();
			TPlacanje duznik1 = new TPlacanje();
			
			poverilac1.setModel(new Long(97));
			poverilac1.setPozivNaBroj("11111111111111111111");
			poverilac1.setRacun(poverilac.getBankAccountNumber());
			
			duznik1.setModel(new Long(97));
			duznik1.setPozivNaBroj("22222222222222222222");
			duznik1.setRacun(duznik.getBankAccountNumber());
			
			u.setPrimalacPoverilac("Poverilac");
			u.setPoverilacOdobrenje(poverilac1);
			
			u.setDuznikNalogodavac("Duznik");
			u.setDuznikZaduzenje(duznik1);
			
//			u.setDatumNaloga(new XMLGregorianCalendarImpl());
//			u.setDatumValute(new XMLGregorianCalendarImpl());
			
			u.setSifraValute("RSD");
			u.setSvrhaPlacanja("Svrha");
			
			mt103.setUplata(u);
			
			Mt910 mt910 = new Mt910();
			
			mt910.setIdPoruke("Isto 1");
			mt910.setBankaPoverilac(poverilac);
//			mt910.setDatumValute(new XMLGregorianCalendarImpl());
			mt910.setIznos(new BigDecimal(1234));
			mt910.setSifraValute("RSD");
			mt910.setIdPorukeNaloga("id poruke naloga");
			
			banka.rtgsBanka(mt103, mt910);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
    }

	public static void main(String[] args) {
		
		BankaClientTest bankaTest = new BankaClientTest();
		bankaTest.testIt1();

	}

}
