package vins;

public abstract class VinBlanc extends Vin {
	//enleve couleur
	public	VinBlanc(float volume, String nom, float prix, String region, int annee) {
		super(volume, nom, prix, region, annee, "blanc");
	}
}
