package modele;

import java.util.Random;

import javafx.scene.paint.Color;

public class Calculs {

	public final static int RGB_MIN = 0;
	public final static int RGB_MAX = 255;
	public static final Random RANDOMIZER = new Random();

	/**
	 * Retourne une nombre random entre min et max
	 * @param min
	 * @param max
	 * @return
	 */
	public static int randomIntMaxInclu(int min, int max) {
		return RANDOMIZER.nextInt(max + 1 - min) + min;
	}

	/**
	 * Retourne un nombre random entre 0 et max
	 * @param max
	 * @return
	 */
	public static int randomIntMaxInclu(int max) {
		return randomIntMaxInclu(0, max);
	}

	/**
	 * Retourne une couleur aléatoire
	 * @return
	 */
	public static Color randomColor() {

		double red = randomDoubleFraction(RGB_MAX);
		double green = randomDoubleFraction(RGB_MAX);
		double blue = randomDoubleFraction(RGB_MAX);

		Color color = new Color(red, green, blue, 1);

		return color;
	}

	/**
	 * Retourne une fraction aléatoire  de min/max à 1
	 * @param min
	 * @param max
	 * @return
	 */
	public static double randomDoubleFraction(int min, int max) {
		return ((double) (randomIntMaxInclu(min, max))) / ((double) (max));
	}

	/**
	 * Retourne une fraction aléatoire de 0 à 1
	 * @param max
	 * @return
	 */
	public static double randomDoubleFraction(int max) {
		return randomDoubleFraction(0, max);
	}
}