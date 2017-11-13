package entrepot.dao.bdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import entrepot.bean.dimension.Vendeur;

public class BddVendeurDAO extends BddDAO<Vendeur> {

	public BddVendeurDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Vendeur obj) {
		int res = 0;
		try{
			String query = 
					"INSERT INTO vendeur (code_vendeur) "+
					"VALUES ('"+obj.getCode_vendeur()+"')";
			res = this.connect.createStatement().executeUpdate(query);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return (res==1);
	}

	@Override
	public boolean delete(Vendeur obj) {
		int res = 0;
		try{
			String query = "DELETE FROM vendeur WHERE code_vendeur ='"+obj.getCode_vendeur()+"'";
			res = this.connect.createStatement().executeUpdate(query);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return (res==1);
	}

	@Override
	public boolean update(Vendeur obj) {
		int res = 0;
		try{
			String query = 
					"UPDATE vendeur SET " +
					"code_vendeur = '"+obj.getCode_vendeur()+"' "+
					" WHERE code_vendeur ='"+obj.getCode_vendeur()+"'";
			res = this.connect.createStatement().executeUpdate(query);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return (res==1);
	}

	@Override
	public Vendeur find(String id) {
		Vendeur vendeur = new Vendeur();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
							"SELECT * FROM vendeur WHERE code_vendeur = '"+id+"'"	
							);
			if(result.first()){
				vendeur.setCode_vendeur(result.getString("code_vendeur"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return vendeur;
	}

}
