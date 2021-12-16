package cave;

import java.util.ArrayList;
import java.util.Random;

import exceptions.ExcesCapaciteException;
import exceptions.StockFiniException;
import vins.Vin;

import interfaces.Merlot;
import interfaces.Chardonnay;
import interfaces.PinotNoir;


public class Cave {
	private Caisse[] caisses;
	private int nb_caisses;
	private static final Cave INSTANCE = new Cave(5); //On a seulement une seule cave à vin avec 5 caisses
	
	private Cave(int nb_caisses) {
		this.nb_caisses = 0;
		this.caisses = new Caisse[nb_caisses];
	}
	
	public static Cave getInstance() {
		return INSTANCE;
	}
	
	public void ajouterCaisse(Caisse c) throws ExcesCapaciteException {
		if (nb_caisses >= caisses.length) {
			throw new ExcesCapaciteException();
		}
		caisses[nb_caisses] = c;
		nb_caisses++;
	}
	
	/*
	* Retourne un message généré aléatoirement qui fait un commentaire avec la couleur du vin
	*/
	public static String genererMessage(String couleur) {
		Random r = new Random();
		float probabilite = r.nextFloat();
		if (probabilite <= 0.25)
			return "Un beau et scintillant vin " + couleur + ".";
		else if (probabilite <= 0.5 && probabilite >= 0.25)
			return "Il vous fera découvrir un monde " + couleur +" et pur.";
		else if (probabilite >= 0.5 && probabilite <= 0.75)
			return "Classique, bon, ce vin vous fera voir tout "+couleur+".";
		else if (probabilite >= 0.75 && probabilite <= 0.9)
			return "Ce vin "+couleur+" vous fera renaître de vos cendres.";
		return "Inspirez un coup et détendez devant cette océan "+couleur+" que vous offre cette merveille.";
	}

	public int getNbVins() {
		int nb = 0;
		for (int i = 0; i < nb_caisses; i++) {
			nb += caisses[i].getNbVins();
		}
		return nb;
	}

    /*
    * Retourne le vin le moins cher parmi les caisses de vins de la cave
    */
	public float getPrixVinMinimum() {
        float budget = 0;
        for (int i=0; i<nb_caisses; i++) {
            if (i==0) {
                budget = caisses[i].getPrixMinVin();
            } else {
                if (caisses[i].getPrixMinVin() < budget) {
                    budget = caisses[i].getPrixMinVin();
                }
            }
        }
        return budget;
    }
	
	/*
	* Retourne un vin aléatoire tel que son prix soit dans le budget passé en argument
	*/
	public Vin getRecommendation(int budget) {
        ArrayList<Vin> vins_budget_cave = new ArrayList<Vin>();
        for (int i=0; i<nb_caisses; i++) {
            ArrayList<Vin> vins_budget_caisse = caisses[i].getVinsBudget(budget);
            vins_budget_cave.addAll(vins_budget_caisse);
        }
        int recommendation_numb = (int) (Math.random() * vins_budget_cave.size());
        return vins_budget_cave.get(recommendation_numb);
    }

	/*
	* Affiche tous les vins selon un format spécifique
	*/
	public void afficherSelection() {
		Vin[] selection = this.getSelection();
		String sb = "";
        	sb += "\n--------------------------------------------------------------------";
        	sb += "\n 'Château'              'Région'     'Année'      'Prix'   'Numero'";
        	sb += "\n--------------------------------------------------------------------";
		for (int i=0; i < selection.length; i++) {
			sb += "\n" + selection[i].affichageCarte() + "       (" + (i+1) +") " ;		
		}
		System.err.println(sb);
	}

	/*
	* Retourne un tableau de tous les vins de la cave
	*/
	public Vin[] getSelection() {
		Vin[] liste = new Vin[this.getNbVins()];
		int i = 0;
		for (int k = 0; k < nb_caisses; k++) {
			Caisse c = caisses[k];
			for (Vin v : c.getTousVins()) {
				liste[i] = v;
				i++;
			}
		}
		return liste;
	}

    /*
    * Retourne une tableau d'ArrayList de vins triés selon leur cépage
    */
	public ArrayList<Vin> getSelectionCepage(int index) {
		Vin[] liste = this.getSelection();
		System.out.println(liste.length);
		ArrayList<Vin>[] tabcepage = new ArrayList[3];
		for (int k=0; k<tabcepage.length; k++) {
			tabcepage[k] = new ArrayList<Vin>();
		}

		for (int i=0; i<liste.length; i++) {
			Vin vin = liste[i];
			if (vin instanceof Chardonnay) {
				tabcepage[0].add(vin);
			} else if (vin instanceof Merlot) {
				tabcepage[1].add(vin);
			} else if (vin instanceof PinotNoir) {
				tabcepage[2].add(vin);
			}
		}

		return tabcepage[index];
	}
	
	/*
	* Trouve le vin parmi les caisses de la cave
	*/
	public Vin trouverVin(Vin v) throws StockFiniException {
		for (Caisse c : caisses) {
			Vin vinTrouve = c.recupererVin(v);
			if (vinTrouve != null) {
				return vinTrouve;
			}
		}
		throw new StockFiniException();
	}

	/*
	* Affichage pour la carte de vins utilisée par la fonction affichageCarte de la classe Vin
	*/
    public String afficherCarte(ArrayList<Vin> vin_liste) {
        String s = "\n\nBien-sûr, voici nos vins speciaux ramenés juste pour vous : \n\n";
        s += "\n-------------------------------------------------------------";
        s += "\n 'Château'              'Région'     'Année'      'Prix' ";
        s += "\n-------------------------------------------------------------";
        for (int i=0; i<vin_liste.size(); i++) {
            s += "\n" + vin_liste.get(i).affichageCarte();
        }
        return s;
    }
	
	/**
	 * 
	 * @param c : caisse à enlever
	 * @return true si la caisse a été enlevée false sinon
	 */
	public boolean enleverCaisse(Caisse c) {
		if (nb_caisses <= 0) {
			return false;
		}
		for (int i = 0; i < nb_caisses; i++) {
			if (caisses[i] == c) {
				caisses[i] = null;
				nb_caisses--;
				return true;
			}
		}
		return false;
	}


	public String toString() {
		String s = "";
		for (Caisse c : caisses) {
			if (c != null)
				s+=c.toString()+"\n";
		}
		return s;
	}

}
