package modele;

import java.util.Random;

import javafx.scene.paint.Color;

public class Calculs {

	public final static int RGB_MIN = 0;
	public final static int RGB_MAX = 255;
	public static final Random RANDOMIZER = new Random();

	public static int randomIntMaxInclu(int min, int max) {
		return RANDOMIZER.nextInt(max + 1 - min) + min;
	}

	public static int randomIntMaxInclu(int max) {
		return randomIntMaxInclu(0, max);
	}

	public static Color randomColor() {

		double red = randomDoubleFraction(RGB_MAX);
		double green = randomDoubleFraction(RGB_MAX);
		double blue = randomDoubleFraction(RGB_MAX);

		Color color = new Color(red, green, blue, 1);

		return color;
	}

	public static double randomDoubleFraction(int min, int max) {
		return ((double) (randomIntMaxInclu(min, max))) / ((double) (max));
	}

	public static double randomDoubleFraction(int max) {
		return randomDoubleFraction(0, max);
	}
}