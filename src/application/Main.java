package application;

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
import util.Constantes;
import util.CsvManager;
import view.FirstViewController;
import view.QuizzViewTextController;

import java.io.IOException;

public class Main extends Application {

    private static final String ROOT_LAYOUT = "view/RootLayout.fxml";
    private static final String FIRST_VIEW_LAYOUT = "view/FirstView.fxml";
    private static final String QUIZZ_LAYOUT = "view/QuizzViewText.fxml";
    private Stage primaryStage;
    private BorderPane rootLayout;
    @SuppressWarnings("FieldCanBeLocal")
    private final ObservableList<Player> players = FXCollections.observableArrayList();
    private final Partie partie;
    private final CsvManager manager;

    public Main() { //initialisation du joueur 1
        manager = new CsvManager(Constantes.FILE_NAME);
        manager.csvCreation(); //verification de l'existance ou création du csv de sauvegarde des scores
        manager.getResource(); //récupération du fichier
        players.add(new Player("Joueur 1"));
        partie = new Partie();
        partie.ajouterJoueur(new Player("Joueur 1"));
        manager.createPlayer(new Player("Joueur 1"));
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle(Constantes.NOM_APP);
        initRootLayout();
        showPlayers();
    }

    /**
     * Initializes the root layout.
     */
    private void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getClassLoader().getResource(ROOT_LAYOUT));
            rootLayout = loader.load();

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
            loader.setLocation(Main.class.getClassLoader().getResource(FIRST_VIEW_LAYOUT));

            AnchorPane testOverView = loader.load();
            // Ajout de la fenêtre de joueur dans la scene principale
            rootLayout.setCenter(testOverView);

            //Ajout du controller
            FirstViewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startGameLayout() throws IOException {
        // On charge la vue des joueurs et on initialise la partie
        this.partie.start();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getClassLoader().getResource(QUIZZ_LAYOUT));

        AnchorPane testOverView = loader.load();
        // Ajout de la fenêtre de joueur dans la scene principale
        rootLayout.setCenter(testOverView);

        QuizzViewTextController controller = loader.getController();
        controller.setMainApp(this);

        //On initialise une première fois la vue question
        //Les autres questions et le déroulement du match se gère dans le controller Quizz
        controller.setViewParameters(
                partie.getNumeroQuestion(),
                partie.getQuestionCourante().getEnonce(),
                partie.getQuestionCourante().getMauvaisesReponses(),
                partie.getQuestionCourante().getBonneReponse()
        );


    }

    public ObservableList<Player> getPlayers() { //Permet la récupération dans les vues de la liste de joueurs
        return FXCollections.observableArrayList(partie.getJoueurs());
    }

    public Partie getPartie() {
        return this.partie;
    }

    public CsvManager getManager() {
        return this.manager;
    }

}
