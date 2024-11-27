package fr.insa.soa.BeneficiaireMS.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.soa.BeneficiaireMS.model.Beneficiaire;
import jakarta.ws.rs.core.MediaType;

@RestController
public class GestionBeneficiaire {

	String urlDB = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_059";
	String userDB = "projet_gei_059";
	String pswdDB = "ooB2quae";


	// Pour récupérer un bénéficiaire grâce à son email dans la BDD
	@GetMapping(value = "/beneficiaires/{email}")
	public Beneficiaire infosBeneficiaire(@PathVariable String email) {
		
		Beneficiaire benef = new Beneficiaire();
		Connection conn = null;
		Statement state = null;
		
		try {
			conn = DriverManager.getConnection(urlDB, userDB, pswdDB);
			state = conn.createStatement();
			String query = "SELECT * FROM beneficiaires WHERE email='" + email + "';";
			ResultSet result = state.executeQuery(query);
			
			if (result.next()) {
				benef.setNom(result.getString("nom"));
				benef.setPrenom(result.getString("prenom"));
				benef.setAge(result.getInt("age"));
				benef.setAdresse(result.getString("adresse"));
				benef.setEmail(result.getString("email"));
				benef.setId(result.getInt("id"));
			}

			System.out.println(benef);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
	        try {
	            if (state != null) state.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

		return benef;
	}

	// Pour ajouter un bénéficiciaire dans BDD
	@PostMapping(value = "/createBenef", consumes = MediaType.APPLICATION_JSON)
	public Beneficiaire createBeneficiaire(@RequestBody Beneficiaire benef) {
		
		Connection conn = null;
	    Statement state = null;
	    
		try {
			conn = DriverManager.getConnection(urlDB, userDB, pswdDB);
			state = conn.createStatement();
			
				
			String query = "INSERT INTO beneficiaires (nom,prenom,age,adresse,email,mdp,emailValideur) VALUES "
					+ "('"+benef.getNom() 
					+ "', '"+benef.getPrenom()
					+ "', "+benef.getAge()
					+ ", '"+benef.getAdresse()
					+ "', '"+benef.getEmail()
					+ "', '"+benef.getMdp()
					+ "', '"+benef.getEmailValideur()
					+ "');";
			
			int result = state.executeUpdate(query);

	        if (result > 0) {
	            System.out.println("Le bénéficiare a été ajouté avec succès");
	        } else {
	            System.out.println("Aucun bénéficiaire ne possède cet id");
	        }
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
	        try {
	            if (state != null) state.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
		
		return benef;
	}
	
	// Pour modifier un bénéficiaire dans la BDD
	@PutMapping(value = "/updateBenef", consumes = MediaType.APPLICATION_JSON)
	public Beneficiaire updateBeneficiaire(@RequestBody Beneficiaire benef) {
		
	    Connection conn = null;
	    Statement state = null;

	    try {
	        conn = DriverManager.getConnection(urlDB, userDB, pswdDB);
	        state = conn.createStatement();

	        String query = "UPDATE beneficiaires SET "
	                + "nom = '" + benef.getNom() + "', "
	                + "prenom = '" + benef.getPrenom() + "', "
	                + "age = " + benef.getAge() + ", "
	                + "adresse = '" + benef.getAdresse() + "', "
	                + "email = '" + benef.getEmail() + "', "
	                + "mdp = '" + benef.getMdp() + "', "
	                + "emailValideur = '" + benef.getEmailValideur() + "' "
	                + "WHERE id = " + benef.getId() + ";";

	        int result = state.executeUpdate(query);

	        if (result > 0) {
	            System.out.println("Le bénéficiaire a été modifié avec succès");
	        } else {
	            System.out.println("Aucun bénéficiaire ne possède cet id");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (state != null) state.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return benef;
	}

	// Pour supprimer un bénéficiaire dans la BDD
	@DeleteMapping(value = "/deleteBenef/{id}")
	public String deleteBeneficiaire(@PathVariable int id) {
		
	    Connection conn = null;
	    Statement state = null;

	    try {

	        conn = DriverManager.getConnection(urlDB, userDB, pswdDB);
	        state = conn.createStatement();
	        
	        // Avant de supprimer un bénéficiaire, on doit supprimer les demandes de ce bénéficiaire
	        
	        String query = "DELETE FROM demandes WHERE idBeneficiaire = " + id + ";";
	        
	        int res = state.executeUpdate(query);


	        query = "DELETE FROM beneficiaires WHERE id = " + id + ";";

	        int result = state.executeUpdate(query);

	        if (result > 0) {
	            return "Bénéficiaire avec l'ID " + id + " supprimé avec succès.";
	        } else {
	            return "Aucun bénéficiaire trouvé avec l'ID " + id + ".";
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return "Erreur lors de la suppression du bénéficiaire : " + e.getMessage();
	    } finally {
	        try {
	            if (state != null) state.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	

}
