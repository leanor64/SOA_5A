package fr.insa.soa.AppliREST;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;

public class ClientREST {
	public static void  main(String [] args) {
		Client client = ClientBuilder.newClient();
		Response response = client.target("").request().get();
		System.out.println(response.readEntity(String.class));
	}
}