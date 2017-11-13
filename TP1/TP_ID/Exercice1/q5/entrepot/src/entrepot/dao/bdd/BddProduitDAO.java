package entrepot.dao.bdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import entrepot.bean.dimension.Produit;

public class BddProduitDAO extends BddDAO<Produit> {

	public BddProduitDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Produit obj) {
		int res = 0;
		try{
			String query = 
					"INSERT INTO produit (ref_produit, nom_produit, code_categorie, quantite_unite, prix_unite, unite_stock, unite_commandee, niveau_reappro, indisponible) "+
					"VALUES ('"
							+obj.getRef_produit()+"', '"
							+obj.getNom_produit()+"', '"
							+obj.getCode_categorie()+"', '"
							+obj.getQuantite_unite()+"', "
							+obj.getPrix_unite()+", "
							+obj.getUnite_stock()+", "
							+obj.getUnite_commandee()+", "
							+obj.getNiveau_reappro()+", "
							+obj.getIndisponible()+"'"
							+")";
			res = this.connect.createStatement().executeUpdate(query);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return (res==1);
	}

	@Override
	public boolean delete(Produit obj) {
		int res = 0;
		try{
			String query = "DELETE FROM produit WHERE ref_produit ='"+obj.getRef_produit()+"'";
			res = this.connect.createStatement().executeUpdate(query);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return (res==1);
	}

	@Override
	public boolean update(Produit obj) {
		int res = 0;
		try{
			String query =
					"UPDATE produit SET " +
					"nom_produit = '"+obj.getNom_produit()+"', "+
					"code_categorie = '"+obj.getCode_categorie()+"', "+
					"quantite_unite = '"+obj.getQuantite_unite()+"', "+
					"prix_unite = "+obj.getPrix_unite()+", "+
					"unite_stock = "+obj.getUnite_stock()+", "+
					"unite_commandee = "+obj.getUnite_commandee()+", "+
					"niveau_reappro = "+obj.getNiveau_reappro()+", "+
					"indisponible = "+obj.getIndisponible()+", "+
					" WHERE ref_produit ='"+obj.getRef_produit()+"'";
			res = this.connect.createStatement().executeUpdate(query);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return (res==1);
	}

	@Override
	public Produit find(String ref_produit) {
		Produit produit = new Produit();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
							"SELECT * FROM produit WHERE ref_produit = '"+ref_produit+"'"	
							);
			if(result.first()){
				produit.setRef_produit(result.getString("ref_produit"));
				produit.setNom_produit(result.getString("nom_produit"));
				produit.setCode_categorie(result.getString("code_categorie"));
				produit.setQuantite_unite(result.getString("quantite_unite"));
				produit.setPrix_unite(result.getFloat("prix_unite"));
				produit.setUnite_stock(result.getFloat("unite_stock"));
				produit.setUnite_commandee(result.getFloat("unite_commandee"));
				produit.setNiveau_reappro(result.getFloat("niveau_reappro"));
				produit.setIndisponible(result.getInt("indisponible"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return produit;
	}

}
