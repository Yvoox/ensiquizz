package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Partie;
import model.Player;
import view.FirstViewController;
import view.QuizzViewTextController;

public class Main extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	private ObservableList<Player> players = FXCollections.observableArrayList();
	private Partie partie;

	public Main() { //initialisation du joueur 1
		players.add(new Player("Joueur 1"));
		partie = new Partie();
		partie.ajouterJoueur(new Player("Joueur 1"));

	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("EnsiQuizz");

		initRootLayout();
		showPlayers();
	}

	/**
	 * Initializes the root layout.
	 */
	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getClassLoader().getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void showPlayers() {
		try {
			// On charge la vue des joueurs
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getClassLoader().getResource("view/FirstView.fxml"));


			AnchorPane testOverView = (AnchorPane) loader.load();
			// Ajout de la fenêtre de joueur dans la scene principale
			rootLayout.setCenter(testOverView);

			//Ajout du controller
			FirstViewController controller = loader.getController();
			controller.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void startGameLayout() throws IOException, InterruptedException {
		// On charge la vue des joueurs et on initialise la partie
		this.partie.start();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getClassLoader().getResource("view/QuizzViewText.fxml"));


		AnchorPane testOverView = (AnchorPane) loader.load();
		// Ajout de la fenêtre de joueur dans la scene principale
		rootLayout.setCenter(testOverView);



		QuizzViewTextController controller = loader.getController();
		controller.setMainApp(this);


		//On initialise une première fois la vue question
		//Les autres questions et le déroulement du match se gère dans le controller Quizz
		controller.setViewParameters(
				partie.getNumeroQuestion()
				, partie.getQuestionCourante().getEnonce()
				, partie.getQuestionCourante().getMauvaisesReponses()
				, partie.getQuestionCourante().getBonneReponse()
				);







	}




	/**
	 * Returns the main stage.
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {

		launch(args);

	}

	public ObservableList<Player> getPlayers() { //Permet la récupération dans les vues de la liste de joueurs
		return FXCollections.observableArrayList(partie.getJoueurs());
	}

	public Partie getPartie() {
		return this.partie;
	}




}
