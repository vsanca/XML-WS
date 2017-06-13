package com.xmlwebservisi2016.firma;

import com.xmlwebservisi2016.firma.model.database_entities.Firma;
import com.xmlwebservisi2016.firma.model.jaxb.izvod.Izvod;
import com.xmlwebservisi2016.firma.model.jaxb.prenos.NalogZaPrenos;
import com.xmlwebservisi2016.firma.model.jaxb.tipovi_podataka.TOsobaPrenos;
import com.xmlwebservisi2016.firma.model.jaxb.zahtev.ZahtevZaIzvod;
import com.xmlwebservisi2016.firma.repository.FirmaRepository;
import com.xmlwebservisi2016.firma.service.BankaService;
import com.xmlwebservisi2016.firma.service.FirmaService;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FirmaApplication.class)
@WebAppConfiguration
public class FirmaApplicationTests {

	@Autowired
	BankaService bankaService;

	@Autowired
	FirmaRepository firmaRepository;

	@Test
	public void testWorkingAtAll() {

		Assert.assertEquals(1 + 1, 2);

	}

	@Test
	public void testFirmaFound() {

		Firma firma = firmaRepository.findByBrojRacuna("111-1111111111111-11");

		Assert.assertNotNull(firma);

	}

	@Test
	public void testSendNalog15() {

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

		Firma firma = firmaRepository.findByBrojRacuna("111-1111111111111-11");

		NalogZaPrenos nalog = new NalogZaPrenos();

		NalogZaPrenos.PodaciOPrenosu prenos = new NalogZaPrenos.PodaciOPrenosu();

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
		boolean rezultat = bankaService.slanjeNalogaZaPlacanje(nalog, firma);

		Assert.assertEquals(true, rezultat);

	}

	@Test
	public void testSendNalog25() {

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

		Firma firma = firmaRepository.findByBrojRacuna("111-1111111111111-11");

		NalogZaPrenos nalog = new NalogZaPrenos();

		NalogZaPrenos.PodaciOPrenosu prenos = new NalogZaPrenos.PodaciOPrenosu();

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
		prenos.setIznos(BigDecimal.valueOf(300000));
		prenos.setOznakaValute("RSD");
		prenos.setDatumValute(xmlGregor);

		nalog.setIdPoruke("nalog25");
		nalog.setDatumNaloga(xmlGregor);
		nalog.setSvrhaPlacanja("Kao da je bitno");
		nalog.setDuznikNalogodavac("Firma A");
		nalog.setPrimalacPoverilac("Firma B");
		nalog.setPodaciOPrenosu(prenos);
		nalog.setHitno(false);

		// nalog u okviru iste banke
		boolean rezultat = bankaService.slanjeNalogaZaPlacanje(nalog, firma);

		Assert.assertEquals(true, rezultat);

	}

	@Test
	public void testSendNalog35() {

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

		Firma firma = firmaRepository.findByBrojRacuna("111-1111111111111-11");

		NalogZaPrenos nalog = new NalogZaPrenos();

		NalogZaPrenos.PodaciOPrenosu prenos = new NalogZaPrenos.PodaciOPrenosu();

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
		prenos.setIznos(BigDecimal.valueOf(1000));
		prenos.setOznakaValute("RSD");
		prenos.setDatumValute(xmlGregor);

		nalog.setIdPoruke("nalog35");
		nalog.setDatumNaloga(xmlGregor);
		nalog.setSvrhaPlacanja("Kao da je bitno");
		nalog.setDuznikNalogodavac("Firma A");
		nalog.setPrimalacPoverilac("Firma B");
		nalog.setPodaciOPrenosu(prenos);
		nalog.setHitno(false);

		// nalog u okviru iste banke
		boolean rezultat = bankaService.slanjeNalogaZaPlacanje(nalog, firma);

		Assert.assertEquals(true, rezultat);

	}

