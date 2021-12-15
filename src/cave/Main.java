package cave;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.lang.Math;

import exceptions.ExcesCapaciteException;
import vins.DomPerignon;
import vins.PerrierJouet;
import vins.Petrus;
import vins.Vin;

public class Main {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		//Notre cave à vin
		Cave cave = Cave.getInstance();
		Caisse c1 = new Caisse(5); //caisse qui contient 5 vins
		Caisse c2 = new Caisse(5);
		int budget = 100000;
		
		try {
			c1.ajouter(new PerrierJouet(Vin.getMinimumVolume() + 250, 1298, 1988));
			c1.ajouter(new DomPerignon(Vin.getMinimumVolume(), 4980, 1966));
			c2.ajouter(new Petrus(Vin.getMinimumVolume() + 100, 15000, 1865));
			
			cave.ajouterCaisse(c1);
			cave.ajouterCaisse(c2);
		} catch (ExcesCapaciteException e) {
			e.printStackTrace();
		}

		int nb_vins = cave.getNbVins();
		

// ------------------------------------------------------------------------------------------------------------

		
		System.out.println("   ===========^===========\n"
				+ "  /      CHAN & JAE's     \\\n"
				+ " |        CAVE A VIN       |\n"
				+ " |    _      ___      _    |\n"
				+ " |   |+|    |   |    |+|   |\n"
				+ " |    -     | - |     -    |\n"
				+ " |_________________________|\n"
				+ "[___________________________]");
		System.out.println("\nBienvenue dans la cave à vin de Jae&Chan's !\n");
		boolean carte = false;
		Vin v_rec = null;
		int entree = 0;
		do {
			System.out.println("Choisissez parmi les options suivantes :\n\n"
					+ "1 - Voir notre carte de vins\n"
					+ "2 - Obtenir une recommendation\n"
					+ "3 - Tester un verre\n"
					+ "4 - Acheter une bouteille de vin\n"
					+ "5 - Quitter et voir l'addition\n");
			System.out.print("Choisissez : ");
			
			//on demande l'entrée à l'utilisateur
			entree = scanner.nextInt();
			if (entree == 1) {
				System.out.println("\n\nBien-sur, voici nos vins special juste pour vous:\n\n");
				System.out.println("-------------------------------------------------------------");
				System.out.println(" 'Chateau'              'Region'     'Annee'      'Prix' ");
				System.out.println("-------------------------------------------------------------");
				System.out.println(cave.toString());
			} else if (entree == 2) {

				int entree_budget = 0;
				do {
					System.out.print("Est-que vous-pouvez nous dire le budget de votre experience dans notre cave-a-vin? : ");
					entree_budget = scanner.nextInt();
					System.out.println("Notre selection des vins, ils sont un peu plus cher que votre budget. Enlever plus de budget pour gouter notre vin!");
				} while (entree_budget < cave.getBudget()); {
					if (entree_budget > cave.getBudget()) {
						v_rec = cave.getRecommendation(entree_budget);
						System.out.println("\n\nPour vous, aujourd'hui, nous recommendons le meilleur vin du l'année " + v_rec.getAnnee());
						System.out.println("C'est le " + "<<" + v_rec.toStringRecommendation() +">>");
					}
				}
				//random recommendation selon le budget
			} else if (entree == 3) {
				if (v_rec != null) {
					System.out.println("Vous voulez essayer la recommendation?");
					System.out.print("C'etait le <<" + v_rec.toStringRecommendation() + ">> :");
				} else {
					System.out.println("Vous voulez essayer quel vin? voici notre carte du vin!");
					System.out.println("-------------------------------------------------------------");
					System.out.println(" 'Chateau'              'Region'     'Annee'      'Prix' ");
					System.out.println("-------------------------------------------------------------");
					System.out.println(cave.toString());
				}
				
				//Faire un commentaire sur le vin avec sa couleur
			} else if (entree == 4) {
				//vérifier le budget
			} else if (entree == 5) {
				//surprise de l'addition
			}
		} while (entree != 5); //5 est l'option par défaut de sortie
		scanner.close();
	}
}
