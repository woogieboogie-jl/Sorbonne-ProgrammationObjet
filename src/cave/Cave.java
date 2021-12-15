package cave;

import java.util.ArrayList;

import exceptions.ExcesCapaciteException;
import exceptions.StockFiniException;
import vins.Vin;

public class Cave {
	private Caisse[] caisses;
	private int nb_caisses;
	private static final Cave INSTANCE = new Cave(5); //On a seulement une seule cave à vin avec 5 caisses
	
	private Cave(int nb_caisses) {
		this.nb_caisses = 0;
		this.caisses = new Caisse[nb_caisses];
	}
	
	public static Cave getInstance() {
		return INSTANCE;
		}
	
	public void ajouterCaisse(Caisse c) throws ExcesCapaciteException {
		if (nb_caisses >= caisses.length) {
			throw new ExcesCapaciteException();
		}
		caisses[nb_caisses] = c;
		nb_caisses++;
	}
	
	public Vin trouverVin(Vin v) throws StockFiniException {
		for (Caisse c : caisses) {
			Vin vinTrouve = c.recupererVin(v);
			if (vinTrouve != null) {
				return vinTrouve;
			}
		}
		throw new StockFiniException();
	}
	
	public String toString() {
		String s = "";
		for (Caisse c : caisses) {
			if (c != null)
				s+=c.toString()+"\n";
		}
		return s;
	}

	public int getNbVins() {
		int nb_vins = 0;
		for (int i=0; i<nb_caisses; i++) {
			nb_vins += caisses[i].getNbVins();
		}
		return nb_vins;
	}
	
	/**
	 * 
	 * @param c : caisse à enlever
	 * @return true si la caisse a été enlevée false sinon
	 */
	public boolean enleverCaisse(Caisse c) {
		if (nb_caisses <= 0) {
			return false;
		}
		for (int i = 0; i < nb_caisses; i++) {
			if (caisses[i] == c) {
				caisses[i] = null;
				nb_caisses--;
				return true;
			}
		}
		return false;
	}

	public Vin getRecommendation(int budget) {
		ArrayList<Vin> vins_budget_cave = new ArrayList<Vin>();
		for (int i=0; i<nb_caisses; i++){
			ArrayList<Vin> vins_budget_caisse = caisses[i].getVinsBudget(budget);
			vins_budget_cave.addAll(vins_budget_caisse);
		}
		int recommendation_numb = (int) (Math.random() * vins_budget_cave.size());
		return vins_budget_cave.get(recommendation_numb);
	}

	public float getBudget() {
		float budget = 0;
		for (int i=0; i<nb_caisses; i++) {
			if (i==0) {
				budget = caisses[i].getBudget();
			} else {
				if (caisses[i].getBudget() < budget) {
					budget = caisses[i].getBudget(); 
				}
			}
		}
		return budget;
	}


}
