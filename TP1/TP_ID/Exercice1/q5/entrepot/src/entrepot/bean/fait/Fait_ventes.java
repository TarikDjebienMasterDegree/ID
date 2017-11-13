package entrepot.bean.fait;

import entrepot.bean.contrat.IFait;
import entrepot.bean.dimension.Client;
import entrepot.bean.dimension.Produit;
import entrepot.bean.dimension.Temps;
import entrepot.bean.dimension.Vendeur;

/**
 * @author  tarik
 */
public class Fait_ventes implements IFait{
	
	private String code_fait_ventes;
	
	private int id_temps;
	private Temps temps;

	private String code_client;
	private Client client;

	private String ref_produit;
	private Produit produit;

	private String code_vendeur;
	private Vendeur vendeur;
	
	public Fait_ventes(){}

	public void ventesMapper(String code_fait_ventes, String id_temps,
			String code_client, String ref_produit, String code_vendeur){
		this.setCode_fait_ventes(code_fait_ventes);
		this.setId_temps(Integer.parseInt(id_temps));
		this.setCode_client(code_client);
		this.setRef_produit(ref_produit);
		this.setCode_vendeur(code_vendeur);
	}
	
	/**
	 * @return the code_fait_ventes
	 */
	public String getCode_fait_ventes() {
		return code_fait_ventes;
	}

	/**
	 * @param code_fait_ventes the code_fait_ventes to set
	 */
	public void setCode_fait_ventes(String code_fait_ventes) {
		this.code_fait_ventes = code_fait_ventes;
	}

	public Temps getTemps() {
		return temps;
	}

	public void setTemps(Temps temps) {
		this.temps = temps;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Vendeur getVendeur() {
		return vendeur;
	}

	public void setVendeur(Vendeur vendeur) {
		this.vendeur = vendeur;
	}

	public int getId_temps() {
		return id_temps;
	}

	public void setId_temps(int id_temps) {
		this.id_temps = id_temps;
	}

	public String getCode_client() {
		return code_client;
	}

	public void setCode_client(String code_client) {
		this.code_client = code_client;
	}

	public String getRef_produit() {
		return ref_produit;
	}

	public void setRef_produit(String ref_produit) {
		this.ref_produit = ref_produit;
	}

	public String getCode_vendeur() {
		return code_vendeur;
	}

	public void setCode_vendeur(String code_vendeur) {
		this.code_vendeur = code_vendeur;
	}
	
	public boolean equals(Fait_ventes fait_ventes){
		return     this.getClient().equals(fait_ventes.getClient())
				&& this.getVendeur().equals(fait_ventes.getVendeur())
				&& this.getProduit().equals(fait_ventes.getProduit())
				&& this.getTemps().equals(fait_ventes.getTemps());
	}
}
