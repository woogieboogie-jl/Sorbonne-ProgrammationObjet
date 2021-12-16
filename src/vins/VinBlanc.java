package vins;

public abstract class VinBlanc extends Vin {
	public	VinBlanc(float volume, String nom, float prix, String region, int annee) {
		super(volume, nom, prix, region, annee, "blanc");
	}
}
