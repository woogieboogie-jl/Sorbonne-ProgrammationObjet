package vins;

public abstract class VinRose extends Vin {
	protected String couleur = "rose";
	public	VinRose(float volume, String nom, float prix, String region, int annee) {
		super(volume, nom, prix, region, annee);
	}
}
