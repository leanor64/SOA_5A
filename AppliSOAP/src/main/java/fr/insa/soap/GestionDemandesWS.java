package fr.insa.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName="demandes")
public class GestionDemandesWS {
	
	//PAS IMPLEMENTE
	//Nous avons choisi d'implémenter uniquement le service pour la gestion des utilisateurs
	
	@WebMethod(operationName="test")
	public int test(@WebParam(name="t") String t) {
		
		return t.length();
	}

}
