package vins;

public abstract class VinBlanc extends Vin {
	protected String couleur = "blanc";
	public	VinBlanc(float volume, String nom, float prix, String region, int annee) {
		super(volume, nom, prix, region, annee);
	}
}
