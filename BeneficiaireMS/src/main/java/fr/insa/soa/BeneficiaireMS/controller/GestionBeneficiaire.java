package fr.insa.soa.BeneficiaireMS.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.soa.BeneficiaireMS.model.Beneficiaire;

@RestController
public class GestionBeneficiaire {

	String urlDB = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_059";
	String userDB = "projet_gei_059";
	String pswdDB = "ooB2quae";


	@GetMapping(value = "/beneficiaires/{email}")
	public Beneficiaire infosBeneficiaire(@PathVariable String email) {
		Beneficiaire benef = new Beneficiaire();
		Connection conn;
		try {
			conn = DriverManager.getConnection(urlDB, userDB, pswdDB);
			Statement state = conn.createStatement();
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
		}

		return benef;
	}

}
