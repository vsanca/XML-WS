package test.client;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import com.xml2017.centralna_banka.CentralnaBanka;
import com.xml2017.schema.mt102.Mt102;
import com.xml2017.schema.mt103.Mt103;
import com.xml2017.schema.mt103.Mt103.Banke;
import com.xml2017.schema.mt900.Mt900;
import com.xml2017.schema.tipovi_podataka.TBanka;
import com.xml2017.schema.tipovi_podataka.TPlacanje;
import com.xml2017.schema.tipovi_podataka.TPojedinacnoPlacanje;
import com.xml2017.schema.tipovi_podataka.TUplata;

public class HelloClient {

    public void testIt() {
    	
		try {
			URL wsdlLocation = new URL("http://localhost:8080/centralna_banka/services/CentralnaBanka?wsdl");
			QName serviceName = new QName("http://www.xml2017.com/centralna_banka", "CentralnaBankaService");
			QName portName = new QName("http://www.xml2017.com/centralna_banka", "CentralnaBanka");

			Service service = Service.create(wsdlLocation, serviceName);
			
			CentralnaBanka RTGS = service.getPort(portName, CentralnaBanka.class);
			
			Mt103 request = new Mt103();
			request.setIdPoruke("1");
			
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
			request.setBanke(banke);

			
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
			
			u.setDatumNaloga(new XMLGregorianCalendarImpl());
			u.setDatumValute(new XMLGregorianCalendarImpl());
			
			u.setSifraValute("RSD");
			u.setSvrhaPlacanja("Svrha");
			
			request.setUplata(u);


			Mt102 mt102 = new Mt102();
			mt102.setIDPoruke("2");
			mt102.setBankaDuznika(duznik);
			mt102.setBankaPoverioca(poverilac);
			mt102.setIDPoruke("333");
			mt102.setUkupanIznos((new BigDecimal(1234)));
			mt102.setSifraValute("RSD");
			mt102.setDatum(new XMLGregorianCalendarImpl());
			mt102.setDatumValute(new XMLGregorianCalendarImpl());
			
			TPojedinacnoPlacanje placanje = new TPojedinacnoPlacanje();
			placanje.setDatumNaloga(new XMLGregorianCalendarImpl());
			placanje.setDuznikNalogodavac("Duznik");
			placanje.setIDNalogaZaPlacanje("1");
			placanje.setIznos(new BigDecimal(1234));
			placanje.setModelOdobrenja(new Long(97));
			placanje.setModelZaduzenja(new Long(97));
			placanje.setPozivNaBrojOdobrenja("11111111111111111111");
			placanje.setPozivNaBrojZaduzenja("22222222222222222222");
			placanje.setPrimalacPoverilac("Poverilac");
			placanje.setRacunDuznika(duznik.getBankAccountNumber());
			placanje.setRacunPoverioca(poverilac.getBankAccountNumber());
			placanje.setSifraValute("RSD");
			placanje.setSvrhaPlacanja("Svrha");
			
			ArrayList<TPojedinacnoPlacanje> placanja = new ArrayList<TPojedinacnoPlacanje>();
			placanja.add(placanje);
			
			//mt102.getPojedinacnaPlacanja().getPojedinacnoPlacanje().add(placanje);
			
			
			//Mt900 mt900 = RTGS.mt102ReceiveCB(mt102);
			
			System.out.println(mt102);		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	public static void main(String[] args) {
		
		HelloClient client = new HelloClient();
		client.testIt();
    }

}