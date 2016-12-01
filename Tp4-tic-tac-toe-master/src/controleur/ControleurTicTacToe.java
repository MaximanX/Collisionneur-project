package controleur;

import artificialIntelligence.AI;
import artificialIntelligence.Adversaire;
import artificialIntelligence.Aleatoire;
import artificialIntelligence.Facile;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import modele.Table;

public class ControleurTicTacToe extends Application {

	@FXML
	private ImageView imgHG;
	@FXML
	private ImageView imgHC;
	@FXML
	private ImageView imgHD;
	@FXML
	private ImageView imgCG;
	@FXML
	private ImageView imgCC;
	@FXML
	private ImageView imgCD;
	@FXML
	private ImageView imgBG;
	@FXML
	private ImageView imgBC;
	@FXML
	private ImageView imgBD;
	@FXML
	private BorderPane root;
	@FXML
	private GridPane gridPane;
	@FXML
	private MenuBar menuBar;
	@FXML
	private MenuItem recommencerItem;
	@FXML
	private MenuItem quitterItem;
	@FXML
	private CheckMenuItem aleatoireCheckItem;
	@FXML
	private CheckMenuItem facileCheckItem;
	@FXML
	private CheckMenuItem aiCheckItem;

	private Image stillTrying = new Image(ControleurTicTacToe.class.getResourceAsStream("/images/StillTrying.png"));
	private Image uRDenied = new Image(ControleurTicTacToe.class.getResourceAsStream("/images/URDenied.png"));

	private Table tableDeJeu = new Table();

	private BooleanProperty hasPlayed = new SimpleBooleanProperty(false);
	private boolean isGameOver = false;

