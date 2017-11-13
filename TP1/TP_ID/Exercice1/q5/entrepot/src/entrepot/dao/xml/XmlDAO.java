package entrepot.dao.xml;

import entrepot.dao.contrat.IDAO;

public abstract class XmlDAO<T> implements IDAO{
	
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
	public abstract T find(int id);

}
