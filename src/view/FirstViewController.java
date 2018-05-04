package view;

import java.io.IOException;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
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
	@FXML
	private BarChart graphique;
	@FXML 
	private ImageView image;
	@FXML
	private Button start;


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
		graphique.setVisible(false);
	}

	@FXML
	private void delete() {
		//suppression d'un joueur sélectionné dans le tableau
		//Impossibilité de supprimer tous les joueurs
		int selectedIndex = playerTable.getSelectionModel().getSelectedIndex();
		if(selectedIndex != 0) {
			playerTable.getItems().remove(selectedIndex);
			main.getPartie().retirerJoueur(selectedIndex);
			labelNom.setVisible(false);
			nom.setVisible(false);
			}
		
	}

	@FXML
	private void add() {
		//ajout d'un joueur avec comme nom joueur+numéro d'index suivant, limité à 4 joueurs
		if(playerTable.getItems().size() < 4) {
			Player newJoueur = new Player("Joueur "+(playerTable.getItems().size()+1));
			playerTable.getItems().add(newJoueur);
			main.getPartie().ajouterJoueur(newJoueur);
			this.main.getManager().createPlayer(newJoueur);
		}
	}

	@FXML
	private void changeNom(KeyEvent e) {
		if((e.getCode().equals(KeyCode.ENTER) || e.getCharacter().getBytes()[0] == '\n' || e.getCharacter().getBytes()[0] == '\r')) {
			Player joueurSel = playerTable.getSelectionModel().getSelectedItem();
			int index = playerTable.getSelectionModel().getSelectedIndex();
			joueurSel.setName(nom.getText());
			playerTable.getItems().get(index).setName(joueurSel.getName().get());
			this.main.getManager().completeGraph(joueurSel, graphique);
			playerTable.refresh();
			this.main.getManager().createPlayer(joueurSel);
			
			nom.setVisible(false);
			labelNom.setVisible(false);
		}
	}
	
	@FXML
	private void deselectJoueur() {
		graphique.setVisible(false);
		image.setVisible(true);
		start.setVisible(true);
		nom.setVisible(false);
		labelNom.setVisible(false);
	}

	@FXML
	private void prepareChangeNom() {
		nom.setVisible(true);
		labelNom.setVisible(true);
		graphique.setVisible(true);
		image.setVisible(false);
		start.setVisible(false);
		Player joueurSel = playerTable.getSelectionModel().getSelectedItem();
		this.main.getManager().completeGraph(joueurSel, graphique);
		
		nom.setText(joueurSel.getName().get());
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
