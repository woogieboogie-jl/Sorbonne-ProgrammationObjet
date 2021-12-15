package cave;

import exceptions.ExcesCapaciteException;
import exceptions.PlusDeVolumeException;
import vins.Vin;

public class Tonneau extends Conteneur {
	private float volume;
	private Vin type;
	
	public Tonneau(int capacite, float volume, Vin type) {
		super(capacite);
		this.type = type;
	}

	@Override
	public void ajouter(Vin v) throws ExcesCapaciteException, PlusDeVolumeException {
		if (v.getVolume() + this.volume >= capacite) {
			throw new ExcesCapaciteException();
		}
		this.volume += v.getVolume();
		v.prelever(v.getVolume());
	}

	@Override
	public void vider() {
		
	}

}
