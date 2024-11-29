package fr.insa.soa.ProxyMS.resources;

import java.util.ArrayList;
import java.util.List;

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
import fr.insa.soa.ProxyMS.model.Demande;
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
			int id = restTemplate.getForObject("http://BeneficiaireMS/id/"+email, int.class);	
			restTemplate.delete("http://BeneficiaireMS/deleteBenef/"+id);
        	return "Utilisateur bénéficiaire = "+email+" supprimé avec succès.";

   	} else {
    		int id = restTemplate.getForObject("http://BenevoleMS/id/"+email, int.class);	
        	restTemplate.delete("http://BenevoleMS/deleteBenev/"+id)	;   	
        	return "Utilisateur bénévole = "+email+" supprimé avec succès.";
   	}
	}
	
	//Gestion de la récupération de toutes les demandes d'un utilisateur
	@GetMapping(value="/demandes/{typeUtilisateur}/{id}")
	public String getDemandes(@PathVariable String typeUtilisateur, @PathVariable int id) {
		String list = restTemplate.getForObject("http://DemandeMS/demandes/"+typeUtilisateur+"/"+id, String.class);
		return list;
	}
	
	//Gestion de la création d'une demande
	@PostMapping(value="/createDemande", consumes = MediaType.APPLICATION_JSON)
	public String creationDemande(@RequestBody Demande d) {
		Demande dem = restTemplate.postForObject("http://DemandeMS/createDemande",d, Demande.class)	;		
		return "Demande ajoutée : "+dem.toString();
	}
	
	//Gestion de la modification des infos demande
	@PutMapping(value="/modifInfosDemande", consumes = MediaType.APPLICATION_JSON)
	public String modifInfosDemande(@RequestBody Demande d) {
		restTemplate.put("http://DemandeMS/updateInfosDemande",d)	;   	
   	return "Les infos de la demande ont bien été modifiées";
	}
	
	//Gestion de l'acceptation d'une demande
	@PutMapping(value="/acceptDemande/{id}/{idBenevole}")
	public String acceptDemande(@PathVariable int id ,@PathVariable int idBenevole) {
		restTemplate.put("http://DemandeMS/acceptDemande/"+id+"/"+idBenevole,id,idBenevole);   	
		return "Demande modifiée avec succès";
	}
		
	//Gestion de la cloture d'une demande
	@PutMapping(value="/closeDemande/{id}/{note}")
	public String closeDemande(@PathVariable int id ,@PathVariable int note) {
   	restTemplate.put("http://DemandeMS/closeDemande/"+id+"/"+note,id,note)	;   	
   	return "Demande modifiée avec succès";
	}
	
	//Gestion de la suppression d'une demande
		@DeleteMapping(value="/deleteDemande/{id}")
		public String deleteDemande(@PathVariable int id) {
			restTemplate.delete("http://DemandeMS/deleteDemande/"+id);
        	return "Demande supprimée avec succès.";
		}

}