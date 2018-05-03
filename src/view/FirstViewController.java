package view;

import java.io.IOException;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Player;

public class FirstViewController {
	@FXML
    private TableView<Player> playerTable;
    @FXML
    private TableColumn<Player, String> nameColumn;


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
