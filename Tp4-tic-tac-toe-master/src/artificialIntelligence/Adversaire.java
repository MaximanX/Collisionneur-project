package artificialIntelligence;

import java.util.ArrayList;

import modele.Emplacement;
import modele.Table;

public class Adversaire {

	protected Table tableDeJeu;
	protected ArrayList<Emplacement> emplacementsVides;

	public Adversaire(Table tableDeJeu) {
		this.tableDeJeu = tableDeJeu;
	}

	public void jouer() {

	}

	protected void updateListe() {
		tableDeJeu.setEmplacementsVides();
		emplacementsVides = tableDeJeu.listeEmplacementsVides();
	}

}
