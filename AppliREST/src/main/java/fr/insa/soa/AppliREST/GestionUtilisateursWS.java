package fr.insa.soa.AppliREST;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@Path("users")
public class GestionUtilisateursWS {
	
	@POST
	@Path("/creation")
	@Produces(MediaType.APPLICATION_JSON)
	public void creationUtilisateur(@PathParam("nom") String nom,
									@PathParam("prenom") String prenom,
									@PathParam("age") int age,
									@PathParam("role") int role,
									@PathParam("adresse") String adresse,
									@PathParam("email") String email,
									@PathParam("mdp") String mdp) {
		
		Utilisateur u = new Utilisateur(nom,
										prenom,
										age,
										role,
										adresse,
										email,
										mdp);
		
		
		System.out.println ("L\'utilisateur" +prenom+ " " +nom+ " a été ajouté !");
	}
	
	@DELETE
	@Path("suppression")
	//TODO : Produces ? 
	public void suppressionUtilisateur(@PathParam("email") String email) {
		
		System.out.println ("L\'utilisateur ayant l'email : "+email +"  a été supprimé !");
	}
	
	@PUT
	@Path("modification")
	public void modificationUtilisateur(@PathParam("nom") String nom,
										@PathParam("prenom") String prenom,
										@PathParam("age") int age,
										@PathParam("role") int role,
										@PathParam("adresse") String adresse,
										@PathParam("email") String email,
										@PathParam("mdp") String mdp) {
		
		Utilisateur u = new Utilisateur(); //Récupération du bon utilisateur
		u.setNom(nom);
		u.setAdresse(adresse);
		u.setAge(age);
		u.setRole(role);
		u.setPrenom(prenom);
		u.setMdp(mdp);
		u.setEmail(email);
		
		
		System.out.println ("L\'utilisateur" +prenom+ " " +nom+ " a été modifié !");
	
	
	}

}
