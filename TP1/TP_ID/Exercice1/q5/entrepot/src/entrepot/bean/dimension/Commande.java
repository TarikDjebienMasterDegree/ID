package entrepot.bean.dimension;

import entrepot.bean.contrat.IDimension;

/**
 * @author tarik
 *
 */
public class Commande implements IDimension {
	
	private String num_commande;

	public String getNum_commande() {
		return num_commande;
	}

	public void setNum_commande(String num_commande) {
		this.num_commande = num_commande;
	}
	
	//etc faire le mapping relationnel objet avec les entité en BDD puis completer le parseur SAX et la DTD XML...

}
