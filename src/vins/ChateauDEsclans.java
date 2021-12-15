package vins;

import interfaces.PinotNoir;

public class ChateauDEsclans extends VinRose implements PinotNoir{

	public ChateauDEsclans(float volume, float prix, int annee) {
		super(volume, "Chateau d'Esclans", prix, "Var", annee);
	}

	@Override
	public Vin clone() {
		return new ChateauDEsclans(super.volume, super.prix, super.annee);
	}

}
