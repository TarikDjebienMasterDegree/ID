package entrepot.bean.dimension;

import entrepot.bean.contrat.IDimension;

/**
 * @author  tarik
 */
public class Client implements IDimension{

	private String code_client;

	private String societe;

	private String contact;

	private String fonction;

	private String adresse;

	private String ville;

	private String region;

	private String code_postal;

	private String pays;

	private String telephone;

	private String fax;
	
	public Client(){}

	public void clientMapper(String code_client, String societe, String contact, String fonction, String adresse,
			String ville, String region, String code_postal, String pays, String telephone, String fax){
		this.setCode_client(code_client);
		this.setSociete(societe);
		this.setContact(contact);
		this.setFonction(fonction);
		this.setAdresse(adresse);
		this.setVille(ville);
		this.setRegion(region);
		this.setCode_postal(code_postal);
		this.setPays(pays);
		this.setTelephone(telephone);
		this.setFax(fax);
	}
	
	public String getCode_client() {
		return code_client;
	}

	public void setCode_client(String code_client) {
		this.code_client = code_client;
	}

	public String getSociete() {
		return societe;
	}

	public void setSociete(String societe) {
		this.societe = societe;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getFonction() {
		return fonction;
	}

	public void setFonction(String fonction) {
		this.fonction = fonction;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCode_postal() {
		return code_postal;
	}

	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
	
	public boolean equals(Client client){
		return this.getCode_client().equals(client.getCode_client());
	}
}
