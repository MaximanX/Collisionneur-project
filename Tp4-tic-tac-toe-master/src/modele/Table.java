package modele;

import java.util.ArrayList;

public class Table {

	private int[][] tableTicTacToe = new int[3][3];

	private int[][] backup = { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };

	private ArrayList<Emplacement> emplacementsVides;

	public int[][] getTableTicTacToe() {
		return tableTicTacToe;
	}

	public void setTableTicTacToeStart() {
		this.tableTicTacToe = backup;
	}

	public void setTable(int x, int y, int val) {
		if (isPlayable(x, y) && 0 <= val && val <= 3) {
			tableTicTacToe[x][y] = val;
		}
	}

	public boolean isPlayable(int x, int y) {
		return tableTicTacToe[x][y] == 0;
	}

	public boolean isItFinished() {
		return hasPlayerWon() || hasAIWon() || listeEmplacementsVides().isEmpty();
	}

	public boolean hasPlayerWon() {
		boolean so = false;
		if ((tableTicTacToe[0][0] == tableTicTacToe[1][1] && tableTicTacToe[0][0] == tableTicTacToe[2][2]
				&& tableTicTacToe[0][0] == 1)
				|| (tableTicTacToe[0][2] == tableTicTacToe[1][1] && tableTicTacToe[0][2] == tableTicTacToe[2][0]
						&& tableTicTacToe[0][2] == 1)) {
			// System.out.println("X Diagonal Win");
			so = true;
		}
		for (int i = 0; i < 3; ++i) {
			if (((tableTicTacToe[i][0] == tableTicTacToe[i][1] && tableTicTacToe[i][0] == tableTicTacToe[i][2]
					&& tableTicTacToe[i][0] == 1)
					|| (tableTicTacToe[0][i] == tableTicTacToe[1][i] && tableTicTacToe[0][i] == tableTicTacToe[2][i]
							&& tableTicTacToe[0][i] == 1))) {
				// System.out.println("X Row or Column win");
				so = true;
			}
		}
		return so;
	}

	public boolean hasAIWon() {
		boolean so = false;
		if ((tableTicTacToe[0][0] == tableTicTacToe[1][1] && tableTicTacToe[0][0] == tableTicTacToe[2][2]
				&& tableTicTacToe[0][0] == 2)
				|| (tableTicTacToe[0][2] == tableTicTacToe[1][1] && tableTicTacToe[0][2] == tableTicTacToe[2][0]
						&& tableTicTacToe[0][2] == 2)) {
			// System.out.println("O Diagonal Win");
			so = true;
		}
		for (int i = 0; i < 3; ++i) {
			if ((tableTicTacToe[i][0] == tableTicTacToe[i][1] && tableTicTacToe[i][0] == tableTicTacToe[i][2]
					&& tableTicTacToe[i][0] == 2)
					|| (tableTicTacToe[0][i] == tableTicTacToe[1][i] && tableTicTacToe[0][i] == tableTicTacToe[2][i]
							&& tableTicTacToe[0][i] == 2)) {
				// System.out.println("O Row or Column win");
				so = true;
			}
		}

		return so;
	}

	public ArrayList<Emplacement> listeEmplacementsVides() {

		setEmplacementsVides();

		return emplacementsVides;
	}

	public void setEmplacementsVides() {
		if (emplacementsVides == null) {
			emplacementsVides = new ArrayList<>();
		}
		emplacementsVides.clear();
		for (int i = 0; i < tableTicTacToe.length; i++) {
			for (int j = 0; j < tableTicTacToe.length; j++) {
				if (tableTicTacToe[i][j] == 0) {
					emplacementsVides.add(new Emplacement(i, j));
				}
			}
		}
	}

	private boolean canIDoIt(int x, int y) {
		boolean so = false;

		for (Emplacement emplacement : listeEmplacementsVides()) {
			if (emplacement.getX() == x) {
				if (emplacement.getY() == y) {
					so = true;
				}
			}
		}

		return so;
	}

	public void placeAMove(Emplacement emplacement, int joueur) {
		if (canIDoIt(emplacement.getX(), emplacement.getY())) {
			tableTicTacToe[emplacement.getX()][emplacement.getY()] = joueur;
		}
	}

}
