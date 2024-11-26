package fr.insa.soa.AuthentificationMS.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GestionAuthentification {
	
	@GetMapping(value="/auth/{table}/{email}/{mdp}")
	public static boolean isAuthentificationOK(@PathVariable String table,@PathVariable String email,@PathVariable String mdp){
		String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_059";
		String user = "projet_gei_059";
		String passwd = "ooB2quae";
		boolean res = false ; 
		
		try {
		    Connection conn = DriverManager.getConnection(url, user, passwd);    
		    System.out.println("********");
		    Statement state = conn.createStatement();
		    String commande = "";

			commande = "SELECT COUNT(*) FROM "+table+" WHERE email = '"+email+"' AND mdp = '"+mdp+"';";

		    ResultSet result = state.executeQuery(commande);
		    if (result.next() && result.getInt(1) == 1) {
		    	res = true ; 
		    } 
		    result.close();
		    state.close();
			conn.close();

		    
		} catch (Exception exce){
		    exce.printStackTrace();
		    System.out.println("Erreur");
	    	    System.exit(0);
	    }
		System.out.println("test authentification");
		return res ;
	}
	
}