	Adversaire adversaire;

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/vue/VueTicTacToe.fxml"));
		root = loader.load();

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	@FXML
	public void initialize() {
		hasPlayed.addListener((new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				isGameOver = tableDeJeu.isItFinished();
				if ((oldValue == false && newValue == true) && !isGameOver) {
					adversaire.jouer();
					isGameOver = tableDeJeu.isItFinished();
				}
				updateView();
			}
		}));

		quitterItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Platform.exit();

			}
		});

		aleatoireCheckItem.selectedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (oldValue == false && newValue == true) {
					facileCheckItem.setSelected(false);
					aiCheckItem.setSelected(false);
					adversaire = new Aleatoire(tableDeJeu);
					tableDeJeu.setTableTicTacToeStart();
					updateView();
					isGameOver = false;
				}
			}
		});
		facileCheckItem.selectedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (oldValue == false && newValue == true) {
					aleatoireCheckItem.setSelected(false);
					aiCheckItem.setSelected(false);
					adversaire = new Facile(tableDeJeu);
					tableDeJeu.setTableTicTacToeStart();
					updateView();
					isGameOver = false;
				}
			}
		});
		aiCheckItem.selectedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (oldValue == false && newValue == true) {
					facileCheckItem.setSelected(false);
					aleatoireCheckItem.setSelected(false);
					adversaire = new AI(tableDeJeu);
					tableDeJeu.setTableTicTacToeStart();
					updateView();
					isGameOver = false;
				}
			}
		});

		adversaire = new Aleatoire(tableDeJeu);

	}

	@FXML
	private void clickedHG() {
		if (!isGameOver) {
			if (tableDeJeu.getTableTicTacToe()[0][0] == 0) {
				tableDeJeu.getTableTicTacToe()[0][0] = 1;
				hasPlayed.setValue(true);
			} else {
				System.out.println("You stupid");
			}
		} else {
			System.out.println("This is the end");
		}
	}

	@FXML
	private void clickedHC() {
		if (!isGameOver) {
			if (tableDeJeu.getTableTicTacToe()[0][1] == 0) {
				tableDeJeu.getTableTicTacToe()[0][1] = 1;
				hasPlayed.setValue(true);
			} else {
				System.out.println("You stupid");
			}
		} else {
			System.out.println("This is the end");
		}
	}

	@FXML
	private void clickedHD() {
		if (!isGameOver) {
			if (tableDeJeu.getTableTicTacToe()[0][2] == 0) {
				tableDeJeu.getTableTicTacToe()[0][2] = 1;
				hasPlayed.setValue(true);
			} else {
				System.out.println("You stupid");
			}
		} else {
			System.out.println("This is the end");
		}
	}

	@FXML
	private void clickedCG() {
		if (!isGameOver) {
			if (tableDeJeu.getTableTicTacToe()[1][0] == 0) {
				tableDeJeu.getTableTicTacToe()[1][0] = 1;
				hasPlayed.setValue(true);
			} else {
				System.out.println("You stupid");
			}
		} else {
			System.out.println("This is the end");
		}
	}

	@FXML
	private void clickedCC() {
		if (!isGameOver) {
			if (tableDeJeu.getTableTicTacToe()[1][1] == 0) {
				tableDeJeu.getTableTicTacToe()[1][1] = 1;
				hasPlayed.setValue(true);
			} else {
				System.out.println("You stupid");
			}
		} else {
			System.out.println("This is the end");
		}
	}

	@FXML
	private void clickedCD() {
		if (!isGameOver) {
			if (tableDeJeu.getTableTicTacToe()[1][2] == 0) {
				tableDeJeu.getTableTicTacToe()[1][2] = 1;
				hasPlayed.setValue(true);
			} else {
				System.out.println("You stupid");
			}
		} else {
			System.out.println("This is the end");
		}
	}

	@FXML
	private void clickedBG() {
		if (!isGameOver) {
			if (tableDeJeu.getTableTicTacToe()[2][0] == 0) {
				tableDeJeu.getTableTicTacToe()[2][0] = 1;
				hasPlayed.setValue(true);
			} else {
				System.out.println("You stupid");
			}
		} else {
			System.out.println("This is the end");
		}
	}

	@FXML
	private void clickedBC() {
		if (!isGameOver) {
			if (tableDeJeu.getTableTicTacToe()[2][1] == 0) {
				tableDeJeu.getTableTicTacToe()[2][1] = 1;
				hasPlayed.setValue(true);
			} else {
				System.out.println("You stupid");
			}
		} else {
			System.out.println("This is the end");
		}
	}

	@FXML
	private void clickedBD() {
		if (!isGameOver) {
			if (tableDeJeu.getTableTicTacToe()[2][2] == 0) {
				tableDeJeu.getTableTicTacToe()[2][2] = 1;
				hasPlayed.setValue(true);
			} else {
				System.out.println("You stupid");
			}
		} else {
			System.out.println("This is the end");
		}
	}

	private void joueurHG() {
		imgHG.setImage(stillTrying);
	}

	private void joueurHC() {
		imgHC.setImage(stillTrying);
	}

	private void joueurHD() {
		imgHD.setImage(stillTrying);
	}

	private void joueurCG() {
		imgCG.setImage(stillTrying);
	}

	private void joueurCC() {
		imgCC.setImage(stillTrying);
	}

	private void joueurCD() {
		imgCD.setImage(stillTrying);
	}

	private void joueurBG() {
		imgBG.setImage(stillTrying);
	}

	private void joueurBC() {
		imgBC.setImage(stillTrying);
	}

	private void joueurBD() {
		imgBD.setImage(stillTrying);
	}

	private void AIBC() {
		imgBC.setImage(uRDenied);
	}

	private void AIBD() {
		imgBD.setImage(uRDenied);
	}

	private void AIHG() {
		imgHG.setImage(uRDenied);
	}

	private void AIHC() {
		imgHC.setImage(uRDenied);
	}

	private void AIHD() {
		imgHD.setImage(uRDenied);
	}

	private void AICG() {
		imgCG.setImage(uRDenied);
	}

	private void AICC() {
		imgCC.setImage(uRDenied);
	}

	private void AICD() {
		imgCD.setImage(uRDenied);
	}

	private void AIBG() {
		imgBG.setImage(uRDenied);
	}

	private void updateView() {
		if (tableDeJeu.getTableTicTacToe()[0][0] == 1) {
			joueurHG();
		} else if (tableDeJeu.getTableTicTacToe()[0][0] == 2) {
			AIHG();
		}
		if (tableDeJeu.getTableTicTacToe()[0][1] == 1) {
			joueurHC();
		} else if (tableDeJeu.getTableTicTacToe()[0][1] == 2) {
			AIHC();
		}
		if (tableDeJeu.getTableTicTacToe()[0][2] == 1) {
			joueurHD();
		} else if (tableDeJeu.getTableTicTacToe()[0][2] == 2) {
			AIHD();
		}
		if (tableDeJeu.getTableTicTacToe()[1][0] == 1) {
			joueurCG();
		} else if (tableDeJeu.getTableTicTacToe()[1][0] == 2) {
			AICG();
		}
		if (tableDeJeu.getTableTicTacToe()[1][1] == 1) {
			joueurCC();
		} else if (tableDeJeu.getTableTicTacToe()[1][1] == 2) {
			AICC();
		}
		if (tableDeJeu.getTableTicTacToe()[1][2] == 1) {
			joueurCD();
		} else if (tableDeJeu.getTableTicTacToe()[1][2] == 2) {
			AICD();
		}
		if (tableDeJeu.getTableTicTacToe()[2][0] == 1) {
			joueurBG();
		} else if (tableDeJeu.getTableTicTacToe()[2][0] == 2) {
			AIBG();
		}
		if (tableDeJeu.getTableTicTacToe()[2][1] == 1) {
			joueurBC();
		} else if (tableDeJeu.getTableTicTacToe()[2][1] == 2) {
			AIBC();
		}
		if (tableDeJeu.getTableTicTacToe()[2][2] == 1) {
			joueurBD();
		} else if (tableDeJeu.getTableTicTacToe()[2][2] == 2) {
			AIBD();
		}
		hasPlayed.set(false);
	}

	public Table getTableDeJeu() {
		return tableDeJeu;
	}
	

}
