package cave;

import exceptions.PlusDeVolumeException;
import interfaces.Petillant;
import vins.Vin;

public class Verre extends Conteneur {
	/**
	 * Le verre n'accepte qu'un volume de 100 mL, c'est la règle de service.
	 */
	private static final float VOLUME = 100; //mL VOLUME doit etre static pour pouvoir l'utiliser dans le constructeur (ne dépend pas des instances)
	private float volume_restant;
	private Vin vin;
	
	public Verre() {
		super(VOLUME);
	}
	
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
		if (volume_restant > 0) {
			int volume = (int) (Math.random() * 20 + 40); //on boit un volume entre 20 et 10 ml.
			this.volume_restant = Math.max(0, this.volume_restant - volume);

            if (this.vin instanceof Petillant)
                System.out.println("(Le verre est bien mousseu)");

			System.out.println("(Vous prenez une gorgée de " + this.getVin().getNom() + " pleine de saveur)\n");
			try {
				Thread.sleep(1000); //attend une seconde
				System.out.println("Vous : Hmm c'est bon.\n");
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				System.out.print(e.getMessage());
			}
			if (volume_restant == 0) {
				System.out.println("Vous : Ah j'ai fini !");
				return false;
			}
			return true; //on retourne vrai s'il reste du vin à consommer
		}
		return false; //on retourne faux si l'on a plus de vin à boire
	}
	
	public float getVolume() {
		return volume_restant;
	}
	
	public Vin getVin() {
		return vin;
	}
	
	/*
	* Retourne le prix du verre selon une formule de proportionnalité en relation avec le prix du vin
	*/
	public float getPrix() {
		return (VOLUME * 2) * (vin.getPrix()/vin.getVolume());
	}

	@Override
	public void vider() {
		this.volume_restant = 0;
	}
}
