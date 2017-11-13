package entrepot.dao.bdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import entrepot.bean.dimension.Temps;

public class BddTempsDAO extends BddDAO<Temps> {

	public BddTempsDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Temps obj) {
		int res = 0;
		try{
			String query = 
					"INSERT INTO temps (id_temps, semaine, mois, trimestre, annee) "+
					"VALUES ("
							+obj.getId_temps()+", "
							+obj.getSemaine()+", "
							+obj.getMois()+", "
							+obj.getTrimestre()+", "
							+obj.getAnnee()
							+")";
			res = this.connect.createStatement().executeUpdate(query);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return (res==1);
	}

	@Override
	public boolean delete(Temps obj) {
		int res = 0;
		try{
			String query = "DELETE FROM temps WHERE id_temps ="+obj.getId_temps();
			res = this.connect.createStatement().executeUpdate(query);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return (res==1);
	}

	@Override
	public boolean update(Temps obj) {
		int res = 0;
		try{
			String query = 
					"UPDATE temps SET " +
					"semaine = "+obj.getSemaine()+", "+
					"mois = "+obj.getMois()+", "+
					"trimestre = "+obj.getTrimestre()+", "+
					"annee = "+obj.getAnnee()+" "+
					" WHERE id_temps ="+obj.getId_temps();
			res = this.connect.createStatement().executeUpdate(query);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return (res==1);
	}

	@Override
	public Temps find(String id_temps) {
		Temps temps = new Temps();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
					).executeQuery(
							"SELECT * FROM client WHERE code_client = "+id_temps
							);
			if(result.first()){
				temps.setId_temps(result.getInt("id_temps"));
				temps.setSemaine(result.getInt("semaine"));
				temps.setMois(result.getInt("mois"));
				temps.setTrimestre(result.getInt("trimestre"));
				temps.setAnnee(result.getInt("annee"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return temps;
	}

}
