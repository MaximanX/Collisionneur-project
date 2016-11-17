package collisionneur.controleur;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ControleurCollisionneur extends Application {

	@FXML
	private BorderPane root;

	@FXML
	private Label lblNbrFormes;

	@FXML
	private Button buttonGenerer;

	@FXML
	private Button buttonReinitialiser;

	@FXML
	private Button buttonQuitter;

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
		System.out.println("Make your dreams come true");
	}
	@FXML
	private void restInPeace(){
		System.out.println("the balls rest in peace");
	}
	
	@FXML
	private void iQuit() {
		Platform.exit();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(
				ControleurCollisionneur.class.getResource("/collisionneur/vue/VueCollisionneur.fxml"));
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
