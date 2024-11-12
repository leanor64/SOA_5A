package fr.insa.soa.AppliREST;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;


@Path("auth")
public class GestionAuthentificationWS {
	
	// Pour recuperer un utilisateur dans la base de données
	@GET
	@Path("recuperation")
	public void recuperationUtilisateur(@PathParam("email") String email) {
		
		System.out.println("Il existe un utilisateur avec cet email");
	}
	
	// Verifier les identifiants (email et mot de passe) d'un utilisateur
	@GET
	@Path("verification")
	public Boolean verificationUtilisateur(@PathParam("email") String email, @PathParam("mdp") String mdp) {
		
		Utilisateur u = new Utilisateur(); // get utilisateur par email
		
		//Le mot de passe correspondant à "email" dans la base de données
		String mdpDB = u.getMdp();
		boolean result = false;
		
		if (mdpDB.equals(mdp)) {
			result = true;
			System.out.println("Les identifiants sont valides!");
		} else {
			System.out.println("Les identifiants sont invalides!");
		}
		return result;
	}

}
