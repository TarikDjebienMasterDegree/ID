package entrepot.dao.bdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import entrepot.bean.fait.Fait_ventes;

public class BddFait_ventesDAO extends BddDAO<Fait_ventes> {

	public BddFait_ventesDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Fait_ventes obj) {
		int res = 0;
		try{
			String query = 
					"INSERT INTO fait_ventes (code_fait_ventes, id_temps, code_client, ref_produit, code_vendeur) "+
					"VALUES ('"
							+obj.getCode_fait_ventes()+"', '"
							+obj.getId_temps()+", '"
							+obj.getCode_client()+"', '"
							+obj.getRef_produit()+"', '"
							+obj.getCode_vendeur()+"'"
							+")";
			res = this.connect.createStatement().executeUpdate(query);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return (res==1);
	}

	@Override
	public boolean delete(Fait_ventes obj) {
		int res = 0;
		try{
			String query = "DELETE FROM fait_ventes WHERE code_fait_ventes ='"+obj.getCode_fait_ventes()+"'";
			res = this.connect.createStatement().executeUpdate(query);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return (res==1);
	}

	@Override
	public boolean update(Fait_ventes obj) {
		int res = 0;
		try{
			String query = 
					"UPDATE fait_ventes SET " +
					"id_temps = "+obj.getId_temps()+", "+
					"code_client = '"+obj.getCode_client()+"', "+
					"ref_produit = '"+obj.getRef_produit()+"', "+
					"code_vendeur = '"+obj.getCode_vendeur()+"' "+
					" WHERE code_fait_ventes ='"+obj.getCode_fait_ventes()+"'";
			res = this.connect.createStatement().executeUpdate(query);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return (res==1);
	}

	@Override
	public Fait_ventes find(String code_fait_ventes) {
		Fait_ventes fait_ventes = new Fait_ventes();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
							"SELECT * FROM fait_ventes WHERE code_fait_ventes = '"+code_fait_ventes+"'"	
							);
			if(result.first()){
				fait_ventes.setCode_fait_ventes(result.getString("code_fait_ventes"));
				fait_ventes.setId_temps(result.getInt("id_temps"));
				fait_ventes.setCode_client(result.getString("code_client"));
				fait_ventes.setRef_produit(result.getString("ref_produit"));
				fait_ventes.setCode_vendeur(result.getString("code_vendeur"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return fait_ventes;
	}

}
