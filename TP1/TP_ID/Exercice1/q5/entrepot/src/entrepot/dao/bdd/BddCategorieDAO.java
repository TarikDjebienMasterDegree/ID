package entrepot.dao.bdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import entrepot.bean.dimension.Categorie;

public class BddCategorieDAO extends BddDAO<Categorie>{

	public BddCategorieDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Categorie categorie) {
		int res = 0;
		try{
			String query = 
					"INSERT INTO categorie (code_categorie, nom_categorie, description, illustration_url) "+
					"VALUES ('"
							+categorie.getCode_categorie()+"', '"
							+categorie.getNom_categorie()+"', '"
							+categorie.getDescription()+"', '"
							+categorie.getIllustration_url()+"'"
							+")";
			res = this.connect.createStatement().executeUpdate(query);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return (res==1);
	}

	@Override
	public boolean delete(Categorie categorie) {
		int res = 0;
		try{
			String query = "DELETE FROM categorie WHERE code_categorie ='"+categorie.getCode_categorie()+"'";
			res = this.connect.createStatement().executeUpdate(query);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return (res==1);
	}

	@Override
	public boolean update(Categorie obj) {
		int res = 0;
		try{
			String query = "UPDATE categorie SET "+
					"nom_categorie = '"+obj.getNom_categorie()+"', "+
					"description = '"+obj.getDescription()+"', "+
					"illustration_url = '"+obj.getIllustration_url()+"'"+
					" WHERE code_categorie ='"+obj.getCode_categorie()+"'";
			res = this.connect.createStatement().executeUpdate(query);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return (res==1);
	}

	@Override
	public Categorie find(String code_categorie) {
		Categorie categorie = new Categorie();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
			).executeQuery(
				"SELECT * FROM categorie WHERE code_categorie = '"+code_categorie+"'"	
			);
			if(result.first()){
				categorie.setCode_categorie(result.getString("code_categorie"));
				categorie.setNom_categorie(result.getString("nom_categorie"));
				categorie.setDescription(result.getString("description"));
				categorie.setIllustration_url(result.getString("illustration_url"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return categorie;
	}

}