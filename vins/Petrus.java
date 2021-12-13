package vins;

import interfaces.Merlot;

public class Petrus extends VinRouge implements Merlot {

	public Petrus(float volume, float prix, int annee) {
		super(volume, "Petrus", prix, "Pomerol", annee);
	}

	@Override
	public Vin clone() {
		return new Petrus(super.volume, super.prix, super.annee);
	}

}
