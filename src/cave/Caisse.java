package cave;

import java.util.ArrayList;

import exceptions.ExcesCapaciteException;
import vins.Vin;

public class Caisse extends Conteneur {
	private ArrayList<Vin> tab;
	//on ne fait pas usage d'un tableau statique car ce serait trop contraignant pour enlever des vins avec les indices
	
	public Caisse(int capacite) {
		super(capacite);
		this.tab = new ArrayList<Vin>();
	}
	
	@Override
	public void ajouter(Vin v) throws ExcesCapaciteException {
		if (tab.size() >= super.capacite) {
			throw new ExcesCapaciteException();
		}
		tab.add(v);
	}
	
	/**
	 * @param v : vin passé en argument recherché dans la caisse
	 * @return vin : le vin trouvé ou null s'il n'a pas été trouvé dans la caisse
	 */
	//ajout commentaire
	public Vin recupererVin(Vin v) {
		for (Vin vin : tab) {
			if (vin.estEquivalent(v)) //deux vins peuvent etre d'une meme instance mais etre non équivalents
				return vin;
		}
		return null;
	}
	
	public ArrayList<Vin> getTousVins() {
		return this.tab;
	}
	
	public int getNbVins() {
		return tab.size();
	}

    /*
    * Retourne le vin dans la caisse ayant le prix le plus bas
    */
	public float getPrixMinVin() {
        float budget = 0;
        for (int i=0; i < getNbVins(); i++){
            if (i ==0) {
                budget = tab.get(i).getPrix();
            } else {
                if (tab.get(i).getPrix() < budget) {
                    budget = tab.get(i).getPrix();
                }
            }
        }
        return budget;
    }

    /*
    * Retourne une ArrayList de vins correspondants au budget passé en paramètre
    */
    public ArrayList<Vin> getVinsBudget(int budget) {
        ArrayList<Vin> vin_budgets = new ArrayList<Vin>();
        for (int i=0; i<tab.size(); i++) {
            if (tab.get(i).getPrix() < budget) {
                vin_budgets.add(tab.get(i));
            }
        }
        return vin_budgets;
    }
	
	@Override
	public void vider() {
		tab.clear(); //supprime tous les vins de tab
	}
	
	public String toString() {
		String s = "";
		for (Vin v : tab) {
			if (v != null)
				s += v.toString() + "\n";
		}
		return s.substring(0, s.length()-1);
	}
	
}
