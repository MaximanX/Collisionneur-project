package collisionneur.modele;

import java.util.ArrayList;

import javafx.animation.TranslateTransition;

public class Deplacement extends Thread{
	
	private TranslateTransition pathTransition = new TranslateTransition();
	ArrayList<Particule> listeMouvement = new ArrayList<Particule>();

	@Override
	public void run() {
		
		for(int i = 1; i <= listeMouvement.size(); i++) {
			
		}
		
	}
	
}
