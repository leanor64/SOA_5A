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
	public void creationUtilisateur(@WebParam(name="nom") String nom, @WebParam(name="prenom") String prenom, @WebParam(name="age") int age, @WebParam(name="role") int role, @WebParam(name="adresse") String adresse, @WebParam(name="email") String email, @WebParam(name="mdp") String mdp) {
		
		
		String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/";
		String user = "projet_gei_059";
		String passwd = "ooB2quae";
		
		try {
		    Connection conn = DriverManager.getConnection(url, user, passwd);            	    
		    Statement state = conn.createStatement();
		    
		    //insérer la personne dans la database
			String commande = "INSERT into users VALUES ('" +nom+ "','" +prenom+ "','" +age+"','" +role+ "','" +email+"','" +adresse+"','" +mdp+"');";
		    state.executeUpdate(commande);
	        
	        //fermer la connexion avec la base de données
	        state.close();
			conn.close();
		} catch (Exception e){
		    e.printStackTrace();
		    System.out.println("Erreur..............................");
			System.exit(0);
	    }
		
		System.out.println (nom+ " a été ajouté !");
	}

}
