package cave;

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
	
	public int getNbVins() {
		return nb_vins;
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
