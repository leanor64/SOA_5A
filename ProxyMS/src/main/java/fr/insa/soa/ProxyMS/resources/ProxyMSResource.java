package fr.insa.soa.ProxyMS.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import fr.insa.soa.ProxyMS.model.Beneficiaire;
import fr.insa.soa.ProxyMS.model.Benevole;


@RestController
@RequestMapping("/benevolat")
public class ProxyMSResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping(value="/auth/{table}/{email}/{mdp}")
	public String authentification(@PathVariable String table, @PathVariable String email, @PathVariable String mdp) {
		boolean result = restTemplate.getForObject("http://AuthentificationMS/auth/"+table+"/"+email+"/"+mdp, Boolean.class);
		
		if (result) {
			Beneficiaire beneficiaire = restTemplate.getForObject("http://BeneficiaireMS/beneficiaires/"+email, Beneficiaire.class);
			return beneficiaire.toString();
		} else {
			return "Mauvais identifiants";
		}
	}
}