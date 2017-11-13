package entrepot.dao.bdd;

import java.sql.Connection;

import entrepot.dao.contrat.IDAO;
/**
 * 
 * @author tarik
 * En resume :
 * - Le pattern DAO vous permet de lier vos données (tables,XML,fichiers...) avec des objets Java.
 * - Interagir avec des bases de donnees en encapsulant l'acces a celle-ci permet de faciliter la migration vers une autre base en cas de besoin.
 * - Afin d'etre vraiment le plus souple possible, on peut laisser la creation de nos DAO a une factory codee par nos soins.
 * - Pour gerer differents types de DAO (BDD, XML, fichiers ...), on peut utiliser une factory qui se chargera de creer nos factory de DAO.
 * @param <T>
 */
public abstract class BddDAO<T> implements IDAO {
	
	protected Connection connect = null;
	
	public BddDAO(Connection conn){
		this.connect=conn;
	}
	
	/**
	 * Methode de creation
	 * @param obj
	 * @return boolean
	 */
	public abstract boolean create(T obj);
	
	/**
	 * Methode pour effacer
	 * @param obj
	 * @return boolean
	 */
	public abstract boolean delete(T obj);
	
	/**
	 * Methode de mise a jour
	 * @param obj
	 * @return boolean
	 */
	public abstract boolean update(T obj);
	
	/**
	 * Methode de recherche des informations
	 * @param obj
	 * @return boolean 
	 */
	public abstract T find(String id);
}
