package fr.insa.soap;

import java.net.MalformedURLException;
import javax.xml.ws.Endpoint;

public class SOAPApplication {
	
	public static String host="localhost";
	public static short port1=8089;
	public static short port2=8099;
	
	public void demarrerService() {
		String url1 = "http://" + host + ":" + port1 + "/";
		String url2 = "http://" + host + ":" + port2 + "/";
		Endpoint.publish(url1, new GestionUtilisateursWS());
		Endpoint.publish(url2, new GestionAuthentificationWS());
	}
	
	public static void main(String [] args) throws MalformedURLException {
		
		new SOAPApplication().demarrerService();
		System.out.println("Service démarré.");
	}

}
