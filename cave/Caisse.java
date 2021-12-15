package cave;

import java.util.ArrayList;

import exceptions.ExcesCapaciteException;
import vins.Vin;

public class Caisse extends Conteneur {
	private ArrayList<Vin> tab; //changement
	//on ne fait pas usage d'un tableau statique car ce serait trop contraignant pour enlever des vins avec les indices
	
	public Caisse(int capacite) {
		super(capacite);
		this.tab = new ArrayList<Vin>();
	}
	
	@Override
	public void ajouter(Vin v) throws ExcesCapaciteException {
		if (tab.size() >= super.capacite) {
			throw new ExcesCapaciteException();
		}
		tab.add(v);
	}
	
	/**
	 * @param v : vin passé en argument recherché dans la caisse
	 * @return vin : le vin trouvé ou null s'il n'a pas été trouvé dans la caisse
	 */
	//ajout commentaire
	public Vin recupererVin(Vin v) {
		for (Vin vin : tab) {
			if (vin.estEquivalent(v)) //deux vins peuvent etre d'une meme instance mais etre non équivalents
				return vin;
		}
		return null;
	}
	
	public ArrayList<Vin> getTousVins() {
		return this.tab;
	}
	
	public int getNbVins() {
		return tab.size();
	}
	
	@Override
	public void vider() {
		tab.clear(); //supprime tous les vins de tab
	}
	
	public String toString() {
		String s = "";
		for (Vin v : tab) {
			if (v != null)
				s += v.toString() + "\n";
		}
		return s.substring(0, s.length()-1);
	}
	
}
