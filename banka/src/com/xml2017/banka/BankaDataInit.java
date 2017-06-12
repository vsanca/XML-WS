package com.xml2017.banka;

import java.math.BigDecimal;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.DatabaseClientFactory.Authentication;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.DocumentMetadataHandle;
import com.marklogic.client.io.JAXBHandle;
import com.marklogic.client.io.SearchHandle;
import com.marklogic.client.query.MatchDocumentSummary;
import com.marklogic.client.query.QueryManager;
import com.marklogic.client.query.StringQueryDefinition;
import com.marklogic.client.query.StructuredQueryBuilder;
import com.marklogic.client.query.StructuredQueryDefinition;
import com.xml2017.bankaTipovi.BankaObracunskiRacun;
import com.xml2017.bankaTipovi.BankaRacunKlijenta;

public class BankaDataInit {
	
	public void initData() throws JAXBException {
		
		DatabaseClient client = DatabaseClientFactory.newClient("localhost",
				8000, "admin", "admin", Authentication.DIGEST);
		
		XMLDocumentManager xmlManager = client.newXMLDocumentManager();
		
		String swiftKod = "AAAABBNS";
		
		String docId = "/racun111-1111111111111-11.xml";
		String collId = "/racuni";
		
		DocumentMetadataHandle metadata = new DocumentMetadataHandle();
		metadata.getCollections().add(collId);
		
		BankaRacunKlijenta racun = new BankaRacunKlijenta();
		
		racun.setBankaPort(BankaService.port);
		racun.setBrojRacuna("111-1111111111111-11");
		racun.setStanje(BigDecimal.ZERO);
		racun.setSwiftKod(swiftKod);
		
		JAXBContext context = JAXBContext.newInstance(BankaRacunKlijenta.class);
		JAXBHandle<BankaRacunKlijenta> writeHandle = new JAXBHandle<BankaRacunKlijenta>(context);
		writeHandle.set(racun);
		
		System.out.println("Tacno pre write-a");
		xmlManager.write(docId, metadata, writeHandle);
		System.out.println("Pisanje racuna klijenta uspesno!");
		
		docId = "/obracunskiRacun" + BankaService.port;
		collId = "/obracunski";
		
		metadata = new DocumentMetadataHandle();
		metadata.getCollections().add(collId);
		
		BankaObracunskiRacun obracunski = new BankaObracunskiRacun();
		
		obracunski.setBankaPort(BankaService.port);
		obracunski.setBrojObracunskog("000-0000000000000-00");
		obracunski.setStanje(BigDecimal.valueOf(300000.00));
		obracunski.setSwiftKod(swiftKod);
	
		context = JAXBContext.newInstance(BankaObracunskiRacun.class);
		JAXBHandle<BankaObracunskiRacun> writeHandle1 = new JAXBHandle<BankaObracunskiRacun>(context);
		writeHandle1.set(obracunski);
		
		xmlManager.write(docId, metadata, writeHandle1);
		System.out.println("Pisanje obracunskog uspesno!");
		
		QueryManager queryManager = client.newQueryManager();
		
		StructuredQueryBuilder queryBuilder = new StructuredQueryBuilder();
    	StructuredQueryDefinition queryDef = 
    			queryBuilder.and(
    					queryBuilder.collection("/racuni"),
    					queryBuilder.value(
    							queryBuilder.element("broj-racuna"),
    							"111-1111111111111-11"));
    	
    	System.out.println("Tacno pre search-a!");
    	
    	SearchHandle search = queryManager.search(queryDef, new SearchHandle());
    	
    	System.out.println("Pronadjeno: " + search.getMatchResults().length);
    	
    	StringQueryDefinition query = queryManager.newStringDefinition();
    	query.setCriteria("");
    	query.setCollections("/obracunski");
    	
    	search = queryManager.search(query, new SearchHandle());
    	
    	System.out.println("broj pronadjenih: " + search.getMatchResults().length);
    	
    	for (MatchDocumentSummary docSum : search.getMatchResults()) {
    		System.out.println(docSum.getUri());
    	}
			
	}
	
	public void queryTest() {
		
		DatabaseClient dbClient = DatabaseClientFactory.newClient("localhost",
    			8000, "admin", "admin", Authentication.DIGEST);
    	
    	QueryManager queryManager = dbClient.newQueryManager();
    	
    	StructuredQueryBuilder queryBuilder1 = new StructuredQueryBuilder();
    	StructuredQueryDefinition queryDef1 = 
    			queryBuilder1.and(
    					queryBuilder1.collection("/racuni"),
    					queryBuilder1.value(queryBuilder1.element("banka-port"), BankaService.port));
    	
    	SearchHandle searchHandle = queryManager.search(queryDef1, new SearchHandle());
    	for (MatchDocumentSummary docSum : searchHandle.getMatchResults()) {
    		System.out.println(docSum.getUri());
    	}
	}

	public static void main(String[] args) {
		
		BankaDataInit init = new BankaDataInit();
		try {
			init.initData();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		//init.queryTest();

	}

}
