package entrepot.bean.fait;

import entrepot.bean.contrat.IFait;
import entrepot.bean.dimension.Fournisseur;
import entrepot.bean.dimension.Produit;
import entrepot.bean.dimension.Temps;

/**
 * @author  tarik
 */
public class Fait_achat implements IFait{
	
	private String code_fait_achat;
	
	private String ref_produit;
	private Produit produit;
	
	private int id_temps;
	private Temps temps;
	
	private String code_fournisseur;
	private Fournisseur fournisseur;
	
	public Fait_achat(){}

	public void achatMapper(String code_fait_achat, String ref_produit, String id_temps, String code_fournisseur){
		this.setCode_fait_achat(code_fait_achat);
		this.setRef_produit(ref_produit);
		this.setId_temps(Integer.parseInt(id_temps));
		this.setCode_fournisseur(code_fournisseur);
	}
	
	public String getCode_fait_achat() {
		return code_fait_achat;
	}

	public void setCode_fait_achat(String code_fait_achat) {
		this.code_fait_achat = code_fait_achat;
	}

	public String getRef_produit() {
		return ref_produit;
	}

	public void setRef_produit(String ref_produit) {
		this.ref_produit = ref_produit;
	}

	public int getId_temps() {
		return id_temps;
	}

	public void setId_temps(int id_temps) {
		this.id_temps = id_temps;
	}

	public String getCode_fournisseur() {
		return code_fournisseur;
	}

	public void setCode_fournisseur(String code_fournisseur) {
		this.code_fournisseur = code_fournisseur;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Temps getTemps() {
		return temps;
	}

	public void setTemps(Temps temps) {
		this.temps = temps;
	}

	public Fournisseur getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}
	
	public boolean equals(Fait_achat fait_achat){
		return     this.getFournisseur().equals(fait_achat.getFournisseur())
				&& this.getProduit().equals(fait_achat.getProduit())
				&& this.getTemps().equals(fait_achat.getTemps());
	}
	
}