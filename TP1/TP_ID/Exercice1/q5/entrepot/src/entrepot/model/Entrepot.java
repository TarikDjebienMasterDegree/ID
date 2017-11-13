package entrepot.model;

import java.util.HashSet;
import java.util.Set;

import entrepot.bean.contrat.IDimension;
import entrepot.bean.contrat.IFait;

/**
 * @author  tarik
 */
public class Entrepot {
	
	private Set<IDimension> lesDimensions;

	private Set<IFait> lesFaits;
	
	public Entrepot(){
		this.lesDimensions=new HashSet<IDimension>();
		this.lesFaits=new HashSet<IFait>();
	}

	public Set<IDimension> getLesDimensions() {
		return lesDimensions;
	}

	public void setLesDimensions(Set<IDimension> lesDimensions) {
		this.lesDimensions = lesDimensions;
	}

	public Set<IFait> getLesFaits() {
		return lesFaits;
	}

	public void setLesFaits(Set<IFait> lesFaits) {
		this.lesFaits = lesFaits;
	}
	
	public void addDimension(IDimension dimension){
		if(!lesDimensions.contains(dimension)){
			lesDimensions.add(dimension);
		}
	}
	
	public void removeDimension(IDimension dimension){
		this.lesDimensions.remove(dimension);
	}
	
	public void addFait(IFait fait){
		if(!lesFaits.contains(fait)){
			lesFaits.add(fait);
		}
	}
	
	public void removeFait(IFait fait){
		this.lesFaits.remove(fait);
	}
}