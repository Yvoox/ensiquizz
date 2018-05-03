package view;

import java.io.IOException;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Player;

public class FirstViewController {
	@FXML
	private TableView<Player> playerTable;
	@FXML
	private TableColumn<Player, String> nameColumn;
	@FXML
	private Label labelNom;
	@FXML
	private TextField nom;


	// Reference to the main application.
	private Main main;

	/**
	 * The constructor.
	 * The constructor is called before the initialize() method.
	 */
	public FirstViewController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		// Initialize the person table with the two columns.
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
		labelNom.setVisible(false);
		nom.setVisible(false);

	}

	@FXML
	private void delete() {
		//suppression d'un joueur sélectionné dans le tableau
		//Impossibilité de supprimer tous les joueurs
		int selectedIndex = playerTable.getSelectionModel().getSelectedIndex();
		if(selectedIndex != 0) {
			playerTable.getItems().remove(selectedIndex);
			main.getPartie().retirerJoueur(selectedIndex);}
	}

	@FXML
	private void add() {
		//ajout d'un joueur avec comme nom joueur+numéro d'index suivant, limité à 4 joueurs
		if(playerTable.getItems().size() < 4) {
			Player newJoueur = new Player("Joueur "+(playerTable.getItems().size()+1));
			playerTable.getItems().add(newJoueur);
			main.getPartie().ajouterJoueur(newJoueur);
		}
	}

	@FXML
	private void changeNom(KeyEvent e) {
		if((e.getCode().equals(KeyCode.ENTER) || e.getCharacter().getBytes()[0] == '\n' || e.getCharacter().getBytes()[0] == '\r')) {
			Player joueurSel = playerTable.getSelectionModel().getSelectedItem();
			int index = playerTable.getSelectionModel().getSelectedIndex();
			joueurSel.setName(nom.getText());
			playerTable.getItems().get(index).setName(joueurSel.getName().get());
			playerTable.refresh();

			
			nom.setVisible(false);
			labelNom.setVisible(false);
		}
	}

	@FXML
	private void prepareChangeNom() {
		nom.setVisible(true);
		labelNom.setVisible(true);
		nom.setText(playerTable.getSelectionModel().getSelectedItem().getName().get());
	}

	@FXML
	private void startGame() throws IOException, InterruptedException {
		//permet de lancer une partie
		main.startGameLayout();
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(Main main) {
		this.main = main;

		// Add observable list data to the table
		playerTable.setItems(main.getPlayers());
	}

}
