package cave;

import java.util.ArrayList;

import exceptions.ExcesCapaciteException;
import vins.Vin;

public class Caisse extends Conteneur {
	private Vin[] tab;
	private int nb_vins;
	
	public Caisse(int capacite) {
		super(capacite);
		this.tab=new Vin[super.capacite];
		this.nb_vins=0;
	}
	
	@Override
	public void ajouter(Vin v) throws ExcesCapaciteException {
		if (nb_vins >= super.capacite) {
			throw new ExcesCapaciteException();
		}
		tab[nb_vins]=v;
		nb_vins++;
	}
	
	/**
	 * @param v : vin passé en argument recherché dans la caisse
	 * @return vin : le vin trouvé ou null s'il n'a pas été trouvé dans la caisse
	 */
	public Vin recupererVin(Vin v) {
		for (Vin vin : tab) {
			if (vin.estVin(v))
				return vin;
		}
		return null;
	}

	public Vin recupererVin(int index) {
		for (int i=0; i < this.getNbVins(); i++) {
			if ( i==index-1 ){
				return tab[i];
			}
		}
		return null;
	}
	
	public int getNbVins() {
		return nb_vins;
	}

	public ArrayList<Vin> getVinsBudget(int budget) {
		ArrayList<Vin> vin_budgets = new ArrayList<Vin>();
		for (int i=0; i<nb_vins; i++) {
			if (tab[i].getPrix() < budget) {
				vin_budgets.add(tab[i]);
			}
		}
		return vin_budgets;
	}


	public float getBudget() {
		float budget = 0;
		for (int i=0; i < getNbVins(); i++){
			if (i ==0) {
				budget = tab[i].getPrix();
			} else {
				if (tab[i].getPrix() < budget) {
					budget = tab[i].getPrix();
				}
			}
		}
		return budget;
	}
	
	@Override
	public void vider() {
		this.nb_vins=0;
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
