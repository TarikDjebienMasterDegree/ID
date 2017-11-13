package entrepot.dao.bdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import entrepot.bean.fait.Fait_achat;

public class BddFait_achatDAO extends BddDAO<Fait_achat> {

	public BddFait_achatDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Fait_achat obj) {
		int res = 0;
		try{
			String query = 
					"INSERT INTO fait_achat (code_fait_achat, ref_produit, id_temps, code_fournisseur) "+
					"VALUES ('"
							+obj.getCode_fait_achat()+"', '"
							+obj.getRef_produit()+"', "
							+obj.getId_temps()+", '"
							+obj.getCode_fournisseur()+"'"
							+")";
			res = this.connect.createStatement().executeUpdate(query);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return (res==1);
	}

	@Override
	public boolean delete(Fait_achat obj) {
		int res = 0;
		try{
			String query = "DELETE FROM fait_achat WHERE code_fait_achat ='"+obj.getCode_fait_achat()+"'";
			res = this.connect.createStatement().executeUpdate(query);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return (res==1);
	}

	@Override
	public boolean update(Fait_achat obj) {
		int res = 0;
		try{
			String query = 
					"UPDATE fait_achat SET " +
					"ref_produit = '"+obj.getRef_produit()+"', "+
					"id_temps = "+obj.getId_temps()+", "+
					"code_fournisseur = '"+obj.getCode_fournisseur()+"' "+
					" WHERE code_fait_achat ='"+obj.getCode_fait_achat()+"'";
			res = this.connect.createStatement().executeUpdate(query);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return (res==1);
	}

	@Override
	public Fait_achat find(String code_fait_achat) {
		Fait_achat fait_achat = new Fait_achat();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
							"SELECT * FROM fait_achat WHERE code_fait_achat = '"+code_fait_achat+"'"	
							);
			if(result.first()){
				fait_achat.setCode_fait_achat(result.getString("code_fait_achat"));
				fait_achat.setRef_produit(result.getString("ref_produit"));
				fait_achat.setId_temps(result.getInt("id_temps"));
				fait_achat.setCode_fournisseur(result.getString("code_fournisseur"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return fait_achat;
	}
}