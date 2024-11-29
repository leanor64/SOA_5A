package fr.insa.soa.DemandeMS.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.soa.DemandeMS.model.Demande;
import fr.insa.soa.DemandeMS.model.Statut;
import jakarta.ws.rs.core.MediaType;

@RestController
public class GestionDemande {

	String urlDB = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_059";
	String userDB = "projet_gei_059";
	String pswdDB = "ooB2quae";
	
	//GET : récupérer toutes les demandes d'un utilisateur (bénéficiaire ou bénévole)
	@GetMapping(value = "/demandes/{typeUtilisateur}/{id}")
	public List<Demande> getDemandes(@PathVariable String typeUtilisateur, @PathVariable int id) {
		
		List<Demande> list = new ArrayList<Demande>();
		Connection conn = null;
		Statement state = null;
		
		try {
			conn = DriverManager.getConnection(urlDB, userDB, pswdDB);
			state = conn.createStatement();
			String query = "";
			
	        if (typeUtilisateur.equals("beneficiaire")) {
	            query = "SELECT * FROM demandes WHERE idBeneficiaire = " + id + ";";
	        } else if (typeUtilisateur.equals("benevole")) {
	            query = "SELECT * FROM demandes WHERE idBenevole = " + id + ";";
	        } else {
	            throw new IllegalArgumentException("Type d'utilisateur non valide : " + typeUtilisateur);
	        }
	        
	        ResultSet result = state.executeQuery(query);

	        while (result.next()) {
	            Demande demande = new Demande();
	            demande.setTitre(result.getString("titre"));
	            demande.setDescription(result.getString("description"));
	            demande.setId(result.getInt("id"));
	            

	            list.add(demande);
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

	    return list;
	}


	// Pour ajouter une demande dans la BDD
	@PostMapping(value = "/createDemande", consumes = MediaType.APPLICATION_JSON)
	public Demande createDemande(@RequestBody Demande dem) {
		
		Connection conn = null;
	    Statement state = null;
	    
		try {
			conn = DriverManager.getConnection(urlDB, userDB, pswdDB);
			state = conn.createStatement();
			
				
			String query = "INSERT INTO demandes (idBeneficiaire, titre, description, statut) VALUES "
					+ "("+dem.getIdBeneficiaire()
					+ ", '"+dem.getTitre()
					+ "', '"+dem.getDescription()
					+ "', '"+Statut.PUBLIEE
					+ "');";
			
			int result = state.executeUpdate(query);

	        if (result > 0) {
	            System.out.println("La demande a été ajouté avec succès");
	        } else {
	            System.out.println("Erreur lors de l'ajout de la demande");
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
		
		return dem;
	}
	
	//PUT : Lorsqu'un bénéficiaire souhaite modifier les infos de sa demande
	@PutMapping(value = "/updateInfosDemande", consumes = MediaType.APPLICATION_JSON)
	public Demande updateDemande(@RequestBody Demande dem) {
		
	    Connection conn = null;
	    Statement state = null;

	    try {
	        conn = DriverManager.getConnection(urlDB, userDB, pswdDB);
	        state = conn.createStatement();

	        String query = "UPDATE demandes SET "
	                + "titre = '" + dem.getTitre() + "', "
	                + "description = '" + dem.getDescription() + "' "
	                + "WHERE id = " + dem.getId() + ";";

	        int result = state.executeUpdate(query);

	        if (result > 0) {
	            System.out.println("La demande a été modifié avec succès");
	        } else {
	            System.out.println("Aucune demande ne possède cet id");
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

	    return dem;
	}
	
	//PUT : Lorsqu'un bénévole accepte une demande
	@PutMapping(value = "/acceptDemande/{id}/{idBenevole}")
	public String acceptDemande(@PathVariable int id ,@PathVariable int idBenevole) {
		
	    Connection conn = null;
	    Statement state = null;
	    String r = "?";

	    try {
	        conn = DriverManager.getConnection(urlDB, userDB, pswdDB);
	        state = conn.createStatement();

	        String query = "UPDATE demandes SET "
	                + "idBenevole = " + idBenevole + ", "
	                + "statut = '" + Statut.ACCEPTEE + "' "
	                + "WHERE id = " + id + ";";

	        int result = state.executeUpdate(query);

	        if (result > 0) {
	            r = "La demande a bien été attribué à ce bénévole";
	        } else {
	            r = "Aucune demande ne possède cet id";
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

	    return r;
	}
	
	
	//PUT : Lorsqu'une demande est terminée
		@PutMapping(value = "/closeDemande/{id}/{note}")
		public String closeDemande(@PathVariable int id ,@PathVariable int note) {
			
		    Connection conn = null;
		    Statement state = null;
		    String r = "?";

		    try {
		        conn = DriverManager.getConnection(urlDB, userDB, pswdDB);
		        state = conn.createStatement();

		        String query = "UPDATE demandes SET "
		                + "statut = '" + Statut.TERMINEE + "', "
		                + "note = " + note + " "
		                + "WHERE id = " + id + ";";

		        int result = state.executeUpdate(query);

		        if (result > 0) {
		            r = "La demande a bien été close";
		        } else {
		            r = "Aucune demande ne possède cet id";
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

		    return r;
		}

	// Pour supprimer une demande de la BDD
	@DeleteMapping(value = "/deleteDemande/{id}")
	public String deleteDemande(@PathVariable int id) {
		
	    Connection conn = null;
	    Statement state = null;

	    try {

	        conn = DriverManager.getConnection(urlDB, userDB, pswdDB);
	        state = conn.createStatement();
	        
	        // Avant de supprimer un bénéficiaire, on doit supprimer les demandes de ce bénéficiaire
	        
	        String query = "DELETE FROM demandes WHERE id = " + id + ";";
	        
	        int res = state.executeUpdate(query);

	        if (res > 0) {
	            return "Demande avec l'ID " + id + " supprimée avec succès.";
	        } else {
	            return "Aucune demande trouvée avec l'ID " + id + ".";
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return "Erreur lors de la suppression de la demande : " + e.getMessage();
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
