package entrepot.dao.factory;

import entrepot.dao.contrat.IDAO;
import entrepot.dao.xml.XmlCategorieDAO;
import entrepot.dao.xml.XmlClientDAO;
import entrepot.dao.xml.XmlFait_achatDAO;
import entrepot.dao.xml.XmlFait_ventesDAO;
import entrepot.dao.xml.XmlFournisseurDAO;
import entrepot.dao.xml.XmlProduitDAO;
import entrepot.dao.xml.XmlTempsDAO;
import entrepot.dao.xml.XmlVendeurDAO;



public class XmlDAOFactory extends AbstractDAOFactory {

	@Override
	public IDAO getCategorieDAO() {
		return new XmlCategorieDAO();
	}

	@Override
	public IDAO getClientDAO() {
		return new XmlClientDAO();
	}

	@Override
	public IDAO getFournisseurDAO() {
		return new XmlFournisseurDAO();
	}

	@Override
	public IDAO getProduitDAO() {
		return new XmlProduitDAO();
	}

	@Override
	public IDAO getTempsDAO() {
		return new XmlTempsDAO();
	}

	@Override
	public IDAO getVendeurDAO() {
		return new XmlVendeurDAO();
	}

	@Override
	public IDAO getFait_achatDAO() {
		return new XmlFait_achatDAO();
	}

	@Override
	public IDAO getFait_ventesDAO() {
		return new XmlFait_ventesDAO();
	}
}