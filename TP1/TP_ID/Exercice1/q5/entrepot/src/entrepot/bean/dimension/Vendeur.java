package entrepot.bean.dimension;

import entrepot.bean.contrat.IDimension;

/**
 * @author  tarik
 */
public class Vendeur implements IDimension{
	

	private String code_vendeur;
	
	public Vendeur(){}

	public void vendeurMapper(String code_vendeur){
		this.setCode_vendeur(code_vendeur);
	}
	
	public String getCode_vendeur() {
		return code_vendeur;
	}

	public void setCode_vendeur(String code_vendeur) {
		this.code_vendeur = code_vendeur;
	}
	
	public boolean equals(Vendeur vendeur){
		return this.getCode_vendeur().equals(vendeur.getCode_vendeur());
	}

}
