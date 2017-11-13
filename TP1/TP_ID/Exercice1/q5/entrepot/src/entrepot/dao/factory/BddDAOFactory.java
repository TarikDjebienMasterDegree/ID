package entrepot.dao.factory;

import java.sql.Connection;

import entrepot.bean.dimension.Categorie;
import entrepot.bean.dimension.Client;
import entrepot.bean.dimension.Fournisseur;
import entrepot.bean.dimension.Produit;
import entrepot.bean.dimension.Temps;
import entrepot.bean.dimension.Vendeur;
import entrepot.bean.fait.Fait_achat;
import entrepot.bean.fait.Fait_ventes;
import entrepot.connexion.SingletonConnexion;
import entrepot.dao.bdd.BddCategorieDAO;
import entrepot.dao.bdd.BddClientDAO;
import entrepot.dao.bdd.BddDAO;
import entrepot.dao.bdd.BddFait_achatDAO;
import entrepot.dao.bdd.BddFait_ventesDAO;
import entrepot.dao.bdd.BddFournisseurDAO;
import entrepot.dao.bdd.BddProduitDAO;
import entrepot.dao.bdd.BddTempsDAO;
import entrepot.dao.bdd.BddVendeurDAO;






public class BddDAOFactory extends AbstractDAOFactory {

	protected static final Connection conn = SingletonConnexion.getInstance();

	@Override
	public BddDAO<Categorie> getCategorieDAO() {
		return new BddCategorieDAO(conn);
	}

	@Override
	public BddDAO<Client> getClientDAO() {
		return new BddClientDAO(conn);
	}

	@Override
	public BddDAO<Fournisseur> getFournisseurDAO() {
		return new BddFournisseurDAO(conn);
	}

	@Override
	public BddDAO<Produit> getProduitDAO() {
		return new BddProduitDAO(conn);
	}

	@Override
	public BddDAO<Temps> getTempsDAO() {
		return new BddTempsDAO(conn);
	}

	@Override
	public BddDAO<Vendeur> getVendeurDAO() {
		return new BddVendeurDAO(conn);
	}

	@Override
	public BddDAO<Fait_achat> getFait_achatDAO() {
		return new BddFait_achatDAO(conn);
	}

	@Override
	public BddDAO<Fait_ventes> getFait_ventesDAO() {
		return new BddFait_ventesDAO(conn);
	}
}