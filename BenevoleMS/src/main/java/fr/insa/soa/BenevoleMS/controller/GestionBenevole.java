package fr.insa.soa.BenevoleMS.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.soa.BenevoleMS.model.Benevole;

@RestController
public class GestionBenevole {
	
	private int counterId = 1; //TODO : gerer counter
	
	
	
	@GetMapping("/benevoles")
	public int benevoleNumber() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Called !");
		return 100;
	}
	
	@GetMapping(value="/benevoles/{id}")
	public Benevole infosBenevole(@PathVariable int id) {
		Benevole benevole = new Benevole("Esposito", "Cléa", 5, "rue de la paix", "espo@mimi.fr", "1234", id);
		return benevole;
	}
	
	//TODO : penser à incrémenter counter avant nouveau benevole
	
	
}

