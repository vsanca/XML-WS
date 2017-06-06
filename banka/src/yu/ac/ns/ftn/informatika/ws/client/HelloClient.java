package yu.ac.ns.ftn.informatika.ws.client;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import yu.ac.ns.ftn.informatika.ws.hello.HelloDocument;
import yu.ac.ns.ftn.informatika.ws.hello.HelloDocumentService;
import yu.ac.ns.ftn.informatika.ws.hello.types.RequestMissType;

public class HelloClient {

    public void testIt1() {
    	
		try {
			URL wsdlLocation = new URL("http://localhost:8080/banka/services/HelloDocument?wsdl");
			QName serviceName = new QName("http://informatika.ftn.ns.ac.yu/ws/hello", "HelloDocumentService");
			QName portName = new QName("http://informatika.ftn.ns.ac.yu/ws/hello", "HelloDocumentPort");

			Service service = Service.create(wsdlLocation, serviceName);
			
			HelloDocument hello = service.getPort(portName, HelloDocument.class); 
			
			RequestMissType request = new RequestMissType();
			request.setFirstName("Dijana");
			request.setLastName("Ninkovic");
			
			String response = hello.sayHelloMiss(request);
			System.out.println("Response from WS: " + response);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
    }
    
    public void testIt2() {
    	//kreiranje web servisa sa generisanom klasom
    	try {
			//kod za kreiranje service objekta iz prethodne metode je samo ugradjen u HelloDocumentService klasu
    		HelloDocumentService service = new HelloDocumentService(new URL("http://localhost:8080/vezbe-wsdl-ws/services/HelloDocument?wsdl"));
			
    		//preuzimanje interfjesa
			HelloDocument hello = service.getHelloDocumentPort();

			RequestMissType request = new RequestMissType();
			request.setFirstName("Nikolina");
			request.setLastName("Ninkovic");
			
			String response = hello.sayHelloMiss(request);
			System.out.println("Response from WS: " + response);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
    }
	
	public static void main(String[] args) {
		
		HelloClient client = new HelloClient();
		client.testIt1();
		client.testIt2();
    }

}