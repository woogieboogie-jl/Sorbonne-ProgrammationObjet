package vins;

import interfaces.Chardonnay;
import interfaces.PinotNoir;

public class DomPerignon extends VinBlanc implements Chardonnay, PinotNoir {

	public DomPerignon(float volume, float prix, int annee) {
		super(volume, "Dom Perignon", prix, "Champagne", annee);
	}

	@Override
	public Vin clone() {
		return new DomPerignon(super.volume, super.prix, super.annee);
	}

}
