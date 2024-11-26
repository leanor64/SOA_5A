package fr.insa.soa.ProxyMS.model;


public class Valideur {
	private int id;
	private String email;
	private String nom;
	
	public Valideur(int id, String email, String nom) {
		super();
		this.id = id;
		this.email = email;
		this.nom = nom;
	}
	public Valideur() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
	
	
}