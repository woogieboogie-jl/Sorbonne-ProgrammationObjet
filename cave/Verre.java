package cave;

import exceptions.PlusDeVolumeException;
import vins.Vin;

public class Verre {
	/**
	 * Le verre n'accepte qu'un volume de 100 mL, c'est la règle de service.
	 */
	private final float VOLUME = 100; //mL
	private float volume_restant;
	private Vin vin;
	
	public void ajouter(Vin vin) throws PlusDeVolumeException {
		if (vin.getVolume() >= VOLUME) {
			this.vin = vin;
			this.vin.prelever(VOLUME);
			this.volume_restant = VOLUME;
		} else {
			throw new PlusDeVolumeException();
		}
	}
	
	public boolean boire() {
		if (volume_restant >= 0) {
			int volume = (int) (Math.random() * 10 + 10); //on boit un volume entre 20 et 10 ml.
			this.volume_restant = Math.max(0, this.volume_restant - volume);
			System.out.println("(Vous prenez une gorgée pleine de saveur)\n");
			try {
				Thread.sleep(1000);
				System.out.println("Vous : Hmm c'est bon.\n");
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (volume_restant == 0) {
				System.out.println("Ah j'ai fini !");
				return true;
			}
			return false; //on retourne faux s'il reste du vin à consommer
		}
		return true; //on retourne vrai si l'on a plus de vin à boire
	}
	
	public Vin getVin() {
		return vin;
	}
	
	public float getPrix() {
		return (VOLUME * 2) * (vin.getPrix()/vin.getVolume());
	}
}
