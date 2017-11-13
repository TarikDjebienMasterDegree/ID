package entrepot.bean.dimension;

import entrepot.bean.contrat.IDimension;

/**
 * @author  tarik
 */
public class Categorie implements IDimension{
	
	private String code_categorie;

	private String nom_categorie;

	private String description;

	private String illustration_url;
	
	public Categorie(){}

	public void categorieMapper(String code_categorie, String nom_categorie, String description,
			String illustration_url){
		this.setCode_categorie(code_categorie);
		this.setNom_categorie(nom_categorie);
		this.setDescription(description);
		this.setIllustration_url(illustration_url);
	}
	
	public String getCode_categorie() {
		return code_categorie;
	}

	public void setCode_categorie(String code_categorie) {
		this.code_categorie = code_categorie;
	}

	public String getNom_categorie() {
		return nom_categorie;
	}

	public void setNom_categorie(String nom_categorie) {
		this.nom_categorie = nom_categorie;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIllustration_url() {
		return illustration_url;
	}

	public void setIllustration_url(String illustration_url) {
		this.illustration_url = illustration_url;
	}
	
	public boolean equals(Categorie categorie){
		return this.getCode_categorie().equals(categorie.getCode_categorie());
	}
	
}
