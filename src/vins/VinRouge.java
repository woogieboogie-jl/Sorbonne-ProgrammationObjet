package vins;

public abstract class VinRouge extends Vin {
	protected String couleur = "rouge";
	public	VinRouge(float volume, String nom, float prix, String region, int annee) {
		super(volume, nom, prix, region, annee);
	}
}
