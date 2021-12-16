package vins;

import exceptions.PlusDeVolumeException;
import interfaces.Merlot;
import interfaces.PinotNoir;

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
	protected final String couleur;
	private static final float MINIMUM_VOLUME = 750;
	
	protected Vin(float volume, String nom, float prix, String region, int annee, String couleur) {
		this.volume=volume;
		this.prix=prix;
		this.nom=nom;
		this.region=region;
		this.annee=annee;
		this.couleur=couleur;
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
	
	 /**
	  * @param v : vin dont on cherche l'équivalent
	  * @return true si on trouve un vin équivalent c'est-à-dire que ce sont les memes vins à l'exception du volume et le prix
	  */
	public boolean estEquivalent(Vin v) {
		return v.getNom().equals(v.getNom()) && v.getAnnee() == this.getAnnee() && v.getRegion().equals(v.getRegion());
	}
	
	public abstract Vin clone();

	//définisseurs
	public void setVolume(float volume) {
        this.volume = volume;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }
	
	//accesseurs
	public String getNom() { return this.nom; }
	
	public float getVolume() {
		return this.volume;
	}
	
	public String getCouleur() {
		return this.couleur;
	}
	
	public static float getMinimumVolume() {
		return MINIMUM_VOLUME;
	}
	
	public float getPrix() {
		return this.prix;
	}
	
	/*
	* Retourne l'estimation en symboles ($) du prix du vin
	*/
	public String getEstimationSymbole() {
		prix = this.getPrix();
		if ( prix < 1000 ) { return "$  "; }
		else if ( prix < 10000 ) {return "$$ "; }
		else { return "$$$"; }
	}

	
	public int getAnnee() {
		return this.annee;
	}
	
	public String getRegion() {
		return this.region;
	}

    //Affichage du vin au format spécial pour la carte
    public String affichageCarte() {
        return "- " + String.format("%-20s", this.nom) + " | " + String.format("%-10s", this.region) + " | " + String.format("%-6s", this.annee) + "       " + this.getEstimationSymbole();
    }

	public String toString() {
        return this.nom + " de " + this.region + " daté de " + this.annee;
    }
}
