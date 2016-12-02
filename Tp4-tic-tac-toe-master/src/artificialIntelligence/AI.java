package artificialIntelligence;

import artificialIntelligence.Adversaire;
import modele.Emplacement;
import modele.Table;

public class AI extends Adversaire {

	public AI(Table tableDeJeu) {
		super(tableDeJeu);

	}

	private Emplacement enplacementDecide() {

		return null;
	}

	public void jouer() {
		this.updateListe();
		Emplacement emplacement;
	}

	private int minimax(int profondeur, int playerNumber) {
		if (tableDeJeu.isItFinished() || profondeur == 0) {

			return 0;
		} else {
			return 0;
		}
	}

}
