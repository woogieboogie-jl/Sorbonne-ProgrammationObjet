package cave;

import java.util.Random;

import exceptions.ExcesCapaciteException;
import exceptions.StockFiniException;
import vins.Vin;

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
	
	public int getNbVins() {
		int nb = 0;
		for (int i = 0; i < nb_caisses; i++) {
			nb += caisses[i].getNbVins();
		}
		return nb;
	}
	
	//ajout
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
	
	//ajout
	public void afficherSelection() {
		Vin[] selection = this.getSelection();
		StringBuilder sb = new StringBuilder();
		for (int y = 0; y < 3; y++) {
			for (int i = 0; i < selection.length; i++) {
				String espace = "\t";
				if (selection[i].getNom().length() <= 6)
					espace += "\t";
				if (y==0)
					sb.append("| " + selection[i].getNom()+" ("+(i+1)+")" + espace);
				else if (y==1)
					sb.append("| Vient de " + selection[i].getRegion()+"\t");
				else if (y==2)
					sb.append("| Daté de " + selection[i].getAnnee()+"\t\t");
			}
			sb.append("\n");
		}
		System.err.println(sb);
	}
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
	
	public Vin trouverVin(Vin v) throws StockFiniException {
		for (Caisse c : caisses) {
			Vin vinTrouve = c.recupererVin(v);
			if (vinTrouve != null) {
				return vinTrouve;
			}
		}
		throw new StockFiniException();
	}
	
	public String toString() {
		String s = "";
		for (Caisse c : caisses) {
			if (c != null)
				s+=c.toString()+"\n";
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
}
