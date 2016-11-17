package collisionneur.modele;

public class Calculs {
	
	public final static int RGB_MIN = 0;
	public final static int RGB_MAX = 255;

	public static int aleatoire(int min, int max) {
		return (min + (int)(Math.random() * ((max - min) + 1)));
	}
	
}
