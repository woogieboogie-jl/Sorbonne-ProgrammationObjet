package vins;

import interfaces.Chardonnay;
import interfaces.Petillant;
import interfaces.PinotNoir;

public class PerrierJouet extends VinBlanc implements Chardonnay, PinotNoir, Petillant {

	public PerrierJouet(float volume, float prix, int annee) {
		super(volume, "Perrier Jouet", prix, "Epemay", annee);
	}

	@Override
	public Vin clone() {
		return new PerrierJouet(super.volume, super.prix, super.annee);
	}
	
	public void champagne() { System.out.println("CHAMPAGNE!!");}
}
