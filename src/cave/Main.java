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
		
		//paramètres utilisateur
		int budget = 10000;
		Verre verre = new Verre();
		
		try {
			c1.ajouter(new PerrierJouet(Vin.getMinimumVolume() + 250, 1298, 1988));
			c1.ajouter(new DomPerignon(Vin.getMinimumVolume(), 4980, 1966));
			c1.ajouter(new DomPerignon(Vin.getMinimumVolume(), 6080, 1930));
			Petrus p = new Petrus(Vin.getMinimumVolume(), 2909, 1955);
			Petrus pp = (Petrus) p.clone();
			pp.setVolume(Vin.getMinimumVolume() + 100);
			pp.setPrix(15000);
			pp.setAnnee(1865);
			c1.ajouter(pp);
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

		//On demande l'identité du client
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
		
		int nb_vins = cave.getNbVins();
		System.out.println("Bien " + nom + ", nous avons actuellement " + nb_vins + " bouteilles de vin à notre disposition.\n");
		System.out.println("(Vous avez un budget de "+budget+" euros)\n");
		
		Vin v_rec = null; //vin recommandé

		int entree = 0; //variable qui contient l'entrée de l'utilisateur
		do {
			try {
				System.out.println("Choisissez parmi les options suivantes :\n"
						+ "1 - Voir notre carte de vins\n"
						+ "2 - Obtenir une recommendation\n"
						+ "3 - Tester un verre\n"
						+ "4 - Acheter une bouteille de vin\n"
						+ "5 - Savourer votre vin\n"
						+ "6 - Quitter et voir l'addition\n");
				System.out.print("Choisissez : ");
				
				//on demande l'entrée à l'utilisateur
				entree = scanner.nextInt();
				if (entree == 1) {
                    System.out.println("Notre cave à vin traite les cépages les plus importants, voici les trois concernés :");
                    while (true) {
                        try {
                            System.out.println("1: Chardonnay\n2: Merlot\n3: Pinot Noir");
                            System.out.print("\nChoisissez un cépage de votre choix : ");
                            int entree_cepage = scanner.nextInt();

                            if (entree_cepage > 3 || entree_cepage < 1) {
                                System.out.println("Veuillez-nous pardonner " + nom + ", que désirez-vous ?");
                            } else {
                                ArrayList<Vin> vin_liste = cave.getSelectionCepage(entree_cepage-1);
                                System.out.println(cave.afficherCarte(vin_liste));
                                System.out.print("\n\n\n");
                                break;
                            }
                        } catch (Exception e) {
                            System.out.println("Veuillez-nous pardonner " + nom + ", que désirez-vous ?");
                            //e.printStackTrace();
                            scanner.next(); //reinitialise le scanner
                        }
                    }
				} else if (entree == 2) {
					//random recommendation selon le budget
                    int entree_budget = 0; //deuxième entrée pour l'utilisateur pour ne pas créer de conflit avec entree

                    do {
                        System.out.print("Pourriez-vous nous faire part de votre budget s'il vous plaît ? : ");
                        try {
                            entree_budget = scanner.nextInt();
                            if (entree_budget > cave.getPrixVinMinimum()) {
                                v_rec = cave.getRecommendation(entree_budget);
                                System.out.println("\nPour vous, aujourd'hui, nous recommendons le meilleur vin de l'année " + v_rec.getAnnee());
                                System.out.println("C'est le " + "<<" + v_rec.toString() +">>");
                            } else {
                                System.out.println("Notre selection de vins semble un peu hors de votre portée.\nAugmentez votre budget pour pouvoir goûter nos vins !");
                            }
                        } catch (Exception e) {
                            System.out.println("Désolé " + nom + ", quel est votre budget ?");
                            scanner.next();
                        }
                    } while (entree_budget < cave.getPrixVinMinimum());
				} else if (entree == 3) {
                    System.out.println("Très bien, quel verre de vin désire " + nom + " ?  (entrez le numéro du vin ou 0 pour sortir)");
					
					Vin[] selection = cave.getSelection(); //on récupère tous les vins de notre cave pour faire une sélection pour le client
					
					while (true) {
						//affichage vins
						cave.afficherSelection();
						System.out.println("\nSélection du vin : ");
						int entree_bis = 0;
						boolean ranger = false;
						try {
							entree_bis = scanner.nextInt();
							if (entree_bis == 0) break; //on sort de la boucle si on entre 0
							
							if (entree_bis > cave.getNbVins()+1 || entree_bis < 0) {
								System.out.println("Veuillez-nous pardonner " + nom + ", mais cela ne correspond pas à un de nos vins.");
							} else {
								Vin vin = selection[entree_bis-1];
								//on vérifie si le verre est déjà rempli
								if (verre.getVolume() > 0) {
									System.out.println("(Il me reste du vin " + verre.getVin().getNom() + " dans mon verre)\n(Jetter ou garder ?) (0: jeter / 1: garder) : ");
									int entree_jeter = -1;
									while (entree_jeter != 0 && entree_jeter != 1) {
										try {
											entree_jeter = scanner.nextInt();
											if (entree_jeter == 0) {
												verre.vider();
												System.out.println("(Vous jetez votre vin)\n");
											} else if (entree_jeter == 1) {
												System.out.println("(Vous rangez votre verre)\n");
												ranger = true; //on range le verre
											}
										} catch (Exception e) {
											scanner.next();
										}
									}
								}
								if (ranger) break; //on doit casser la boucle while si on a rangé notre verre
								
								verre.ajouter(vin);
								float prix = verre.getPrix();
								if (budget - prix > 0) {
									System.out.println(vin + ". Ah oui ce fameux vin !\n");
									System.out.println(Cave.genererMessage(vin.getCouleur()));
									budget -= verre.getPrix(); //le prix du verre est déterminé par le vin versé
									System.out.println("Voici votre verre de vin, savourez-le.\n");
								} else {
									System.out.println("Hmph. Il semblerait que "+nom+" vienne chez Jae&Chan's les mains vides. (pas assez de budget)");
								}
								break;
							}
						} catch (Exception e) {
							System.out.println("Veuillez-nous pardonner " + nom + ", ce vin n'est pas disponible chez-nous, que désirez-vous ?");
							scanner.next();
						}
					}
				} else if (entree == 4) {

                    //on vérifie la recommendation
                    boolean recommandation_accepte = false;

                    if (v_rec != null) {
                        System.out.println("La recommendation était le <<" + v_rec.toString() + ">>");

                        int entree_rec = -1; //entrée secondaire pour l'utilisateur
                        do {
                            System.out.println("Voulez-vous essayer la recommendation ? (0 : oui / 1 : non) : ");
                            try {
                                entree_rec = scanner.nextInt();
                                if (entree_rec == 0) {
                                    System.out.println("D'accord " + nom + " !");
                                    verre.ajouter(v_rec);
                                    float prix = verre.getPrix();
                                    if (budget - prix > 0) {
                                        System.out.println(Cave.genererMessage(v_rec.getCouleur()));
                                        budget -= verre.getPrix(); //le prix du verre est déterminé par le vin versé
                                        System.out.println("Voici votre verre de vin, savourez-le.\n");
                                    } else {
                                        System.out.println("Hmph. Il semblerait que "+nom+" vienne chez Jae&Chan's les mains vides. (pas assez de budget)");
                                    }
                                    recommandation_accepte = true;
                                    break; //on casse la boucle car on vient de sélectionner note vin
                                } else if (entree_rec == 1) {
                                    System.out.println("Vous aimeriez alors peut-être nos autres vins.\n");
                                } else {
                                    System.out.println("Désolé " + nom + ", nous n'avons pas compris.");
                                }
                            } catch (Exception e) {
                                System.out.println("Nos plus sincères excuses " + nom + ", nous n'avons pas compris.");
                                scanner.next(); //réinitialise le scanner
                            }
                        } while (entree_rec != 0 && entree_rec != 1);
                    }

                    if (recommandation_accepte) continue; //on passe le code en bas qui traite de la sélection de vin car on a choisi la recommandation déjà

                    //fin recommandation

					System.out.println("Très bien, quel vin désire " + nom + " ? (entrez le numéro du vin ou 0 pour sortir)");
					
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

								if (budget - vin.getPrix() > 0) {
									System.out.println(vin + ". Excellent choix !\n");
									System.out.println(Cave.genererMessage(vin.getCouleur()));
									budget -= vin.getPrix();
								} else {
									System.out.println("Hmph. Il semblerait que "+nom+" vienne chez Jae&Chan's les mains vides. (pas assez de budget)");
								}
								break;
							}
						} catch (Exception e) {
							System.out.println("Veuillez-nous pardonner " + nom + ", ce vin n'est pas disponible chez-nous, que désirez-vous ?");
							scanner.next();
						}
					}
				} else if (entree == 5) { 
					boolean reste = verre.boire();
					if (reste) { //s'il reste du vin à boire
						int entree_bis = -1;
						while (true) {
							System.out.println("(Il vous reste du vin, voulez-vous continuer à boire ?) (0 : oui / 1 : non) : ");
							try {
								entree_bis = scanner.nextInt();
								if (entree_bis == 0) {
									reste = verre.boire();
									if (!reste) {
										System.out.println("(Vous n'avez pas de vin dans votre verre)\n");
										break;
									}
								} else if (entree_bis == 1) {
									System.out.println("(Vous rangez votre verre)\n");
									break;
								}
							} catch (Exception e) {
								scanner.next();
							}
						}
					} else {
						System.out.println("(Vous n'avez pas de vin dans votre verre)\n");
					}
				} else if (entree == 6) {
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
		} while (true);
		scanner.close();
	}
}
