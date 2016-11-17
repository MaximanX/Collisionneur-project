package collisionneur.controleur;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
