package entrepot.bean.dimension;

import entrepot.bean.contrat.IDimension;

/**
 * @author  tarik
 */
public class Temps implements IDimension{
	

	private int id_temps;

	private int semaine;

	private int mois;

	private int trimestre;

	private int annee;
	
	public Temps(){}

	public void tempsMapper(String id_temps, String semaine, String mois, String trimestre, String annee){
		this.setId_temps(Integer.parseInt(id_temps));
		this.setSemaine(Integer.parseInt(semaine));
		this.setMois(Integer.parseInt(mois));
		this.setTrimestre(Integer.parseInt(trimestre));
		this.setAnnee(Integer.parseInt(annee));
	}
	
	public int getId_temps() {
		return id_temps;
	}

	public void setId_temps(int id_temps) {
		this.id_temps = id_temps;
	}

	public int getSemaine() {
		return semaine;
	}

	public void setSemaine(int semaine) {
		this.semaine = semaine;
	}

	public int getMois() {
		return mois;
	}

	public void setMois(int mois) {
		this.mois = mois;
	}

	public int getTrimestre() {
		return trimestre;
	}

	public void setTrimestre(int trimestre) {
		this.trimestre = trimestre;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}
	
	public boolean equals(Temps temps){
		return this.getId_temps()==temps.getId_temps();
	}
}