package entrepot.bean.dimension;

import entrepot.bean.contrat.IDimension;

/**
 * @author  tarik
 */
public class Fournisseur implements IDimension{
	
	private String code_fournisseur;
	
	public Fournisseur(){}
	
	public void fournisseurMapper(String code_fournisseur){
		this.setCode_fournisseur(code_fournisseur);
	}

	public String getCode_fournisseur() {
		return code_fournisseur;
	}

	public void setCode_fournisseur(String code_fournisseur) {
		this.code_fournisseur = code_fournisseur;
	}
	
	public boolean equals(Fournisseur fournisseur){
		return this.getCode_fournisseur().equals(fournisseur.getCode_fournisseur());
	}
}
