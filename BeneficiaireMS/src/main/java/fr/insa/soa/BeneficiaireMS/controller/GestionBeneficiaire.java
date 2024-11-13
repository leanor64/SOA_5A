package fr.insa.soa.BeneficiaireMS.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.soa.BeneficiaireMS.model.Beneficiaire;

@RestController
public class GestionBeneficiaire {
	
	private int counterId = 1; //TODO : gerer counter
	
	
	
	@GetMapping("/beneficiaires")
	public int benevoleNumber() {
		return 100;
	}
	
	@GetMapping(value="/beneficiaires/{id}")
	public Beneficiaire infosBeneficiaire(@PathVariable int id) {
		Beneficiaire beneficiaire = new Beneficiaire("Esposito", "Cléa", 5, "rue de la paix", "espo@mimi.fr", "1234", id);
		return beneficiaire;
	}
	
	//TODO : penser à incrémenter counter avant nouveau benevole
	
	
}
