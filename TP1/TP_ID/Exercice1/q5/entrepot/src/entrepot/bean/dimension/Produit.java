package entrepot.bean.dimension;

import entrepot.bean.contrat.IDimension;

/**
 * @author  tarik
 */
public class Produit implements IDimension{
	

	private String ref_produit;

	private String nom_produit;

	private String code_categorie;
	
	private Categorie categorie;

	private String quantite_unite;

	private float prix_unite;

	private float unite_stock;

	private float unite_commandee;

	private float niveau_reappro;

	private int indisponible;
	
	public Produit(){}

	public void produitMapper(String ref_produit, String nom_produit, String code_categorie,
			String quantite_unite, String prix_unite, String unite_stock, String unite_commandee,
			String niveau_reappro, String indisponible){
		this.setRef_produit(ref_produit);
		this.setNom_produit(nom_produit);
		this.setCode_categorie(code_categorie);
		this.setQuantite_unite(quantite_unite);
		this.setPrix_unite(Float.parseFloat(prix_unite));
		this.setUnite_stock(Float.parseFloat(unite_stock));
		this.setUnite_commandee(Float.parseFloat(unite_commandee));
		this.setNiveau_reappro(Float.parseFloat(niveau_reappro));
		this.setIndisponible(Integer.parseInt(indisponible));
	}
	
	public String getRef_produit() {
		return ref_produit;
	}

	public void setRef_produit(String ref_produit) {
		this.ref_produit = ref_produit;
	}

	public String getNom_produit() {
		return nom_produit;
	}

	public void setNom_produit(String nom_produit) {
		this.nom_produit = nom_produit;
	}

	public String getCode_categorie() {
		return code_categorie;
	}

	public void setCode_categorie(String code_categorie) {
		this.code_categorie = code_categorie;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public String getQuantite_unite() {
		return quantite_unite;
	}

	public void setQuantite_unite(String quantite_unite) {
		this.quantite_unite = quantite_unite;
	}

	public float getPrix_unite() {
		return prix_unite;
	}

	public void setPrix_unite(float prix_unite) {
		this.prix_unite = prix_unite;
	}

	public float getUnite_stock() {
		return unite_stock;
	}

	public void setUnite_stock(float unite_stock) {
		this.unite_stock = unite_stock;
	}

	public float getUnite_commandee() {
		return unite_commandee;
	}

	public void setUnite_commandee(float unite_commandee) {
		this.unite_commandee = unite_commandee;
	}

	public float getNiveau_reappro() {
		return niveau_reappro;
	}

	public void setNiveau_reappro(float niveau_reappro) {
		this.niveau_reappro = niveau_reappro;
	}

	public int getIndisponible() {
		return indisponible;
	}

	public void setIndisponible(int indisponible) {
		this.indisponible = indisponible;
	}
	
	public boolean equals(Produit produit){
		return this.getRef_produit().equals(produit.getRef_produit());
	}
}