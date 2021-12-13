package cave;

import java.util.Scanner;

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
		
		
		System.out.println("   ==========^===========\n"
				+ "  /     CHAN & JAE's     \\\n"
				+ " |       CAVE A VIN       |\n"
				+ " |    _      __      _    |\n"
				+ " |   |+|    |  |    |+|   |\n"
				+ " |    -     |  |     -    |\n"
				+ " |________________________|\n"
				+ "[__________________________]");
		System.out.println("\nBienvenue dans la cave à vin de Jae&Chan's !\n");
		boolean carte = false;
		
		int entree = 0;
		do {
			System.out.println("Choisissez parmi les options suivantes :\n"
					+ "1 - Voir notre carte de vins\n"
					+ "2 - Obtenir une recommendation\n"
					+ "3 - Tester un verre\n"
					+ "4 - Acheter une bouteille de vin\n"
					+ "5 - Quitter et voir l'addition\n");
			System.out.print("Choisissez : ");
			
			//on demande l'entrée à l'utilisateur
			entree = scanner.nextInt();
			if (entree == 1) {
				System.out.println("Bien-sur voici nos vins :\n");
				System.out.println(cave.toString()); //ajouter les $$$
			} else if (entree == 2) {
				//random recommendation selon le budget
			} else if (entree == 3) {
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
