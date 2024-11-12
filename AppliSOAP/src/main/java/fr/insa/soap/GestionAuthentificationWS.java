package fr.insa.soap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName="auth")
public class GestionAuthentificationWS {
	
	// Pour recuperer un utilisateur dans la base de données
	@WebMethod(operationName="recuperation")
	public void recuperationUtilisateur(@WebParam(name="email") String email) {
		
		//commande = "SELECT COUNT(*) FROM users WHERE email = '"+email+"';";
		System.out.println("Il existe un utilisateur avec cet email");
	}
	
	// Verifier les identifiants (email et mot de passe) d'un utilisateur
	@WebMethod(operationName="verification")
	public void verificationUtilisateur(@WebParam(name="email") String email, @WebParam(name="mdp") String mdp) {
		//ResultSet result = state.executeQuery("SELECT * FROM users WHERE email = '"+email+"' AND mdp = '"+mdp+"';");
		System.out.println("Les identifiants sont valides!");
	}

}
