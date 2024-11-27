package fr.insa.soa.BenevoleMS.model;

public class Benevole {
	private String nom;
	private String prenom;
	private int age;
	private String adresse;
	private String email;
	private String mdp;
	private int id;
	
	

	// Constructeurs
	public Benevole(String nom, String prenom, int age, String adresse, String email, String mdp) {
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.adresse = adresse;
		this.email = email;
		this.mdp = mdp;
	}
	
	public Benevole() {}
	
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
	
	
	@Override
	public String toString() {
		return "Benevole [nom=" + nom + ", prenom=" + prenom + ", age=" + age + ", adresse=" + adresse + ", email="
				+ email + ", mdp=" + mdp + ", id=" + id + "]";
	}

	
	
}