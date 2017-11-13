package entrepot.dao.bdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import entrepot.bean.dimension.Client;

public class BddClientDAO extends BddDAO<Client> {

	public BddClientDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Client obj) {
		int res = 0;
		try{
			String query = 
					"INSERT INTO client (code_client, societe, contact, fonction, adresse, ville, region, code_postal, pays, telephone, fax) "+
					"VALUES ('"
							+obj.getCode_client()+"', '"
							+obj.getSociete()+"', '"
							+obj.getContact()+"', '"
							+obj.getFonction()+"', '"
							+obj.getAdresse()+"', '"
							+obj.getVille()+"', '"
							+obj.getRegion()+"', '"
							+obj.getCode_postal()+"', '"
							+obj.getPays()+"', '"
							+obj.getTelephone()+"', '"
							+obj.getFax()+"'"
							+")";
			res = this.connect.createStatement().executeUpdate(query);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return (res==1);
	}

	@Override
	public boolean delete(Client obj) {
		int res = 0;
		try{
			String query = "DELETE FROM client WHERE code_client ='"+obj.getCode_client()+"'";
			res = this.connect.createStatement().executeUpdate(query);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return (res==1);
	}

	@Override
	public boolean update(Client obj) {
		int res = 0;
		try{
			String query = 
					"UPDATE client SET " +
					"societe = '"+obj.getSociete()+"', "+
					"contact = '"+obj.getContact()+"', "+
					"fonction = '"+obj.getFonction()+"', "+
					"adresse = '"+obj.getAdresse()+"', "+
					"ville = '"+obj.getVille()+"', "+
					"region = '"+obj.getRegion()+"', "+
					"code_postal = '"+obj.getCode_postal()+"', "+
					"pays = '"+obj.getPays()+"', "+
					"telephone = '"+obj.getTelephone()+"', "+
					"fax = '"+obj.getFax()+"' "+
					" WHERE code_client ='"+obj.getCode_client()+"'";
			res = this.connect.createStatement().executeUpdate(query);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return (res==1);
	}

	@Override
	public Client find(String code_client) {
		Client client = new Client();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
							"SELECT * FROM client WHERE code_client = '"+code_client+"'"	
							);
			if(result.first()){
				client.setCode_client(result.getString("code_client"));
				client.setSociete(result.getString("societe"));
				client.setContact(result.getString("contact"));
				client.setFonction(result.getString("fonction"));
				client.setAdresse(result.getString("adresse"));
				client.setVille(result.getString("ville"));
				client.setRegion(result.getString("region"));
				client.setCode_postal(result.getString("code_postal"));
				client.setPays(result.getString("pays"));
				client.setTelephone(result.getString("telephone"));
				client.setFax(result.getString("fax"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return client;
	}

}
