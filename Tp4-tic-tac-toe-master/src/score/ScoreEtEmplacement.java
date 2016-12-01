package score;

import modele.Emplacement;
import modele.Table;

public class ScoreEtEmplacement {
	private int score;
	private Emplacement emplacement;
	private Table tabledeJeu;

	ScoreEtEmplacement(int score, Emplacement emplacementAjoute, Table tableDeJeu) {
		this.score = score;
		this.emplacement = emplacementAjoute;
		this.tabledeJeu=tableDeJeu;
	}
	
	
	
}
