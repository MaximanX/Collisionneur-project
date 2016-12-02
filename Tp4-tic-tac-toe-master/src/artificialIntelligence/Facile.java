package artificialIntelligence;

import java.util.ArrayList;

import controleur.ControleurTicTacToe;
import modele.Emplacement;
import modele.Table;

public class Facile extends Adversaire {

	public Facile(Table tableDeJeu) {
		super(tableDeJeu);
		emplacementsVides = tableDeJeu.listeEmplacementsVides();
	}

	private Emplacement emplacementJoue(ArrayList<Emplacement> emplacementsVides) {

		
	}

	private Emplacement bloquer() {
		int[][] tab = tableDeJeu.getTableTicTacToe();
		Emplacement retour = null;
		// lignes

		for (int x = 0; x < tab.length; x++) {
			int x1 = 0;
			int v1 = 0;
			for (int i = 0; i < tab.length; i++) {

				if (tab[x][i] == 1) {
					x1++;
				} else if (tab[x][i] == 0) {
					v1++;
				}
				if (x1 == 2 && v1 == 1) {
					if (tableDeJeu.isPlayable(x, i)) {
						retour = new Emplacement(x, i);
					}
				}
			}
		}
		return retour;

	}

}
