package fr.insa.soa.ProxyMS.model;

public class Demande {
	private int idBenevole;
	private int idBeneficiare;
	private String titre;
	private String description;
	private Statut statut;
	private int note;
	private int id;
	
	// Constructeur
	public Demande(int idBenevole, int idBeneficiare, String titre, String description, Statut statut, int note) {
		this.idBenevole = idBenevole;
		this.idBeneficiare = idBeneficiare;
		this.titre = titre;
		this.description = description;
		this.statut = statut;
		this.note = note;
	}
	
	public Demande() {}

	// Getters et Setteurs
	public int getIdBenevole() {
		return idBenevole;
	}
	
	public void setIdBenevole(int idBenevole) {
		this.idBenevole = idBenevole;
	}
	
	public int getIdBeneficiare() {
		return idBeneficiare;
	}

	public void setIdBeneficiare(int idBeneficiare) {
		this.idBeneficiare = idBeneficiare;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
		

}