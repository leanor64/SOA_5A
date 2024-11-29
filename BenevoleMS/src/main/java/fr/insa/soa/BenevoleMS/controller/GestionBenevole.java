package fr.insa.soa.BenevoleMS.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.soa.BenevoleMS.model.Benevole;
import jakarta.ws.rs.core.MediaType;



@RestController
public class GestionBenevole {
	
	String urlDB = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_059";
	String userDB = "projet_gei_059";
	String pswdDB = "ooB2quae";


	// Pour récupérer un bénévole grâce à son email dans la BDD
	@GetMapping(value = "/benevoles/{email}")
	public Benevole infosBenevole(@PathVariable String email) {
		
		Benevole benev = new Benevole();
		Connection conn = null;
		Statement state = null;
		
		try {
			conn = DriverManager.getConnection(urlDB, userDB, pswdDB);
			state = conn.createStatement();
			String query = "SELECT * FROM benevoles WHERE email='" + email + "';";
			ResultSet result = state.executeQuery(query);
			
			if (result.next()) {
				benev.setNom(result.getString("nom"));
				benev.setPrenom(result.getString("prenom"));
				benev.setAge(result.getInt("age"));
				benev.setAdresse(result.getString("adresse"));
				benev.setEmail(result.getString("email"));
				benev.setId(result.getInt("id"));
			}

			System.out.println(benev);

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

		return benev;
	}
	
	// Pour récupérer un id bénévole grâce à son email
		@GetMapping(value = "/id/{email}")
		public int idBenevole(@PathVariable String email) {
			int id=0;
			
			try {
				Connection conn = DriverManager.getConnection(urlDB, userDB, pswdDB);
				Statement state = conn.createStatement();
				String query = "SELECT id FROM benevoles WHERE email='" + email + "';";
				ResultSet result = state.executeQuery(query);
				
				if (result.next()) {
					id = result.getInt(1);
				}
				state.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return id;
		}


	// Pour ajouter un bénévole dans BDD
	@PostMapping(value = "/createBenev", consumes = MediaType.APPLICATION_JSON)
	public Benevole createBenevole(@RequestBody Benevole benev) {
		
		Connection conn = null;
	    Statement state = null;
	    
		try {
			conn = DriverManager.getConnection(urlDB, userDB, pswdDB);
			state = conn.createStatement();
			
				
			String query = "INSERT INTO benevoles (nom,prenom,age,adresse,email,mdp) VALUES "
					+ "('"+benev.getNom() 
					+ "', '"+benev.getPrenom()
					+ "', "+benev.getAge()
					+ ", '"+benev.getAdresse()
					+ "', '"+benev.getEmail()
					+ "', '"+benev.getMdp()
					+ "');";
			
			int result = state.executeUpdate(query);

	        if (result > 0) {
	            System.out.println("Le bénévole a été ajouté avec succès");
	        } else {
	            System.out.println("Erreur lors de l'ajout du bénévole");
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
		
		return benev;
	}
	
	// Pour modifier un bénévole dans la BDD
	@PutMapping(value = "/updateBenev", consumes = MediaType.APPLICATION_JSON)
	public Benevole updateBenevole(@RequestBody Benevole benev) {
		
	    Connection conn = null;
	    Statement state = null;

	    try {
	        conn = DriverManager.getConnection(urlDB, userDB, pswdDB);
	        state = conn.createStatement();

	        String query = "UPDATE benevoles SET "
	                + "nom = '" + benev.getNom() + "', "
	                + "prenom = '" + benev.getPrenom() + "', "
	                + "age = " + benev.getAge() + ", "
	                + "adresse = '" + benev.getAdresse() + "', "
	                + "email = '" + benev.getEmail() + "', "
	                + "mdp = '" + benev.getMdp() + "'"
	                + "WHERE id = " + benev.getId() + ";";

	        int result = state.executeUpdate(query);

	        if (result > 0) {
	            System.out.println("Le bénévole a été modifié avec succès");
	        } else {
	            System.out.println("Aucun bénévole ne possède cet id");
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

	    return benev;
	}

	// Pour supprimer un bénévole dans la BDD
	@DeleteMapping(value = "/deleteBenev/{id}")
	public String deleteBenevole(@PathVariable int id) {
		
	    Connection conn = null;
	    Statement state = null;

	    try {

	        conn = DriverManager.getConnection(urlDB, userDB, pswdDB);
	        state = conn.createStatement();
	        
	        // Avant de supprimer un bénévole, on doit supprimer ce bénévole des demandes auxquelles il est associé
	        
	        String query = "UPDATE demandes SET "
	                + "idBenevole = 999 "
	                + "WHERE idBenevole = " + id + ";";
	        
	        int res = state.executeUpdate(query);


	        query = "DELETE FROM benevoles WHERE id = " + id + ";";

	        int result = state.executeUpdate(query);

	        if (result > 0) {
	            return "Bénévole avec l'ID " + id + " supprimé avec succès.";
	        } else {
	            return "Aucun bénévole trouvé avec l'ID " + id + ".";
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return "Erreur lors de la suppression du bénévole : " + e.getMessage();
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
	

