package modele;

import static java.lang.Math.sqrt;

import java.util.concurrent.Callable;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class Particule {

	public static final int MIN_VITESSE = 0;
	public static final int MAX_VITESSE = 10;
	public static final int MIN_DIRECTION = 0;
	public static final int MAX_DIRECTION = 360;
	public static final int MIN_RAYON = 4;
	public static final int MAX_RAYON = 10;
	public static final int MIN_X = 0;
	public static final int MIN_Y = 0;
	public static final int MAX_X = 100;
	public static final int MAX_Y = 100;
	private final DoubleProperty vitesseX;
	private final DoubleProperty vitesseY;
	private final ReadOnlyDoubleWrapper vitesse;
	private final double masse;
	private final double radius;

	private final Circle cercle;

	/**
	 * Constructeur de particule avec tous ses paramètres
	 * @param positionX
	 * @param positionY
	 * @param rayon
	 * @param vitesseX
	 * @param vitesseY
	 */
	public Particule(double positionX, double positionY, double rayon, double vitesseX, double vitesseY) {

		this.cercle = new Circle(positionX, positionY, rayon);
		this.vitesseX = new SimpleDoubleProperty(this, "vitesseX", vitesseX * 10);
		this.vitesseY = new SimpleDoubleProperty(this, "vitesseY", vitesseY * 10);
		this.vitesse = new ReadOnlyDoubleWrapper(this, "vitesse");
		this.radius = rayon;
		this.masse = Math.pow((rayon / 40), 3);
		cercle.setRadius(rayon);
		vitesse.bind(Bindings.createDoubleBinding(new Callable<Double>() {

			@Override
			public Double call() throws Exception {
				final double xVel = getVitesseX();
				final double yVel = getVitesseY();
				return sqrt(xVel * xVel + yVel * yVel);
			}
		}, this.vitesseX, this.vitesseY));

	}

	public double getMasse() {
		return masse;
	}

	public double getRadius() {
		return radius;
	}

	public final double getVitesseX() {
		return vitesseX.get();
	}

	public final void setVitesseX(double xVelocity) {
		this.vitesseX.set(xVelocity);
	}

	public final DoubleProperty xVelocityProperty() {
		return vitesseX;
	}

	public final double getVitesseY() {
		return vitesseY.get();
	}

	public final void setVitesseY(double yVelocity) {
		this.vitesseY.set(yVelocity);
	}

	public final DoubleProperty yVelocityProperty() {
		return vitesseY;
	}

	public final double getSpeed() {
		return vitesse.get();
	}

	public final ReadOnlyDoubleProperty speedProperty() {
		return vitesse.getReadOnlyProperty();
	}

	public final double getCenterX() {
		return cercle.getCenterX();
	}

	public final void setCenterX(double centerX) {
		cercle.setCenterX(centerX);
	}

	public final DoubleProperty centerXProperty() {
		return cercle.centerXProperty();
	}

	public final double getCenterY() {
		return cercle.getCenterY();
	}

	public final void setCenterY(double centerY) {
		cercle.setCenterY(centerY);
	}

	public final DoubleProperty centerYProperty() {
		return cercle.centerYProperty();
	}

	public Shape getView() {
		return cercle;
	}

}