package fr.insa.soa.ProxyMS.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/benevolat")
public class ProxyMSResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/test")
	public int benevoleNumber() {
		int numbenevole = restTemplate.getForObject("http://BenevoleMS/benevoles", int.class);
		int numbeneficiaire = restTemplate.getForObject("http://BeneficiaireMS/beneficiaires", int.class);

		return numbenevole + numbeneficiaire;
		
	}


}