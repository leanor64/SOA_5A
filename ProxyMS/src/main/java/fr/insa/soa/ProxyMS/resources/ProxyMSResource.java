package fr.insa.soa.ProxyMS.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import fr.insa.soa.ProxyMS.model.Beneficiaire;
import fr.insa.soa.ProxyMS.model.Benevole;
import jakarta.ws.rs.core.MediaType;


@RestController
@RequestMapping("/benevolat")
public class ProxyMSResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	//Gestion de l'authentification d'un utilisateur (bénévole ou bénéficiaire)
	@GetMapping(value="/auth/{table}/{email}/{mdp}")
	public String authentification(@PathVariable String table, @PathVariable String email, @PathVariable String mdp) {
		boolean result = restTemplate.getForObject("http://AuthentificationMS/auth/"+table+"/"+email+"/"+mdp, Boolean.class);
		
		if (result) {
			if (table == "beneficiaires") {
				Beneficiaire benef = restTemplate.getForObject("http://BeneficiaireMS/beneficiaires/"+email, Beneficiaire.class);
				return benef.toString();
			} else {
				Benevole benev = restTemplate.getForObject("http://BenevoleMS/benevoles/"+email, Benevole.class)	;		
				return benev.toString();
			}
		} else {
			return "Mauvais identifiants";
		}
	}
	
	//Gestion de la création d'un bénévole
	@PostMapping(value="/createBenev", consumes = MediaType.APPLICATION_JSON)
	public String creationBenevole(@RequestBody Benevole b) {
		Benevole benev = restTemplate.postForObject("http://BenevoleMS/createBenev",b, Benevole.class)	;		
		return "Bénévole ajouté : "+benev.toString();
	}
	
	//Gestion de la création d'un bénéficiaire
	@PostMapping(value="/createBenef", consumes = MediaType.APPLICATION_JSON)
	public String creationBeneficiaire(@RequestBody Beneficiaire b) {
		Beneficiaire benef = restTemplate.postForObject("http://BeneficiaireMS/createBenef",b, Beneficiaire.class)	;		
		return "Bénéficiaire ajouté : "+benef.toString();
	}
	
	//Gestion de la modification d'un bénévole
	@PutMapping(value="/modifBenev", consumes = MediaType.APPLICATION_JSON)
	public String modifBenevole(@RequestBody Benevole b) {
   	restTemplate.put("http://BenevoleMS/updateBenev",b)	;   	
   	return "Bénévole modifié : "+b.toString();
	}
	
	//Gestion de la modification d'un bénéficiaire
	@PutMapping(value="/modifBenef", consumes = MediaType.APPLICATION_JSON)
	public String modifBeneficiaire(@RequestBody Beneficiaire b) {
   	restTemplate.put("http://BeneficiaireMS/updateBenef",b)	;   	
   	return "Bénéficiaire modifié : "+b.toString();
	}
	
	//Gestion de la suppression d'un utilisateur
	@DeleteMapping(value="/delete/{table}/{email}")
	public String suppressionBenevole(@PathVariable String table, @PathVariable String email) {
		if (table.equals("beneficiaires")) {
			System.out.println("test1");
			int id = restTemplate.getForObject("http://BeneficiaireMS/id/"+email, int.class);	
			System.out.println("test2");
			restTemplate.delete("http://BeneficiaireMS/deleteBenef/"+id);
        	return "Utilisateur bénéficiaire = "+email+" supprimé avec succès.";

   	} else {
    		int id = restTemplate.getForObject("http://BenevoleMS/id/"+email, int.class);	
        	restTemplate.delete("http://BenevoleMS/deleteBenev/"+id)	;   	
        	return "Utilisateur bénévole = "+email+" supprimé avec succès.";
   	}
	}

}