 package artificialIntelligence;

import java.util.ArrayList;
import java.util.Random;

import modele.Emplacement;
import modele.Table;

public class Aleatoire extends Adversaire {

	Random randomizer = new Random();

	public Aleatoire(Table tableDeJeu) {
		super(tableDeJeu);
		emplacementsVides = tableDeJeu.listeEmplacementsVides();
	}

	private Emplacement emplacementJoue(ArrayList<Emplacement> emplacementsVides) {
		return emplacementsVides.get(randomizer.nextInt(emplacementsVides.size()));
	}

	public void jouer() {
		this.updateListe();
		Emplacement emplacement = emplacementJoue(emplacementsVides);
		tableDeJeu.getTableTicTacToe()[emplacement.getX()][emplacement.getY()] = 2;
	}

}
