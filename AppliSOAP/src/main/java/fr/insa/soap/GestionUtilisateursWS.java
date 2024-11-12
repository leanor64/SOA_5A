package fr.insa.soap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName="users")
public class GestionUtilisateursWS {
	
	@WebMethod(operationName="creation")
	public void creationUtilisateur(@WebParam(name="nom") String nom,
									@WebParam(name="prenom") String prenom,
									@WebParam(name="age") int age,
									@WebParam(name="role") int role,
									@WebParam(name="adresse") String adresse,
									@WebParam(name="email") String email,
									@WebParam(name="mdp") String mdp) {
		
		
		//String commande = "INSERT into users VALUES ('" +nom+ "','" +prenom+ "','" +age+"','" +role+ "','" +email+"','" +adresse+"','" +mdp+"');";
		System.out.println ("L\'utilisateur" +nom+ " a été ajouté !");
	}
	
	@WebMethod(operationName="suppression")
	public void suppressionUtilisateur(@WebParam(name="email") String email) {
		
		//String commande = "DELETE FROM users WHERE email='"+email+"';";
		System.out.println ("L\'utilisateur a été supprimé !");
	}
	
	
	@WebMethod(operationName="modification")
	public void modificationUtilisateur(@WebParam(name="nom") String nom,
			@WebParam(name="prenom") String prenom,
			@WebParam(name="age") int age,
			@WebParam(name="role") int role,
			@WebParam(name="adresse") String adresse,
			@WebParam(name="email") String email,
			@WebParam(name="mdp") String mdp) {
		
		//String commande = "UPDATE users SET nom='" +nom+ "', prenom='" +prenom+ "', age='" +age+"', role='" +role+ "', adresse='" +adresse+"', mdp='" +mdp+"' WHERE email='"+email+"';";
		System.out.println ("L\'utilisateur" +nom+ " a été modifié !");
	
	
	}

}
