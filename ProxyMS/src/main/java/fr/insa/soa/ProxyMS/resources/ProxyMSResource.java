package fr.insa.soa.ProxyMS.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/test")
	public int benevoleNumber() {
		Benevole benevole = restTemplate.getForObject("http://BenevoleMS/benevoles", Benevole.class);
		//Beneficiaire beneficiaire = restTemplate.getForObject("http://BeneficiaireMS/beneficiaires", Beneficiaire.class);

		return benevole.getId();
		
	}


}