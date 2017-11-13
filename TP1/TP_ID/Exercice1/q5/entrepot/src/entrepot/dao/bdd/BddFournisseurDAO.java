package entrepot.dao.bdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import entrepot.bean.dimension.Fournisseur;

public class BddFournisseurDAO extends BddDAO<Fournisseur> {

	public BddFournisseurDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Fournisseur obj) {
		int res = 0;
		try{
			String query = 
					"INSERT INTO fournisseur (code_fournisseur) "+
					"VALUES ('"+obj.getCode_fournisseur()+"')";
			res = this.connect.createStatement().executeUpdate(query);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return (res==1);
	}

	@Override
	public boolean delete(Fournisseur obj) {
		int res = 0;
		try{
			String query = "DELETE FROM fournisseur WHERE code_fournisseur ='"+obj.getCode_fournisseur()+"'";
			res = this.connect.createStatement().executeUpdate(query);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return (res==1);
	}

	@Override
	public boolean update(Fournisseur obj) {
		int res = 0;
		try{
			String query = 
					"UPDATE fournisseur SET " +
					"code_fournisseur = '"+obj.getCode_fournisseur()+"' "+
					" WHERE code_fournisseur ='"+obj.getCode_fournisseur()+"'";
			res = this.connect.createStatement().executeUpdate(query);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return (res==1);
	}

	@Override
	public Fournisseur find(String code_fournisseur) {
		Fournisseur fournisseur = new Fournisseur();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
							"SELECT * FROM fournisseur WHERE code_fournisseur = '"+code_fournisseur+"'"	
							);
			if(result.first()){
				fournisseur.setCode_fournisseur(result.getString("code_fournisseur"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return fournisseur;
	}

}
