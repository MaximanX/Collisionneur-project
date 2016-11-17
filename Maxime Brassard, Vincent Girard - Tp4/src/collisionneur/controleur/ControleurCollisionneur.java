package collisionneur.controleur;

import collisionneur.modele.Calculs;
import collisionneur.modele.Particule;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ControleurCollisionneur extends Application {

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
	
	@FXML
	private void justDoIt(){
		
		for(int i = 1; i <  Integer.parseInt(lblNbrFormes.getText()); i++) {
			
			int tempVitesse = Calculs.aleatoire(Particule.MIN_VITESSE, Particule.MAX_VITESSE);
			int tempDirection = Calculs.aleatoire(Particule.MIN_DIRECTION, Particule.MAX_DIRECTION);
			int tempRayon = Calculs.aleatoire(Particule.MIN_RAYON, Particule.MAX_RAYON);
			Color tempCouleur = Color.rgb(Calculs.aleatoire(Calculs.RGB_MIN, Calculs.RGB_MAX), Calculs.aleatoire(Calculs.RGB_MIN, Calculs.RGB_MAX), Calculs.aleatoire(Calculs.RGB_MIN, Calculs.RGB_MAX));
			int tempPositionX = Calculs.aleatoire(Particule.MIN_X, Particule.MAX_X);
			int tempPositionY = Calculs.aleatoire(Particule.MIN_Y, Particule.MAX_Y);
			
			Particule temp = new Particule(tempVitesse, tempDirection, tempRayon, tempCouleur, tempPositionX, tempPositionY);
			
		}
	}
	@FXML
	private void restInPeace(){
		//TODO faire en sorte que ca réinitialise
	}
	
	@FXML
	private void iQuit() {
		Platform.exit();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/collisionneur/vue/VueCollisionneur.fxml"));
		root = loader.load();
		lblNbrFormes = (Label) ((VBox) ((GridPane) ((VBox) ((HBox) (root.getChildren().get(1))).getChildren().get(0))
				.getChildren().get(0)).getChildren().get(0)).getChildren().get(1);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	
}
