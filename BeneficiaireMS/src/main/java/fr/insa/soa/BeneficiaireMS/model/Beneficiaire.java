package fr.insa.soa.BeneficiaireMS.model;

public class Beneficiaire {
	
	private String nom;
	private String prenom;
	private int age;
	private String adresse;
	private String email;
	private String mdp;
	private int id;
	private String emailValideur;
	
	

	// Constructeurs
	public Beneficiaire(String nom, String prenom, int age, String adresse, String email, String mdp, String emailValideur) {
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.adresse = adresse;
		this.email = email;
		this.mdp = mdp;
		this.emailValideur = emailValideur;
	}
		
	
	

	public Beneficiaire() {}
	
	//Méthodes
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmailValideur() {
		return emailValideur;
	}
	public void setEmailValideur(String emailValideur) {
		this.emailValideur = emailValideur;
	}


	@Override
	public String toString() {
		return "Beneficiaire [nom=" + nom + ", prenom=" + prenom + ", age=" + age + ", adresse=" + adresse + ", email="
				+ email + ", mdp=" + mdp + ", id=" + id + ", emailValideur=" + emailValideur + "]";
	}
	
}