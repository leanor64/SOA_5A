package fr.insa.soa.ProxyMS.model;

public class Beneficiaire {
	private String nom;
	private String prenom;
	private int age;
	private String adresse;
	private String email;
	private String mdp;
	private int id;
	private int idValideur;
	
	

	// Constructeurs
	public Beneficiaire(String nom, String prenom, int age, String adresse, String email, String mdp, int id, int idValideur) {
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.adresse = adresse;
		this.email = email;
		this.mdp = mdp;
		this.id = id; //TODO : gerer id avec compteur ?
		this.idValideur = idValideur;	}
	
	

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
	
	public int getIdValideur() {
		return idValideur;
	}

	public void setIdValideur(int idValideur) {
		this.idValideur = idValideur;
	}


	
	
}