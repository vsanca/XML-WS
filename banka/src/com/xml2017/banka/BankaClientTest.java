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
import com.xml2017.bankaTipovi.BankaObracunskiRacun;
import com.xml2017.bankaTipovi.BankaRacunKlijenta;
import com.xml2017.schema.izvod.Izvod;
import com.xml2017.schema.izvod.Izvod.Presek.StavkaPreseka;
import com.xml2017.schema.mt103.Mt103;
import com.xml2017.schema.mt103.Mt103.Banke;
import com.xml2017.schema.mt910.Mt910;
import com.xml2017.schema.prenos.NalogZaPrenos;
import com.xml2017.schema.prenos.NalogZaPrenos.PodaciOPrenosu;
import com.xml2017.schema.tipovi_podataka.TBanka;
import com.xml2017.schema.tipovi_podataka.TOsobaPrenos;
import com.xml2017.schema.tipovi_podataka.TPlacanje;
import com.xml2017.schema.tipovi_podataka.TUplata;
import com.xml2017.schema.zahtev.ZahtevZaIzvod;
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
import com.marklogic.client.query.StringQueryDefinition;
import com.marklogic.client.query.StructuredQueryBuilder;
import com.marklogic.client.query.StructuredQueryDefinition;


public class BankaClientTest {
	
