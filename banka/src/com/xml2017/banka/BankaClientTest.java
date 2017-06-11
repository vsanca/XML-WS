package com.xml2017.banka;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import com.xml2017.schema.mt103.Mt103;
import com.xml2017.schema.mt103.Mt103.Banke;
import com.xml2017.schema.mt910.Mt910;
import com.xml2017.schema.prenos.NalogZaPrenos;
import com.xml2017.schema.prenos.NalogZaPrenos.PodaciOPrenosu;
import com.xml2017.schema.tipovi_podataka.TBanka;
import com.xml2017.schema.tipovi_podataka.TOsobaPrenos;
import com.xml2017.schema.tipovi_podataka.TPlacanje;
import com.xml2017.schema.tipovi_podataka.TUplata;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.DatabaseClientFactory.Authentication;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.DocumentMetadataHandle;
import com.marklogic.client.io.InputStreamHandle;
import com.marklogic.client.io.JAXBHandle;
import com.marklogic.client.io.SearchHandle;
import com.marklogic.client.query.MatchDocumentSummary;
import com.marklogic.client.query.MatchLocation;
import com.marklogic.client.query.QueryManager;
import com.marklogic.client.query.StructuredQueryBuilder;
import com.marklogic.client.query.StructuredQueryDefinition;


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
			poverilac.setBankAccountNumber("000-0000000000000-00");
			poverilac.setSWIFT("AAAABBNS");
			poverilac.setId("222");
			
			banke.setBankaDuznika(duznik);
			banke.setBankaPoverioca(poverilac);
			mt103.setBanke(banke);
			
			TUplata u = new TUplata();
			u.setIznos(BigDecimal.valueOf(250000.00));
			
			TPlacanje poverilac1 = new TPlacanje();
			TPlacanje duznik1 = new TPlacanje();
			
			poverilac1.setModel(new Long(97));
			poverilac1.setPozivNaBroj("11111111111111111111");
			poverilac1.setRacun("111-1111111111111-11");
			
			duznik1.setModel(new Long(97));
			duznik1.setPozivNaBroj("22222222222222222222");
			duznik1.setRacun("111-1111111111111-12");
			
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
			mt910.setIznos(BigDecimal.valueOf(250000.00));
			mt910.setSifraValute("RSD");
			mt910.setIdPorukeNaloga("id poruke naloga");
			
			banka.rtgsBanka(mt103, mt910);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
    }

public void testMarkLogicConnection() {
	
	DatabaseClient client = DatabaseClientFactory.newClient("localhost",
			8000, "admin", "admin", Authentication.DIGEST);
	
	System.out.println("It worked!");	
	
}

public void nalogWriteAndQueryTest() {
	
	DatabaseClient client = DatabaseClientFactory.newClient("localhost",
			8000, "admin", "admin", Authentication.DIGEST);
	
	XMLDocumentManager xmlDocManager = client.newXMLDocumentManager();
	
	GregorianCalendar gregor = new GregorianCalendar();
	Date date = new Date();
	gregor.setTime(date);
	XMLGregorianCalendar xmlGregor = null;
	try {
		xmlGregor = DatatypeFactory.newInstance().newXMLGregorianCalendarDate(
				gregor.get(Calendar.YEAR), gregor.get(Calendar.MONTH)+1,
				gregor.get(Calendar.DAY_OF_MONTH), DatatypeConstants.FIELD_UNDEFINED);
	} catch (DatatypeConfigurationException e) {
		e.printStackTrace();
	}
	
	System.out.println(xmlGregor.toString());
	
	System.out.println(xmlGregor.toXMLFormat());
	
	NalogZaPrenos nalog = new NalogZaPrenos();
	
	PodaciOPrenosu prenos = new PodaciOPrenosu();
	
	TOsobaPrenos duznikPrenos = new TOsobaPrenos();
	duznikPrenos.setBrojRacuna("111-1111111111111-11");
	duznikPrenos.setModel(97);
	duznikPrenos.setPozivNaBroj("11111111111111111111");
	
	TOsobaPrenos poverilacPrenos = new TOsobaPrenos();
	poverilacPrenos.setBrojRacuna("333-1111111111111-33");
	poverilacPrenos.setModel(97);
	poverilacPrenos.setPozivNaBroj("11111111111111111111");
	
	prenos.setDuznikPrenos(duznikPrenos);
	prenos.setPoverilacPrenos(poverilacPrenos);
	prenos.setIznos(BigDecimal.valueOf(3395.00));
	prenos.setOznakaValute("RSD");
	prenos.setDatumValute(xmlGregor);
	
	nalog.setIdPoruke("nalog1");
	nalog.setDatumNaloga(xmlGregor);
	nalog.setSvrhaPlacanja("Kao da je bitno");
	nalog.setDuznikNalogodavac("Firma A");
	nalog.setPrimalacPoverilac("Firma B");
	nalog.setPodaciOPrenosu(prenos);
	nalog.setHitno(false);
	
	JAXBContext context = null;
	try {
		context = JAXBContext.newInstance(NalogZaPrenos.class);
	} catch (JAXBException e) {
		e.printStackTrace();
	}
	JAXBHandle<NalogZaPrenos> writeHandler = new JAXBHandle<NalogZaPrenos>(context);
	writeHandler.set(nalog);
	
	DocumentMetadataHandle metadata = new DocumentMetadataHandle();
	metadata.getCollections().add("/nalozi");
	
	xmlDocManager.write("/nalog25", metadata, writeHandler);
	
	System.out.println("Uspesno pisanje naloga");
	
	QueryManager queryManager = client.newQueryManager();
	
	StructuredQueryBuilder queryBuilder = new StructuredQueryBuilder();
	
	StructuredQueryDefinition queryDef = 
			queryBuilder.and(
					queryBuilder.collection("/nalozi"),
					queryBuilder.value(
							queryBuilder.element(new QName("http://www.xml2017.com/schema/tipovi_podataka", "broj-racuna")),
							"111-1111111111111-11"),
					queryBuilder.value(
							queryBuilder.element(new QName("http://www.xml2017.com/schema/tipovi_podataka", "broj-racuna")), 
							"333-1111111111111-33"));
	
	SearchHandle search = queryManager.search(queryDef, new SearchHandle());
	
	for (MatchDocumentSummary docSum : search.getMatchResults()) {
		System.out.println(docSum.getUri());
		for (MatchLocation docLoc : docSum.getMatchLocations()) {
			System.out.println(docLoc.getPath());
			System.out.println(docLoc.getAllSnippetText());
			if (docLoc.getPath().contains("duznik-prenos")) {
				System.out.println("Jeste duznik");
			}
		}
	}
	
	xmlDocManager.delete("/nalog25");
	
}

	public static void main(String[] args) {
		
		BankaClientTest bankaTest = new BankaClientTest();
		bankaTest.testIt1();
		//bankaTest.testMarkLogicConnection();
		//bankaTest.nalogWriteAndQueryTest();

	}

}
