package controleur;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

import java.util.ListIterator;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import modele.Calculs;
import modele.Particule;

public class ControleurCollisionneur extends Application {

	@FXML
	private Pane particuleContainer;

	@FXML
	private BorderPane root;

	@FXML
	private Label lblNbrFormes;

	@FXML
	private Button buttonQuitter;

	@FXML
	private Button buttonReinitialiser;

	@FXML
	private Button buttonGenerer;

	@FXML
	private Slider vitesseIO;

	@FXML
	private Slider angleIO;

	@FXML
	private Slider rayonIO;

	@FXML
	private ColorPicker theChosenOne;
	final private ObservableList<Particule> particules = FXCollections.observableArrayList();
	private boolean hasAnimationStarted = false;
	StringProperty nbrParticules;

	private static final int NUM_BALLS = 10;

	@FXML
	private void justDoIt() {

		creerParticules();

	}

	/**
	 * Crée une particule
	 * 
	 * @param event
	 */

	@FXML
	private void makeYourDreamsComeTrue(MouseEvent event) {
		creerParticules(rayonIO.getValue(), vitesseIO.getValue(), angleIO.getValue(), event.getX(), event.getY(),
				theChosenOne.getValue());
	}

	/**
	 * Reset l'application (retire toutes les particules de l'espace)
	 */
	@FXML
	private void restInPeace() {
		particules.clear();
		particuleContainer.getChildren().clear();
	}

	/**
	 * Ferme l'application
	 */
	@FXML
	private void iQuit() {
		Platform.exit();
	}

	/**
	 * L:e début de l'application
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/vue/VueCollisionneur.fxml"));

		root = loader.load();

		Scene scene = new Scene(root);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * 
	 */
	private void demarerAnimation() {
		hasAnimationStarted = true;
		LongProperty tempsEcouleDepuisDerniereVerification = new SimpleLongProperty(0);
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long temps) {
				if (tempsEcouleDepuisDerniereVerification.get() > 0) {
					long tempsMettreAJour = temps - tempsEcouleDepuisDerniereVerification.get();
					verifierCollisions(particuleContainer.getWidth(), particuleContainer.getHeight());
					mettreAJour(tempsMettreAJour);
				}
				tempsEcouleDepuisDerniereVerification.set(temps);
			}

		};
		timer.start();

	}

	/**
	 * Déplace les particulles
	 * 
	 * @param elapsedTime
	 */
	private void mettreAJour(long elapsedTime) {
		double elapsedSeconds = elapsedTime / 1_000_000_000.0;
		for (Particule p : particules) {
			p.setCenterX(p.getCenterX() + elapsedSeconds * p.getVitesseX());
			p.setCenterY(p.getCenterY() + elapsedSeconds * p.getVitesseY());
			if (p.getCenterX() > particuleContainer.getWidth() || p.getCenterX() < 0
					|| p.getCenterY() > particuleContainer.getHeight() || p.getCenterY() < 0) {
				p.setCenterX(particuleContainer.getWidth() / 2);
				p.setCenterY(particuleContainer.getHeight() / 2);
			}
		}
	}

	/**
	 * Gère les collisions Trouve si les particules sont en collision et
	 * s'occupe de réaliser celles-ci
	 * 
	 * @param maxX
	 * @param maxY
	 */
	private void verifierCollisions(double maxX, double maxY) {
		for (ListIterator<Particule> slowIt = particules.listIterator(); slowIt.hasNext();) {
			Particule b1 = slowIt.next();
			double xVel = b1.getVitesseX();
			double yVel = b1.getVitesseY();
			if ((b1.getCenterX() - b1.getRadius() <= 0 && xVel < 0)
					|| (b1.getCenterX() + b1.getRadius() >= maxX && xVel > 0)) {
				b1.setVitesseX(-xVel);
			}
			if ((b1.getCenterY() - b1.getRadius() <= 0 && yVel < 0)
					|| (b1.getCenterY() + b1.getRadius() >= maxY && yVel > 0)) {
				b1.setVitesseY(-yVel);
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

	/**
	 * Utilisé par verifierCollisions pour réaliser celles-ci
	 * 
	 * @param p1
	 *            Première particule de la collision
	 * @param p2
	 *            Deuxième particule de la collision
	 * @param deltaX
	 *            Distance entre les deux en X
	 * @param deltaY
	 *            Distance entre les deux en Y
	 * @return
	 */
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

	/**
	 * Étape finale utilisé par verifierCollison pour faire rebondir les balles
	 * 
	 * @param p1
	 *            Première particule de la collision
	 * @param p2
	 *            Deuxième particule de la collision
	 * @param deltaX
	 *            Distance entre les deux en X
	 * @param deltaY
	 *            Distance entre les deux en Y
	 */
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

	/**
	 * Crée une instance de la classe particules avec tout les paramètres qui
	 * vont avec
	 * 
	 * @param rayon
	 * @param vitesse
	 * @param angle
	 * @param initialX
	 * @param initialY
	 * @param couleur
	 */

	private void creerParticules(double rayon, double vitesse, double angle, double initialX, double initialY,
			Color couleur) {
		Particule particule = new Particule(initialX, initialY, rayon, vitesse * cos(angle * Math.PI / 180),
				vitesse * sin(angle * Math.PI / 180));
		particule.getView().setFill(couleur);
		particules.add(particule);
		particuleContainer.getChildren().add(particule.getView());
		if (!hasAnimationStarted) {
			demarerAnimation();
		}
	}

	/**
	 * Crée une particule aléatoire
	 */

	private void creerParticules() {
		for (int i = 0; i < NUM_BALLS; i++) {
			Random random = new Random();
			double rayon = random.nextDouble() * (10 - 4) + (4);
			rayonIO.setValue(rayon);
			double vitesse = random.nextDouble() * 10;
			vitesseIO.setValue(vitesse);
			double angle = random.nextDouble() * 360;
			angleIO.setValue(angle);

			Color color = Calculs.randomColor();

			theChosenOne.setValue(color);

			creerParticules(rayon, vitesse, angle, particuleContainer.getWidth() / 2,
					particuleContainer.getHeight() / 2, color);
		}
	}

	/**
	 * Lance L'application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Est lancé au début lorsque le fxml est initialisé
	 */

	@FXML
	public void initialize() {

		lblNbrFormes.textProperty()
				.bind((new SimpleListProperty<>(particuleContainer.getChildren())).sizeProperty().asString());
	}
}