	public void testIt1() {
    	
		try {
			URL wsdlLocation = new URL("http://localhost:8080/banka/services/Banka?wsdl");
			QName serviceName = new QName("http://www.xml2017.com/banka", "BankaService");
			QName portName = new QName("http://www.xml2017.com/banka", "Banka");
			
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
			
			u.setDatumNaloga(xmlGregor);
			u.setDatumValute(xmlGregor);
			
			u.setSifraValute("RSD");
			u.setSvrhaPlacanja("Svrha");
			
			mt103.setUplata(u);
			
			Mt910 mt910 = new Mt910();
			
			mt910.setIdPoruke("1");
			mt910.setBankaPoverilac(poverilac);
			mt910.setDatumValute(xmlGregor);
			mt910.setIznos(BigDecimal.valueOf(250000.00));
			mt910.setSifraValute("RSD");
			mt910.setIdPorukeNaloga("id poruke naloga");
			
			System.out.println("Poziv banke");
			
			banka.rtgsBanka(mt103, mt910);
			
			System.out.println("Izvrseno");
			
			
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


	public void testIt2() {
		
		try {
			URL wsdlLocation = new URL("http://localhost:8080/banka/services/Banka?wsdl");
			QName serviceName = new QName("http://www.xml2017.com/banka", "BankaService");
			QName portName = new QName("http://www.xml2017.com/banka", "Banka");
	
			Service service = Service.create(wsdlLocation, serviceName);
			
			Banka banka = service.getPort(portName, Banka.class); 
			
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
			
			ZahtevZaIzvod zahtevZaIzvod = new ZahtevZaIzvod();
			zahtevZaIzvod.setDatum(xmlGregor);
			zahtevZaIzvod.setRedniBrojPreseka(1);
			zahtevZaIzvod.setBrojRacuna("111-1111111111111-11");
			
			System.out.println("Poziv banke");
			
			Izvod izvod = banka.preuzimanjeIzvoda(zahtevZaIzvod);
			
			System.out.println("izvod prethodno: " + izvod.getZaglavlje().getPrethodnoStanje());
			System.out.println("izvod trenutno:" + izvod.getZaglavlje().getNovoStanje());
			System.out.println("izvod broj preseka: " + izvod.getZaglavlje().getBrojPreseka());
			System.out.println("Broj stavki: " + izvod.getPresek().getStavkaPreseka().size() );
			for (StavkaPreseka stavka : izvod.getPresek().getStavkaPreseka()) {
				
				System.out.println("Duznik: " + stavka.getPodaciOPrenosu().getDuznikPrenos().getBrojRacuna());
				System.out.println("Poverilac: " + stavka.getPodaciOPrenosu().getPoverilacPrenos().getBrojRacuna());
				System.out.println("Iznos: " + stavka.getPodaciOPrenosu().getIznos().doubleValue());
				
			}
			
			System.out.println("Izvrseno");
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	
	}
	
	
	public void ultimateTest1() {
		
		URL wsdlLocation;
		try {
			wsdlLocation = new URL("http://localhost:8080/banka/services/Banka?wsdl");
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
			return;
		}
		QName serviceName = new QName("http://www.xml2017.com/banka", "BankaService");
		QName portName = new QName("http://www.xml2017.com/banka", "Banka");
		
		Service service = Service.create(wsdlLocation, serviceName);
		
		Banka banka = service.getPort(portName, Banka.class); 
		
		
		
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
		poverilacPrenos.setBrojRacuna("111-1111111111111-33");
		poverilacPrenos.setModel(97);
		poverilacPrenos.setPozivNaBroj("11111111111111111111");
		
		prenos.setDuznikPrenos(duznikPrenos);
		prenos.setPoverilacPrenos(poverilacPrenos);
		prenos.setIznos(BigDecimal.valueOf(3000));
		prenos.setOznakaValute("RSD");
		prenos.setDatumValute(xmlGregor);
		
		nalog.setIdPoruke("nalog75");
		nalog.setDatumNaloga(xmlGregor);
		nalog.setSvrhaPlacanja("Kao da je bitno");
		nalog.setDuznikNalogodavac("Firma A");
		nalog.setPrimalacPoverilac("Firma B");
		nalog.setPodaciOPrenosu(prenos);
		nalog.setHitno(false);
		
		
		boolean rezultat = banka.slanjeNalogaZaPlacanje(nalog);
		
		System.out.println("Izvrseno slanje naloga!");
		System.out.println("rezultat: " + rezultat);
		
	}
	
	public void ultimateMegaGigaUltraTest() {
		
		BankaDataInit dataInit = new BankaDataInit();
		try {
			dataInit.initData();
		} catch (JAXBException e) {
			e.printStackTrace();
			return;
		}
		
		URL wsdlLocation;
		try {
			wsdlLocation = new URL("http://localhost:8080/banka/services/Banka?wsdl");
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
			return;
		}
		QName serviceName = new QName("http://www.xml2017.com/banka", "BankaService");
		QName portName = new QName("http://www.xml2017.com/banka", "Banka");
		
		Service service = Service.create(wsdlLocation, serviceName);
		
		Banka banka = service.getPort(portName, Banka.class); 
		
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
		
		
		NalogZaPrenos nalog = new NalogZaPrenos();
		
		PodaciOPrenosu prenos = new PodaciOPrenosu();
		
		TOsobaPrenos duznikPrenos = new TOsobaPrenos();
		duznikPrenos.setBrojRacuna("111-1111111111111-11");
		duznikPrenos.setModel(97);
		duznikPrenos.setPozivNaBroj("11111111111111111111");
		
		TOsobaPrenos poverilacPrenos = new TOsobaPrenos();
		poverilacPrenos.setBrojRacuna("111-1111111111111-22");
		poverilacPrenos.setModel(97);
		poverilacPrenos.setPozivNaBroj("11111111111111111111");
		
		prenos.setDuznikPrenos(duznikPrenos);
		prenos.setPoverilacPrenos(poverilacPrenos);
		prenos.setIznos(BigDecimal.valueOf(25000));
		prenos.setOznakaValute("RSD");
		prenos.setDatumValute(xmlGregor);
		
		nalog.setIdPoruke("nalog15");
		nalog.setDatumNaloga(xmlGregor);
		nalog.setSvrhaPlacanja("Kao da je bitno");
		nalog.setDuznikNalogodavac("Firma A");
		nalog.setPrimalacPoverilac("Firma B");
		nalog.setPodaciOPrenosu(prenos);
		nalog.setHitno(false);
		
		// nalog u okviru iste banke
		boolean rezultat = banka.slanjeNalogaZaPlacanje(nalog);
		if (!rezultat) {
			System.out.println("Nije uspelo izvrsavanje slanja naloga u okviru iste banke!");
		}
		else {
			System.out.println("Proslo izvrsenje naloga u okviru iste banke");
		}
		
		// nalog koji ide na rtgs
		nalog.setIdPoruke("nalog25");
		nalog.getPodaciOPrenosu().getPoverilacPrenos().setBrojRacuna("111-1111111111111-33");
		nalog.getPodaciOPrenosu().setIznos(BigDecimal.valueOf(300000));
		
		rezultat = banka.slanjeNalogaZaPlacanje(nalog);
		if (!rezultat) {
			System.out.println("Nije uspelo izvrsavanje rtgs slanja naloga!");
		}
		else {
			System.out.println("Proslo izvrsenje rtgs naloga");
		}
		
		// nalog koji ide na C&S, ide u neobradjene
		nalog.setIdPoruke("nalog35");
		nalog.getPodaciOPrenosu().getPoverilacPrenos().setBrojRacuna("111-1111111111111-33");
		nalog.getPodaciOPrenosu().setIznos(BigDecimal.valueOf(1000));
		
		rezultat = banka.slanjeNalogaZaPlacanje(nalog);
		if (!rezultat) {
			System.out.println("Nije uspelo izvrsavanje C&S slanja naloga 35!");
		}
		else {
			System.out.println("Proslo izvrsenje C&S naloga 35");
		}
		
		// nalog koji ide na C&S, ide u neobradjene
		nalog.setIdPoruke("nalog45");
		nalog.getPodaciOPrenosu().getPoverilacPrenos().setBrojRacuna("111-1111111111111-33");
		nalog.getPodaciOPrenosu().setIznos(BigDecimal.valueOf(1500));
				
		rezultat = banka.slanjeNalogaZaPlacanje(nalog);
		if (!rezultat) {
			System.out.println("Nije uspelo izvrsavanje C&S slanja naloga 45!");
		}
		else {
			System.out.println("Proslo izvrsenje C&S naloga 45");
		}
		
		// nalog koji ide na C&S, ide u neobradjene
		nalog.setIdPoruke("nalog55");
		nalog.getPodaciOPrenosu().getPoverilacPrenos().setBrojRacuna("111-1111111111111-33");
		nalog.getPodaciOPrenosu().setIznos(BigDecimal.valueOf(2000));
						
		rezultat = banka.slanjeNalogaZaPlacanje(nalog);
		if (!rezultat) {
			System.out.println("Nije uspelo izvrsavanje C&S slanja naloga 55!");
		}
		else {
			System.out.println("Proslo izvrsenje C&S naloga 55");
		}
		
		// nalog koji ide na C&S, ide u neobradjene
		nalog.setIdPoruke("nalog65");
		nalog.getPodaciOPrenosu().getPoverilacPrenos().setBrojRacuna("111-1111111111111-33");
		nalog.getPodaciOPrenosu().setIznos(BigDecimal.valueOf(2500));
						
		rezultat = banka.slanjeNalogaZaPlacanje(nalog);
		if (!rezultat) {
			System.out.println("Nije uspelo izvrsavanje C&S slanja naloga 65!");
		}
		else {
			System.out.println("Proslo izvrsenje C&S naloga 65");
		}
		
		// nalog koji ide na C&S i pokrece njegovo izvrsavanje
		nalog.setIdPoruke("nalog75");
		nalog.getPodaciOPrenosu().getPoverilacPrenos().setBrojRacuna("111-1111111111111-33");
		nalog.getPodaciOPrenosu().setIznos(BigDecimal.valueOf(3000));
						
		rezultat = banka.slanjeNalogaZaPlacanje(nalog);
		if (!rezultat) {
			System.out.println("Nije uspelo izvrsavanje C&S slanja naloga 75!");
		}
		else {
			System.out.println("Proslo izvrsenje C&S naloga 75");
		}
		
		// slanje fakture za 11
		ZahtevZaIzvod zahtevZaIzvod = new ZahtevZaIzvod();
		zahtevZaIzvod.setDatum(xmlGregor);
		zahtevZaIzvod.setRedniBrojPreseka(1);
		zahtevZaIzvod.setBrojRacuna("111-1111111111111-11");
		
		Izvod izvod = banka.preuzimanjeIzvoda(zahtevZaIzvod);
		
		System.out.println("Izvod za 111-1111111111111-11");
		System.out.println("broj na teret: " + izvod.getZaglavlje().getBrojPromenaNaTeret());
		System.out.println("broj u korist: " + izvod.getZaglavlje().getBrojPromenaUKorist());
		System.out.println("prethodno stanje: " + izvod.getZaglavlje().getPrethodnoStanje());
		System.out.println("trenutno stanje: " + izvod.getZaglavlje().getNovoStanje());
		System.out.println("broj preseka: " + izvod.getZaglavlje().getBrojPreseka());
		System.out.println("Broj stavki: " + izvod.getPresek().getStavkaPreseka().size() );
		for (StavkaPreseka stavka : izvod.getPresek().getStavkaPreseka()) {
			
			System.out.println("Duznik: " + stavka.getPodaciOPrenosu().getDuznikPrenos().getBrojRacuna());
			System.out.println("Poverilac: " + stavka.getPodaciOPrenosu().getPoverilacPrenos().getBrojRacuna());
			System.out.println("Iznos: " + stavka.getPodaciOPrenosu().getIznos().doubleValue());
			
		}
		
		// slanje fakture za 33
		zahtevZaIzvod.setBrojRacuna("111-1111111111111-33");
				
		izvod = banka.preuzimanjeIzvoda(zahtevZaIzvod);
				
		System.out.println("Izvod za 111-1111111111111-11");
		System.out.println("broj na teret: " + izvod.getZaglavlje().getBrojPromenaNaTeret());
		System.out.println("broj u korist: " + izvod.getZaglavlje().getBrojPromenaUKorist());
		System.out.println("prethodno stanje: " + izvod.getZaglavlje().getPrethodnoStanje());
		System.out.println("trenutno stanje: " + izvod.getZaglavlje().getNovoStanje());
		System.out.println("broj preseka: " + izvod.getZaglavlje().getBrojPreseka());
		System.out.println("Broj stavki: " + izvod.getPresek().getStavkaPreseka().size() );
		for (StavkaPreseka stavka : izvod.getPresek().getStavkaPreseka()) {
					
			System.out.println("Duznik: " + stavka.getPodaciOPrenosu().getDuznikPrenos().getBrojRacuna());
			System.out.println("Poverilac: " + stavka.getPodaciOPrenosu().getPoverilacPrenos().getBrojRacuna());
			System.out.println("Iznos: " + stavka.getPodaciOPrenosu().getIznos().doubleValue());
					
		}
		
		// provera ispravnosti podataka
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(BankaRacunKlijenta.class);
		} catch (JAXBException e) {
			e.printStackTrace();
			return;
		}
		JAXBHandle<BankaRacunKlijenta> readHandleRacun = new JAXBHandle<BankaRacunKlijenta>(context);
		
		xmlDocManager.read("/racun111-1111111111111-11", readHandleRacun);
		System.out.println("Vrednost na 11: " + readHandleRacun.get().getStanje().doubleValue());
		if (readHandleRacun.get().getStanje().doubleValue() == 15000) {
			System.out.println("Ocekivana vrednost u 11");
		}
		else {
			System.out.println("Pogresna vrednost u 11!");
		}
		
		xmlDocManager.read("/racun111-1111111111111-22", readHandleRacun);
		System.out.println("Vrednost na 22: " + readHandleRacun.get().getStanje().doubleValue());
		if (readHandleRacun.get().getStanje().doubleValue() == 75000) {
			System.out.println("Ocekivana vrednost u 22");
		}
		else {
			System.out.println("Pogresna vrednost u 22!");
		}
		
		xmlDocManager.read("/racun111-1111111111111-33", readHandleRacun);
		System.out.println("Vrednost na 33: " + readHandleRacun.get().getStanje().doubleValue());
		if (readHandleRacun.get().getStanje().doubleValue() == 410000) {
			System.out.println("Ocekivana vrednost u 33");
		}
		else {
			System.out.println("Pogresna vrednost u 33!");
		}
		
		// provera da nije doslo do promena u obracunskim racunima
		try {
			context = JAXBContext.newInstance(BankaObracunskiRacun.class);
		} catch (JAXBException e) {
			e.printStackTrace();
			return;
		}
		JAXBHandle<BankaObracunskiRacun> readHandleObracunski = new JAXBHandle<BankaObracunskiRacun>(context);
		
		xmlDocManager.read("/obracunskiRacun8080", readHandleObracunski);
		System.out.println("Vrednost na 8080: " + readHandleObracunski.get().getStanje().doubleValue());
		if (readHandleObracunski.get().getStanje().doubleValue() == 500000) {
			System.out.println("Nepromenjena vrednost na obracunskom 8080");
		}
		else {
			System.out.println("Pogresna vrednost na obracunskom 8080!");
		}
		
		xmlDocManager.read("/obracunskiRacun8081", readHandleObracunski);
		System.out.println("Vrednost na 8081: " + readHandleObracunski.get().getStanje().doubleValue());
		if (readHandleObracunski.get().getStanje().doubleValue() == 300000) {
			System.out.println("Nepromenjena vrednost na obracunskom 8081");
		}
		else {
			System.out.println("Pogresna vrednost na obracunskom 8081!");
		}
		
		// provera sacuvanih naloga
		QueryManager queryManager = client.newQueryManager();
		StringQueryDefinition queryDef = queryManager.newStringDefinition();
		queryDef.setCriteria("");
		queryDef.setCollections("/nalozi-obradjeni");
		
		SearchHandle search = queryManager.search(queryDef, new SearchHandle());
		
		if (search.getMatchResults().length == 7) {
			System.out.println("Ispravan broj obradjenih naloga");
		}
		else {
			System.out.println("Pogresan broj obradjenih naloga!");
		}
		
		queryDef = queryManager.newStringDefinition();
		queryDef.setCriteria("");
		queryDef.setCollections("/nalozi-neobradjeni");
		
		search = queryManager.search(queryDef, new SearchHandle());
		
		if (search.getMatchResults().length == 0) {
			System.out.println("Ispravan broj neobradjenih naloga");
		}
		else {
			System.out.println("Pogresan broj neobradjenih naloga!");
		}
				
	}
	

	public static void main(String[] args) {
		
		BankaClientTest bankaTest = new BankaClientTest();
		//bankaTest.testIt1();
		//bankaTest.testMarkLogicConnection();
		//bankaTest.nalogWriteAndQueryTest();
		//bankaTest.testIt2();
		
		//bankaTest.ultimateTest1();
		
		// obavezno ocistiti bazu od svih dokumenata pre pokretanja!
		bankaTest.ultimateMegaGigaUltraTest();

	}

}
