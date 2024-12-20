package fr.insa.soap;

public class Utilisateur {
	private String nom;
	private String prenom;
	private int age;
	// 0=bénéficiaire, 1= bénévole, 2=valideur
	private int role;
	private String adresse;
	private String email;
	private String mdp;
	
	
	// Constructeurs
	public Utilisateur(String nom, String prenom, int age, int role, String adresse, String email, String mdp) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.role = role;
		this.adresse = adresse;
		this.email = email;
		this.mdp = mdp;
	}
	
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
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
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
	
	
	

}