	@Test
	public void testSendNalog45() {

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

		Firma firma = firmaRepository.findByBrojRacuna("111-1111111111111-11");

		NalogZaPrenos nalog = new NalogZaPrenos();

		NalogZaPrenos.PodaciOPrenosu prenos = new NalogZaPrenos.PodaciOPrenosu();

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
		prenos.setIznos(BigDecimal.valueOf(1500));
		prenos.setOznakaValute("RSD");
		prenos.setDatumValute(xmlGregor);

		nalog.setIdPoruke("nalog45");
		nalog.setDatumNaloga(xmlGregor);
		nalog.setSvrhaPlacanja("Kao da je bitno");
		nalog.setDuznikNalogodavac("Firma A");
		nalog.setPrimalacPoverilac("Firma B");
		nalog.setPodaciOPrenosu(prenos);
		nalog.setHitno(false);

		// nalog u okviru iste banke
		boolean rezultat = bankaService.slanjeNalogaZaPlacanje(nalog, firma);

		Assert.assertEquals(true, rezultat);

	}

	@Test
	public void testSendNalog55() {

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

		Firma firma = firmaRepository.findByBrojRacuna("111-1111111111111-11");

		NalogZaPrenos nalog = new NalogZaPrenos();

		NalogZaPrenos.PodaciOPrenosu prenos = new NalogZaPrenos.PodaciOPrenosu();

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
		prenos.setIznos(BigDecimal.valueOf(2000));
		prenos.setOznakaValute("RSD");
		prenos.setDatumValute(xmlGregor);

		nalog.setIdPoruke("nalog55");
		nalog.setDatumNaloga(xmlGregor);
		nalog.setSvrhaPlacanja("Kao da je bitno");
		nalog.setDuznikNalogodavac("Firma A");
		nalog.setPrimalacPoverilac("Firma B");
		nalog.setPodaciOPrenosu(prenos);
		nalog.setHitno(false);

		// nalog u okviru iste banke
		boolean rezultat = bankaService.slanjeNalogaZaPlacanje(nalog, firma);

		Assert.assertEquals(true, rezultat);

	}

	@Test
	public void testSendNalog65() {

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

		Firma firma = firmaRepository.findByBrojRacuna("111-1111111111111-11");

		NalogZaPrenos nalog = new NalogZaPrenos();

		NalogZaPrenos.PodaciOPrenosu prenos = new NalogZaPrenos.PodaciOPrenosu();

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
		prenos.setIznos(BigDecimal.valueOf(2500));
		prenos.setOznakaValute("RSD");
		prenos.setDatumValute(xmlGregor);

		nalog.setIdPoruke("nalog65");
		nalog.setDatumNaloga(xmlGregor);
		nalog.setSvrhaPlacanja("Kao da je bitno");
		nalog.setDuznikNalogodavac("Firma A");
		nalog.setPrimalacPoverilac("Firma B");
		nalog.setPodaciOPrenosu(prenos);
		nalog.setHitno(false);

		// nalog u okviru iste banke
		boolean rezultat = bankaService.slanjeNalogaZaPlacanje(nalog, firma);

		Assert.assertEquals(true, rezultat);

	}

	@Test
	public void testSendNalog75() {

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

		Firma firma = firmaRepository.findByBrojRacuna("111-1111111111111-11");

		NalogZaPrenos nalog = new NalogZaPrenos();

		NalogZaPrenos.PodaciOPrenosu prenos = new NalogZaPrenos.PodaciOPrenosu();

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

		// nalog u okviru iste banke
		boolean rezultat = bankaService.slanjeNalogaZaPlacanje(nalog, firma);

		Assert.assertEquals(true, rezultat);

	}

	@Test
	public void testSendIzvestaj11() {

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

		Firma firma = firmaRepository.findByBrojRacuna("111-1111111111111-11");

		ZahtevZaIzvod zahtevZaIzvod = new ZahtevZaIzvod();
		zahtevZaIzvod.setDatum(xmlGregor);
		zahtevZaIzvod.setRedniBrojPreseka(1);
		zahtevZaIzvod.setBrojRacuna("111-1111111111111-11");

		Izvod izvod = bankaService.preuzimanjeIzvoda(zahtevZaIzvod, firma);

		Assert.assertEquals(izvod.getZaglavlje().getBrojRacuna(), "111-1111111111111-11");

	}

}
