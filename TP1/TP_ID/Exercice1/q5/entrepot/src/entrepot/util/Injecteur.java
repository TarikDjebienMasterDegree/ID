package entrepot.util;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import entrepot.bean.dimension.Categorie;
import entrepot.bean.dimension.Client;
import entrepot.bean.dimension.Fournisseur;
import entrepot.bean.dimension.Produit;
import entrepot.bean.dimension.Temps;
import entrepot.bean.dimension.Vendeur;
import entrepot.bean.fait.Fait_achat;
import entrepot.bean.fait.Fait_ventes;
import entrepot.dao.bdd.BddDAO;
import entrepot.dao.factory.BddDAOFactory;
import entrepot.model.Entrepot;

/**
 * Classe qui parse un fichier XML avec SAX pour alimenter la BDD
 * @author tarik
 *
 */
public class Injecteur extends DefaultHandler{

	private Entrepot model;

	public Entrepot getModel(){
		return this.model;
	}

	private BddDAO<Categorie> categorieDAO;
	private BddDAO<Client> clientDAO;
	private BddDAO<Fournisseur> fournisseurDAO;
	private BddDAO<Produit> produitDAO;
	private BddDAO<Temps> tempsDAO;
	private BddDAO<Vendeur> vendeurDAO;
	private BddDAO<Fait_achat> faitAchatDAO;
	private BddDAO<Fait_ventes> faitVentesDAO;

	// LES DIMENSIONS
	Temps temps = null; 
	boolean inDimTemps = false;

	Vendeur vendeur = null; 
	boolean inDimVendeur = false;

	Categorie categorie = null; 
	boolean inDimCategorie,
	inNomCategorie,
	inDescription,
	inIllustration_url = false;

	Produit produit = null; 
	boolean inDimProduit,
	inNomProduit,
	inQuantiteUnite,
	inPrixUnite,
	inUniteStock,
	inUniteCommandee,
	inNiveauReappro = false;

	Client client = null; 
	boolean inDimClient,
	inSociete,
	inContact,
	inFonction,
	inAdresse,
	inVille,
	inRegion,
	inCodePostal,
	inPays,
	inTelephone,
	inFax = false;

	Fournisseur fournisseur=null; 
	boolean inDimFournisseur = false;

	// LES FAITS

	Fait_ventes fait_ventes=null;
	boolean inFaitVentes, inClient, inVendeur=false;

	Fait_achat fait_achat=null;
	boolean inFaitAchat, inFournisseur=false;

	//dimension commune au deux faits
	boolean inTemps, inProduit = false;

	public Injecteur(BddDAOFactory fabriqueDAO, Entrepot model){
		this.categorieDAO=fabriqueDAO.getCategorieDAO();
		this.clientDAO=fabriqueDAO.getClientDAO();
		this.fournisseurDAO=fabriqueDAO.getFournisseurDAO();
		this.produitDAO=fabriqueDAO.getProduitDAO();
		this.tempsDAO=fabriqueDAO.getTempsDAO();
		this.vendeurDAO=fabriqueDAO.getVendeurDAO();
		this.faitAchatDAO=fabriqueDAO.getFait_achatDAO();
		this.faitVentesDAO=fabriqueDAO.getFait_ventesDAO();
		this.model=model;
	}

	/**
	 * @return the categorie Data Access Object
	 */
	public BddDAO<Categorie> getCategorieDAO() {
		return categorieDAO;
	}

	/**
	 * @return the client Data Access Object
	 */
	public BddDAO<Client> getClientDAO() {
		return clientDAO;
	}

	/**
	 * @return the fournisseur Data Access Object
	 */
	public BddDAO<Fournisseur> getFournisseurDAO() {
		return fournisseurDAO;
	}

	/**
	 * @return the produit Data Access Object
	 */
	public BddDAO<Produit> getProduitDAO() {
		return produitDAO;
	}

	/**
	 * @return the temps Data Access Object
	 */
	public BddDAO<Temps> getTempsDAO() {
		return tempsDAO;
	}

	/**
	 * @return the vendeur Data Access Object
	 */
	public BddDAO<Vendeur> getVendeurDAO() {
		return vendeurDAO;
	}

	/**
	 * @return the faitAchat Data Access Object
	 */
	public BddDAO<Fait_achat> getFaitAchatDAO() {
		return faitAchatDAO;
	}

	/**
	 * @return the faitVentes Data Access Object
	 */ 
	public BddDAO<Fait_ventes> getFaitVentesDAO() {
		return faitVentesDAO;
	}

	/**
	 * Evenement envoye au demarrage du parse du flux xml.
	 * @throws SAXException en cas de probleme quelquonque ne permettant pas de
	 * se lancer dans l'analyse du document.
	 * @see org.xml.sax.ContentHandler#startDocument()
	 */
	public void startDocument() throws SAXException {

	}

	/**
	 * Evenement envoye a la fin de l'analyse du flux xml.
	 * @throws SAXException en cas de probleme quelquonque ne permettant pas de
	 * considerer l'analyse du document comme etant complete.
	 * @see org.xml.sax.ContentHandler#endDocument()
	 */
	public void endDocument() throws SAXException {

	}

