package fr.diginamic.recensement.services;

import java.util.List;
import java.util.Scanner;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.recensement.utils.CodeDeptException;

/**
 * Recherche et affichage de toutes les villes d'un département dont la
 * population est comprise entre une valeur min et une valeur max renseignées
 * par l'utilisateur.
 * 
 * @author DIGINAMIC
 *
 */
public class RecherchePopulationBorneService extends MenuService {
	@Override
	public void traiter(Recensement rec, Scanner scanner) throws CodeDeptException {
		String choix = "";
		int min = 0;
		int max = 0;

		try {
			System.out.println("Quel est le code du département recherché ? ");
			choix = scanner.nextLine();
		} catch (Exception e) {
			throw new CodeDeptException("Erreur lors de la saisie du code de département.");

		}

		try {
			System.out.println("Choississez une population minimum (en milliers d'habitants): ");
			String saisieMin = scanner.nextLine();
			min = Integer.parseInt(saisieMin) * 1000;

		} catch (NumberFormatException e) {
			throw new CodeDeptException(
					"Erreur lors de la saisie de la population minimum : veuillez entrer un nombre entier.");

		}

		try {
			System.out.println("Choississez une population maximum (en milliers d'habitants): ");
			String saisieMax = scanner.nextLine();
			max = Integer.parseInt(saisieMax) * 1000;

		} catch (NumberFormatException e) {
			throw new CodeDeptException(
					"Erreur lors de la saisie de la population maximum : veuillez entrer un nombre entier et pas inferieur a zero.");

		}
		if (min < 0 || max < 0 || min > max) {
			throw new CodeDeptException(
					"Erreur lors de la saisie de la population maximum : veuillez entrer un nombre entier pas inferieur a zero et que le minimum dépasse pas le maximun.");
		}
		List<Ville> villes = rec.getVilles();
		for (Ville ville : villes) {
			if (ville.getCodeDepartement().equalsIgnoreCase(choix)) {
				if (ville.getPopulation() >= min && ville.getPopulation() <= max) {
					System.out.println(ville);
				}
			} else {
				throw new CodeDeptException(
						"departement inconu");
			}
		}
	}

}
