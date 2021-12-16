package cave;

import java.rmi.UnexpectedException;
import java.util.Scanner;
import java.util.ArrayList;

import exceptions.ExcesCapaciteException;
import vins.ChateauDEsclans;
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
			c1.ajouter(new DomPerignon(Vin.getMinimumVolume(), 6080, 1930));
			c1.ajouter(new Petrus(Vin.getMinimumVolume(), 2909, 1955));
			c2.ajouter(new Petrus(Vin.getMinimumVolume() + 100, 15000, 1865));
			c2.ajouter(new PerrierJouet(Vin.getMinimumVolume() + 100, 1500, 1994));
			c2.ajouter(new PerrierJouet(Vin.getMinimumVolume() + 50, 3000, 1984));
			c2.ajouter(new ChateauDEsclans(Vin.getMinimumVolume() + 50, 30000, 1974));
			
			cave.ajouterCaisse(c1);
			cave.ajouterCaisse(c2);
		} catch (ExcesCapaciteException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("   ==========^===========\n"
				+ "  /     JAE & CHAN's     \\\n"
				+ " |       CAVE A VIN       |\n"
				+ " |    _      __      _    |\n"
				+ " |   |+|    |  |    |+|   |\n"
				+ " |    -     |  |     -    |\n"
				+ " |________________________|\n"
				+ "[__________________________]");
		System.out.println("\nBienvenue dans la cave à vin de Jae&Chan's !");
		//ajout
		System.out.println("Auriez-vous l'amabilité de nous communiquer votre identité ? (0:Homme/1:Femme) :");
		int option = -1;
		String nom = "";
		while (option == -1) {
			try {
				int entree = scanner.nextInt();
				if (entree==0) {
					nom="Monsieur";
					option=0;
				} else if (entree==1) {
					nom="Madame";
					option=1;
				} else {
					System.out.println("Toutes nos excuses, nous n'avons pas compris votre identité, quelle est-elle ? (0:Homme/1:Femme) :");
				}
			} catch (Exception e) {
				//on est dans le cas où l'entrée n'est pas un entier
				System.out.println("Toutes nos excuses, nous n'avons pas compris votre identité, quelle est-elle ? (0:Homme/1:Femme) :");
				scanner.next(); //remet le scanner à 0 pour effacer la mauvaise entrée précédente
			}
		}
		
		//ajout
		int nb_vins = cave.getNbVins();
		System.out.println("Bien " + nom + ", nous avons actuellement " + nb_vins + " bouteilles de vin à notre disposition.\n");
		//boolean carte = false;
		System.out.println("(Vous avez un budget de "+budget+" euros)\n");
		
		int entree = 0;
		do {
			try {
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
					try {
						System.out.println("Nous considerons les cépages les plus importantes, et nous avons trois");
						while (true) {
							System.out.println("1: Chardonnay\n2: Merlot\n3: Pinot Noir");
							System.out.print("\nChoisisez-vous un cépage de votre choix:");
							int entree_cepage = scanner.nextInt();
							
							if (entree_cepage > 3 || entree_cepage < 1) {
								System.out.println("Veuillez-nous pardonner " + nom + ", que désirez-vous ?");	
							} else {
								ArrayList<Vin> vin_liste = cave.getSelectionCepage(entree_cepage-1);
								System.out.println(cave.afficherCarte(vin_liste));
								System.out.print("\n\n\n");
								break;
							}
						}
					} catch (Exception e) {
						System.out.println("Veuillez-nous pardonner " + nom + ", que désirez-vous ?");
						e.printStackTrace();
						scanner.next(); //reinitialise le scanner
					}

				} else if (entree == 2) {
					//random recommendation selon le budget
				} else if (entree == 3) {
					//Faire un commentaire sur le vin avec sa couleur
					System.out.println("Très bien, quel verre de vin désire " + nom + " ?  (entrez le numéro du vin ou 0 pour sortir)");
					//vérifier le budget
					System.out.println();
					
					Vin[] selection = cave.getSelection();
					while (true) {
						//affichage vins
						cave.afficherSelection();
						System.out.println("\nSélection du vin : ");
						int entree_bis = 0;
						try {
							entree_bis = scanner.nextInt();
							if (entree_bis == 0) break; //on sort de la boucle si on entre 0
							
							if (entree_bis > cave.getNbVins()+1 || entree_bis < 0) {
								System.out.println("Veuillez-nous pardonner " + nom + ", mais cela ne correspond pas à un de nos vins.");
							} else {
								Vin vin = selection[entree-1];
								//budget -=...
								//ajouter bouteille parmi les vins...
								System.out.println(vin + ". Ah oui ce fameux vin !\n");
								System.out.println(Cave.genererMessage(vin.getCouleur()));
								Verre verre = new Verre();
								verre.ajouter(vin);
								budget -= verre.getPrix(); //le prix du verre est déterminé par le vin versé
								System.out.println("Voici votre verre de vin, savourez-le.");
								verre.boire();
								break;
							}
						} catch (Exception e) {
							System.out.println("Veuillez-nous pardonner " + nom + ", ce vin n'est pas disponible chez-nous, que désirez-vous ?");
							scanner.next();
						}
					}
				} else if (entree == 4) {
					//vérifier le budget
					System.out.println("Très bien, quel vin désire " + nom + " ? (entrez le numéro du vin ou 0 pour sortir)");
					System.out.println();
					
					Vin[] selection = cave.getSelection();
					int entree_bis = 0;
					while (true) {
						//affichage vins
						cave.afficherSelection();
						System.out.println("\nSélection du vin : ");
						try {
							entree_bis = scanner.nextInt();
							if (entree_bis == 0) break; //on sort de la boucle si on entre 0
							
							if (entree_bis > cave.getNbVins()+1 || entree_bis < 0) {
								System.out.println("Veuillez-nous pardonner " + nom + ", mais cela ne correspond pas à un de nos vins.");
							} else {
								Vin vin = selection[entree_bis-1];
								//budget -=...
								//ajouter bouteille parmi les vins...
								System.out.println(vin + ". Excellent choix !\n");
								break;
							}
						} catch (Exception e) {
							System.out.println("Veuillez-nous pardonner " + nom + ", ce vin n'est pas disponible chez-nous, que désirez-vous ?");
							scanner.next();
						}
					}
				} else if (entree == 5) {
					//surprise de l'addition
					System.out.println("(Vous regardez discrètement votre porte-monnaie)\n(Il vous reste " + budget + " euros)");
					System.out.println("Nous avons été honorés de votre présence " + nom + ", nous espérons avoir l'occasion de vous revoir.");
					break;
				}
			} catch (Exception e) {
				//on est dans le cas similaire à en haut où l'entrée n'est pas un entier
				System.out.println("Veuillez-nous pardonner " + nom + ", que désirez-vous ?");
				scanner.next(); //reinitialise le scanner
			}
		} while (entree != 5); //5 est l'option par défaut de sortie
		scanner.close();
	}
}
