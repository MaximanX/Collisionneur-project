package modele;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

import java.util.ListIterator;
import java.util.Random;

import controleur.ControleurCollisionneur;
import javafx.animation.AnimationTimer;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class ChambreDeParticules {

	private static final int NUM_BALLS = 500;
	private ObservableList<Particule> particules = FXCollections.observableArrayList();
	private DoubleProperty largeur = new SimpleDoubleProperty();
	private DoubleProperty hauteur = new SimpleDoubleProperty();
	private boolean hasAnimationStarted = false;
	private Pane pane;
	private ControleurCollisionneur cC;

	public ChambreDeParticules(Pane pane, ObservableList<Particule> particules, ControleurCollisionneur cC) {
		this.pane = pane;
		largeur.bind(this.pane.widthProperty());
		hauteur.bind(this.pane.heightProperty());
		this.particules = particules;
		this.cC = cC;
	}

	public void creerParticules(double rayon, double vitesse, double angle, double initialX, double initialY,
			Color couleur) {
		Particule particule = new Particule(initialX, initialY, rayon, vitesse * cos(angle * Math.PI / 180),
				vitesse * sin(angle * Math.PI / 180));
		particule.getView().setFill(couleur);
		particules.add(particule);
		pane.getChildren().add(particule.getView());
		if (!hasAnimationStarted) {
			demarerAnimation();
		}
	}

	public void creerParticules() {
		for (int i = 0; i < NUM_BALLS; i++) {
			Random random = new Random();
			double rayon = random.nextDouble() * (10 - 4) + (4);
			cC.setRayonIO(rayon);
			double vitesse = random.nextDouble() * 10;
			cC.setVitesseIO(vitesse);
			double angle = random.nextDouble() * 360;
			cC.setAngleIO(angle);

			Color color = Calculs.randomColor();

			cC.setTheChosenOne(color);

			creerParticules(rayon, vitesse, angle, pane.getWidth() / 2, pane.getHeight() / 2, color);
		}
	}

	public void demarerAnimation() {
		if (!hasAnimationStarted) {
			LongProperty tempsEcouleDepuisDerniereVerification = new SimpleLongProperty(0);
			AnimationTimer timer = new AnimationTimer() {
				@Override
				public void handle(long temps) {
					if (tempsEcouleDepuisDerniereVerification.get() > 0) {
						long tempsMettreAJour = temps - tempsEcouleDepuisDerniereVerification.get();
						verifierCollisions(largeur.get(), hauteur.get());
						mettreAJour(tempsMettreAJour);
					}
					tempsEcouleDepuisDerniereVerification.set(temps);
				}
			};
			timer.start();
			hasAnimationStarted = true;
		}
	}

	private void mettreAJour(long elapsedTime) {
		double elapsedSeconds = elapsedTime / 1_000_000_000.0;
		for (Particule p : particules) {
			p.setCenterX(p.getCenterX() + elapsedSeconds * p.getVitesseX());
			p.setCenterY(p.getCenterY() + elapsedSeconds * p.getVitesseY());
			if (p.getCenterX() > largeur.get() || p.getCenterX() < 0 || p.getCenterY() > hauteur.get()
					|| p.getCenterY() < 0) {
				p.setCenterX(largeur.get() / 2);
				p.setCenterY(hauteur.get() / 2);
			}
		}
	}

	private void verifierCollisions(double maxX, double maxY) {
		for (ListIterator<Particule> slowIt = particules.listIterator(); slowIt.hasNext();) {
			Particule b1 = slowIt.next();
			double vitesseX = b1.getVitesseX();
			double vitesseY = b1.getVitesseY();
			if ((b1.getCenterX() - b1.getRadius() <= 0 && vitesseX < 0)
					|| (b1.getCenterX() + b1.getRadius() >= maxX && vitesseX > 0)) {
				b1.setVitesseX(-vitesseX);
			}
			if ((b1.getCenterY() - b1.getRadius() <= 0 && vitesseY < 0)
					|| (b1.getCenterY() + b1.getRadius() >= maxY && vitesseY > 0)) {
				b1.setVitesseY(-vitesseY);
			}
			for (ListIterator<Particule> fastIt = particules.listIterator(slowIt.nextIndex()); fastIt.hasNext();) {
				Particule b2 = fastIt.next();
				final double deltaX = b2.getCenterX() - b1.getCenterX();
				final double deltaY = b2.getCenterY() - b1.getCenterY();
				if (colliding(b1, b2, deltaX, deltaY)) {
					rebondir(b1, b2, deltaX, deltaY);
				}
			}
		}
	}

	public boolean colliding(final Particule p1, final Particule p2, final double deltaX, final double deltaY) {
		boolean so = false;
		final double totalRayon = p1.getRadius() + p2.getRadius();
		if (deltaX * deltaX + deltaY * deltaY <= totalRayon * totalRayon) {
			if (deltaX * (p2.getVitesseX() - p1.getVitesseX()) + deltaY * (p2.getVitesseY() - p1.getVitesseY()) < 0) {
				so = true;
			}
		}
		return so;
	}

	private void rebondir(final Particule p1, final Particule p2, final double deltaX, final double deltaY) {
		double distance = sqrt(deltaX * deltaX + deltaY * deltaY);
		double unitContactX = deltaX / distance;
		double unitContactY = deltaY / distance;

		double vitesseX1 = p1.getVitesseX();
		double vitesseY1 = p1.getVitesseY();
		double vitesseX2 = p2.getVitesseX();
		double vitesseY2 = p2.getVitesseY();

		double vecteur1 = vitesseX1 * unitContactX + vitesseY1 * unitContactY;
		double vecteur2 = vitesseX2 * unitContactX + vitesseY2 * unitContactY;

		double totalMasses = p1.getMasse() + p2.getMasse();
		double differenceMasses = p1.getMasse() - p2.getMasse();

		double v1 = (2 * p2.getMasse() * vecteur2 + vecteur1 * differenceMasses) / totalMasses;
		double v2 = (2 * p1.getMasse() * vecteur1 - vecteur2 * differenceMasses) / totalMasses;

		double u1PerpX = vitesseX1 - vecteur1 * unitContactX;
		double u1PerpY = vitesseY1 - vecteur1 * unitContactY;
		double u2PerpX = vitesseX2 - vecteur2 * unitContactX;
		double u2PerpY = vitesseY2 - vecteur2 * unitContactY;

		p1.setVitesseX(v1 * unitContactX + u1PerpX);
		p1.setVitesseY(v1 * unitContactY + u1PerpY);
		p2.setVitesseX(v2 * unitContactX + u2PerpX);
		p2.setVitesseY(v2 * unitContactY + u2PerpY);

	}
}
