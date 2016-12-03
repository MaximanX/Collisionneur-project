package controleur;

import javafx.application.Platform;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import modele.ChambreDeParticules;
import modele.Particule;

public class ControleurCollisionneur {

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
	private ObservableList<Particule> particules = FXCollections.observableArrayList();
	StringProperty nbrParticules;

	private ChambreDeParticules chambreDeParticules;

	public ControleurCollisionneur() {
	}

	@FXML
	private void justDoIt() {

		chambreDeParticules.creerParticules();

	}

	@FXML
	private void makeYourDreamsComeTrue(MouseEvent event) {
		chambreDeParticules.creerParticules(rayonIO.getValue(), vitesseIO.getValue(), angleIO.getValue(), event.getX(),
				event.getY(), theChosenOne.getValue());
	}

	@FXML
	private void restInPeace() {
		particules.clear();
		particuleContainer.getChildren().clear();
	}

	@FXML
	private void iQuit() {
		Platform.exit();
	}

	@FXML
	public void initialize() {
		lblNbrFormes.textProperty()
				.bind((new SimpleListProperty<>(particuleContainer.getChildren())).sizeProperty().asString());
		chambreDeParticules = new ChambreDeParticules(particuleContainer, particules, this);
	}

	public void setVitesseIO(double vitesseIO) {
		this.vitesseIO.setValue(vitesseIO);
	}

	public void setAngleIO(double angleIO) {
		this.angleIO.setValue(angleIO);
	}

	public void setRayonIO(double rayonIO) {
		this.rayonIO.setValue(rayonIO);
	}

	public void setTheChosenOne(Color theChosenOne) {
		this.theChosenOne.setValue(theChosenOne);
	}
}