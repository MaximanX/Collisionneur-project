package application;

import controleur.ControleurCollisionneur;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Collisionneur extends Application {

	private BorderPane root = null;
	private Stage stage = null;

	@SuppressWarnings("unused")
	private ControleurCollisionneur contrColl = null;

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage=primaryStage;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/vue/VueCollisionneur.fxml"));

		root = (BorderPane) loader.load();
		contrColl = loader.getController();
		Scene scene = new Scene(root);

		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