	/**
	 * Evenement recu a chaque fois que l'analyseur rencontre une balise xml ouvrante.
	 * @param nameSpaceURI l'url de l'espace de nommage.
	 * @param localName le nom local de la balise.
	 * @param rawName nom de la balise en version 1.0 <code>nameSpaceURI + ":" + localName</code>
	 * @throws SAXException si la balise ne correspond pas a ce qui est attendu,
	 * comme par exemple non respect d'une dtd.
	 * @see org.xml.sax.ContentHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	public void startElement(String nameSpaceURI, String localName, String rawName, Attributes attributs) throws SAXException {

		// DIMENSION TEMPS
		if(localName.equals("dim_temps")) {
			inDimTemps = true;
			temps = new Temps();
			temps.tempsMapper(
					attributs.getValue("id"), 
					attributs.getValue("semaine"), 
					attributs.getValue("mois"),
					attributs.getValue("trimestre"),
					attributs.getValue("annee")
					);
			this.getModel().addDimension(temps);
			this.getTempsDAO().create(temps);
		}

		// DIMENSION VENDEURS
		if(localName.equals("dim_vendeur")) {
			inDimVendeur = true;
			vendeur = new Vendeur();
			vendeur.vendeurMapper(attributs.getValue("code_vendeur"));
			this.getModel().addDimension(vendeur);
			this.getVendeurDAO().create(vendeur);
		}

		// DIMENSION CATEGORIE
		if(localName.equals("dim_categorie")) {
			inDimCategorie = true;
			categorie = new Categorie();
			categorie.setCode_categorie(attributs.getValue("code_categorie"));
		}
		if(localName.equals("nom_categorie")) inNomCategorie=true;
		if(localName.equals("description")) inDescription=true;
		if(localName.equals("illustration_url")) inIllustration_url=true;

		// DIMENSION PRODUIT
		if(localName.equals("dim_produit")) {
			inDimProduit = true;
			produit = new Produit();
			produit.setRef_produit(attributs.getValue("ref_produit"));
			produit.setCode_categorie(attributs.getValue("code_categorie"));
			produit.setIndisponible(Integer.parseInt(attributs.getValue("indisponible")));
		}
		if(localName.equals("nom_produit")) inNomProduit=true;
		if(localName.equals("quantite_unite")) inQuantiteUnite=true;
		if(localName.equals("prix_unite")) inPrixUnite=true;
		if(localName.equals("unite_stock")) inUniteStock=true;
		if(localName.equals("unite_commandee")) inUniteCommandee=true;
		if(localName.equals("niveau_reappro")) inNiveauReappro=true;

		// DIMENSION CLIENT
		if(localName.equals("dim_client")) {
			inDimClient = true;
			client = new Client();
			client.setCode_client(attributs.getValue("code_client"));
		}
		if(localName.equals("societe")) inSociete=true;
		if(localName.equals("contact")) inContact=true;
		if(localName.equals("fonction")) inFonction=true;
		if(localName.equals("adresse")) inAdresse=true;
		if(localName.equals("ville")) inVille=true;
		if(localName.equals("region")) inRegion=true;
		if(localName.equals("code_postal")) inCodePostal=true;
		if(localName.equals("pays")) inPays=true;
		if(localName.equals("telephone")) inTelephone=true;
		if(localName.equals("fax")) inFax=true;

		// DIMENSION FOURNISSEUR
		if(localName.equals("dim_fournisseur")) {
			inDimFournisseur = true;
			fournisseur = new Fournisseur();
			fournisseur.fournisseurMapper(attributs.getValue("code_fournisseur"));
			this.getModel().addDimension(fournisseur);
			this.getFournisseurDAO().create(fournisseur);
		}

		// FAIT VENTE
		if(localName.equals("fait_ventes")) {
			inFaitVentes = true;
			fait_ventes = new Fait_ventes();
		}
		if(localName.equals("client")) inClient = true;
		if(localName.equals("vendeur")) inVendeur = true;

		// FAIT ACHAT
		if(localName.equals("fait_achat")) {
			inFaitAchat = true;
			fait_achat = new Fait_achat();
		}
		if(localName.equals("fournisseur")) inFournisseur = true;

		// FAIT
		if(localName.equals("produit")) inProduit = true;
		if(localName.equals("temps")) inTemps = true;

	}

	/**
	 * Evenement recu a chaque fermeture de balise.
	 * @see org.xml.sax.ContentHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void endElement(String nameSpaceURI, String localName, String rawName) throws SAXException {

		// DIMENSION TEMPS
		if(localName.equals("dim_temps")) {
			inDimTemps = false;
			temps = null;
		}

		// DIMENSION VENDEURS
		if(localName.equals("dim_vendeur")) {
			inDimVendeur = false;
			vendeur = null;
		}

		// DIMENSION CATEGORIE
		if(localName.equals("dim_categorie")) {
			inDimCategorie = false;
			this.getModel().addDimension(categorie);
			this.getCategorieDAO().create(categorie);
			categorie = null;
		}
		if(localName.equals("nom_categorie")) inNomCategorie=false;
		if(localName.equals("description")) inDescription=false;
		if(localName.equals("illustration_url")) inIllustration_url=false;

		// DIMENSION PRODUIT
		if(localName.equals("dim_produit")) {
			inDimProduit = false;
			this.getModel().addDimension(produit);
			this.getProduitDAO().create(produit);
			produit = null;
		}
		if(localName.equals("nom_produit")) inNomProduit=false;
		if(localName.equals("quantite_unite")) inQuantiteUnite=false;
		if(localName.equals("prix_unite")) inPrixUnite=false;
		if(localName.equals("unite_stock")) inUniteStock=false;
		if(localName.equals("unite_commandee")) inUniteCommandee=false;
		if(localName.equals("niveau_reappro")) inNiveauReappro=false;

		// DIMENSION CLIENT
		if(localName.equals("dim_client")) {
			inDimClient = false;
			this.getModel().addDimension(client);
			this.getClientDAO().create(client);
			client = null;
		}
		if(localName.equals("societe")) inSociete=false;
		if(localName.equals("contact")) inContact=false;
		if(localName.equals("fonction")) inFonction=false;
		if(localName.equals("adresse")) inAdresse=false;
		if(localName.equals("ville")) inVille=false;
		if(localName.equals("region")) inRegion=false;
		if(localName.equals("code_postal")) inCodePostal=false;
		if(localName.equals("pays")) inPays=false;
		if(localName.equals("telephone")) inTelephone=false;
		if(localName.equals("fax")) inFax=false;

		// DIMENSION FOURNISSEUR
		if(localName.equals("dim_fournisseur")) {
			inDimFournisseur = false;
			fournisseur = null;
		}

		// FAIT VENTE
		if(localName.equals("fait_ventes")) {
			inFaitVentes = false;
			fait_ventes = null;
		}
		if(localName.equals("client")) {
			inClient = false;
			if(inFaitVentes) fait_ventes.setClient(client);//TODO
		}
		if(localName.equals("vendeur")) {
			inVendeur = false;
		}

		// FAIT ACHAT
		if(localName.equals("fait_achat")) {
			inFaitAchat = false;
			fait_achat = null;
		}
		if(localName.equals("fournisseur")) {
			inFournisseur = false;
		}
		
		// FAIT
		if(localName.equals("produit")) {
			inProduit = false;
		}
		if(localName.equals("temps")) {
			inTemps = false;
		}
	}

	/**
	 * Evenement recu a chaque fois que l'analyseur rencontre des caracteres (entre
	 * deux balises).
	 * @param ch les caracteres proprement dits.
	 * @param start le rang du premier caractere a traiter effectivement.
	 * @param end le nombre de caracteres a traiter effectivement
	 * @see org.xml.sax.ContentHandler#characters(char[], int, int)
	 */
	public void characters(char[] ch, int start, int end) throws SAXException {
		
		// DIMENSION CATEGORIE
		if(inDimCategorie && inNomCategorie) categorie.setNom_categorie(new String(ch,start,end));
		if(inDimCategorie && inDescription) categorie.setDescription(new String(ch,start,end));
		if(inDimCategorie && inIllustration_url) categorie.setIllustration_url(new String(ch,start,end));
		
		// DIMENSION PRODUIT
		if(inDimProduit && inNomProduit) produit.setNom_produit(new String(ch,start,end));
		if(inDimProduit && inQuantiteUnite) produit.setQuantite_unite(new String(ch,start,end));
		if(inDimProduit && inPrixUnite) produit.setPrix_unite(Float.parseFloat(new String(ch,start,end)));
		if(inDimProduit && inUniteStock) produit.setUnite_stock(Float.parseFloat(new String(ch,start,end)));
		if(inDimProduit && inUniteCommandee) produit.setUnite_commandee(Float.parseFloat(new String(ch,start,end)));
		if(inDimProduit && inNiveauReappro) produit.setNiveau_reappro(Float.parseFloat(new String(ch,start,end)));
		
		// DIMENSION CLIENT
		if(inDimClient && inSociete) client.setSociete(new String(ch,start,end));
		if(inDimClient && inContact) client.setContact(new String(ch,start,end));
		if(inDimClient && inFonction) client.setFonction(new String(ch,start,end));
		if(inDimClient && inAdresse) client.setAdresse(new String(ch,start,end));
		if(inDimClient && inVille) client.setVille(new String(ch,start,end));
		if(inDimClient && inRegion) client.setRegion(new String(ch,start,end));
		if(inDimClient && inCodePostal) client.setCode_postal(new String(ch,start,end));
		if(inDimClient && inPays) client.setPays(new String(ch,start,end));
		if(inDimClient && inTelephone) client.setTelephone(new String(ch,start,end));
		if(inDimClient && inFax) client.setFax(new String(ch,start,end));

	}

}
