package entrepot.dao.factory;

import entrepot.dao.contrat.IDAO;


public abstract class AbstractDAOFactory {

	public static final int BDD_DAO_FACTORY = 0;
	public static final int XML_DAO_FACTORY = 1;

	//Retourne un objet Categorie interagissant avec la BDD ou le XML
	public abstract IDAO getCategorieDAO();

	//Retourne un objet Client interagissant avec la BDD ou le XML
	public abstract IDAO getClientDAO();

	//Retourne un objet Fournisseur interagissant avec la BDD ou le XML
	public abstract IDAO getFournisseurDAO();

	//Retourne un objet Produit interagissant avec la BDD ou le XML
	public abstract IDAO getProduitDAO();
	
	//Retourne un objet Temps interagissant avec la BDD ou le XML
	public abstract IDAO getTempsDAO();
	
	//Retourne un objet Vendeur interagissant avec la BDD ou le XML
    public abstract IDAO getVendeurDAO();
    
    //Retourne un objet Fait_achat interagissant avec la BDD ou le XML
  	public abstract IDAO getFait_achatDAO();
	
    //Retourne un objet Fait_ventes interagissant avec la BDD ou le XML
  	public abstract IDAO getFait_ventesDAO();
  	
	//Methode permettant de recuperer les Factory
	public static AbstractDAOFactory getFactory(int type){
		switch(type){
		  case BDD_DAO_FACTORY:
			  return new BddDAOFactory();
		  case XML_DAO_FACTORY:
			  return new XmlDAOFactory();
	      default:
	    	  return null;
		}
	}

}