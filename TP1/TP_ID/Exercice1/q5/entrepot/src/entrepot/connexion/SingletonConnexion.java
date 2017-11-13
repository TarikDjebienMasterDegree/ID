package entrepot.connexion;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe de connexion pour limiter les ressources les ressouces sont associer au demarrage de la JVM.
 * @author   tarik
 */
public class SingletonConnexion {
	
	private static String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static String user = "Entrepot";
	private static String password = "Entrepot";
	private static Connection connect;
	
	private static SingletonConnexion connectSGBD = new SingletonConnexion();
	
	private SingletonConnexion(){
		System.out.println("Initialisation de la connexion Base de donnée");
		if(connect==null){
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				System.out.println("DRIVER SGBD OK ! ");
			} catch (ClassNotFoundException e1) {
				System.out.println("Probleme de Driver");
				e1.printStackTrace();
			}
			try {
				connect = DriverManager.getConnection(url, user, password);
				System.out.println("Connexion JDBC OK !");
			} catch (SQLException e) {
				System.out.println("Probleme de connexion JDBC");
				e.printStackTrace();
			}
		}
	}
	private Connection getConnection(){
		return SingletonConnexion.connect;
	}
	public static Connection getInstance(){
		return connectSGBD.getConnection();
	}

}