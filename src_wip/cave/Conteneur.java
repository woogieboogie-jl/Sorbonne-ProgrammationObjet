package cave;

import exceptions.ExcesCapaciteException;
import exceptions.PlusDeVolumeException;
import vins.Vin;

public abstract class Conteneur {
	protected int capacite;
	
	public Conteneur(int capacite) {
		this.capacite=capacite;
	}
	
	//méthodes d'action
	public abstract void ajouter(Vin v) throws ExcesCapaciteException, PlusDeVolumeException;
	public abstract void vider();
	
	//accesseurs
	public int getCapacite() {
		return capacite;
	}
}
