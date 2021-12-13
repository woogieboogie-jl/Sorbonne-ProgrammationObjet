package vins;

import exceptions.PlusDeVolumeException;

public abstract class Vin {
	/**
	 * La classe Vin est confondue pour plus de simplicité avec la bouteille de Vin en elle-meme.
	 * Notre cave à vin ne souhaite pas offrir d'autres vins que ceux qu'elle propose.
	 */
	protected float volume;
	protected float prix;
	protected String region;
	protected int annee;
	protected String nom;
	private static final float MINIMUM_VOLUME = 750;
	
	protected Vin(float volume, String nom, float prix, String region, int annee) {
		this.volume=volume;
		this.prix=prix;
		this.nom=nom;
		this.region=region;
		this.annee=annee;
	}
	
	//méthodes d'action
	/**
	 * Prelever est une méthode de classe qui prélève un certain volume du vin,
	 * elle est susceptible de lever une exception PlusDeVolumeException si le vin ne contient plus assez de volume.
	 */
	
	public void prelever(float volume) throws PlusDeVolumeException {
		if (this.volume - volume >= 0) {
			this.volume -= volume;	
		} else {
			throw new PlusDeVolumeException();
		}
	}
	
	
	public boolean estVin(Vin v) {
		return v.getAnnee() == this.annee && v.getPrix() == this.getPrix() && v.getVolume() == this.getVolume();
	}
	
	public abstract Vin clone();
	
	//accesseurs
	public String getNom() { return this.nom; }
	
	public float getVolume() {
		return this.volume;
	}
	
	public static float getMinimumVolume() {
		return MINIMUM_VOLUME;
	}
	
	public float getPrix() {
		return this.prix;
	}
	public int getAnnee() {
		return this.annee;
	}
	
	public String toString() { //on ne dit pas le prix c'est une surprise
		return "- " + this.nom + " " + this.region + " " + this.annee;
	}
	
}
