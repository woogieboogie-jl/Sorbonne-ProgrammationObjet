package cave;

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
}
