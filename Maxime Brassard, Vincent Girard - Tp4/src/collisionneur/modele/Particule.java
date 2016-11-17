package collisionneur.modele;

import javafx.scene.paint.Color;

public class Particule {

	public static final int MIN_VITESSE = 0;
	public static final int MAX_VITESSE = 10;
	public static final int MIN_DIRECTION = 0;
	public static final int MAX_DIRECTION = 360;
	public static final int MIN_RAYON = 4;
	public static final int MAX_RAYON = 10;
	public static final int MIN_X = 0;
	public static final int MIN_Y = 0;
	// TODO établir la position maximum en X
	public static final int MAX_X = 100;
	// TODO établir la position maximum en Y
	public static final int MAX_Y = 100;
	private int positionY;
	private int positionX;
	// Le rayon agit aussi comme la masse pour les calculs
	private int rayon;
	private int direction;
	private Color couleur;
	private int vitesse;

	public Particule(int pVitesse, int pDirection, int pRayon, Color pCouleur, int pPositionX, int pPositionY) {

		if (verifierPosY(pPositionY) && verifierPosX(pPositionX) && verifierRayon(pRayon)
				&& verifierDirection(pDirection) && verifierVitesse(pVitesse)) {
			setCouleur(pCouleur);
			setDirection(pDirection);
			setPositionX(pPositionX);
			setPositionY(pPositionY);
			setRayon(pRayon);
			setVitesse(pVitesse);
		}

	}

	private boolean verifierPosY(int posY) {
		boolean result = false;
		if (posY >= MIN_Y && posY <= MAX_Y) {
			result = true;
		}
		return result;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		if (verifierPosY(positionY)) {
			this.positionY = positionY;
		}
	}

	private boolean verifierPosX(int posX) {
		boolean result = false;
		if (posX >= MIN_X && posX <= MAX_X) {
			result = true;
		}
		return result;
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		if (verifierPosX(positionX)) {
			this.positionX = positionX;
		}
	}

	private boolean verifierRayon(int rayon) {
		boolean result = false;
		if (rayon >= MIN_RAYON && rayon <= MAX_RAYON) {
			result = true;
		}
		return result;
	}

	public int getRayon() {
		return rayon;
	}

	public void setRayon(int rayon) {
		if (verifierRayon(rayon)) {
			this.rayon = rayon;
		}
	}

	private boolean verifierDirection(int direction) {
		boolean result = false;
		if (direction >= MIN_DIRECTION && direction <= MAX_DIRECTION) {
			result = true;
		}
		return result;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		if (verifierDirection(direction)) {
			this.direction = direction;
		}
	}

	public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}

	private boolean verifierVitesse(int vitesse) {
		boolean result = false;
		if (vitesse >= MIN_VITESSE && vitesse <= MAX_VITESSE) {
			result = true;
		}
		return result;
	}

	public int getVitesse() {
		return vitesse;
	}

	public void setVitesse(int vitesse) {
		if (verifierVitesse(vitesse)) {
			this.vitesse = vitesse;
		}
	}

}
