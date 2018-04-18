package view;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;



import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Context;
import model.Player;


public class QuizzViewTextController {
	private String bonneRep;
	private Player currJoueur;
	private boolean answerd = false;
	
	@FXML
	private Label question;
	@FXML
	private Label joueur;
	@FXML
	private Label rep1;
	@FXML
	private Label rep2;
	@FXML
	private Label rep3;
	@FXML
	private Label rep4;
	@FXML
	private Label intitule;
	@FXML
	private Button suivant;
	
	
	private Context context;



    // Reference to the main application.
    private Main main;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public QuizzViewTextController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	suivant.setVisible(false);
		intitule.setVisible(true);
		rep1.setVisible(true);
		rep2.setVisible(true);
		rep3.setVisible(true);
		rep4.setVisible(true);

		
    	
        
        
    }
    
    @FXML
    private void rep1() {
    	if(rep1.getText().equals(bonneRep)) {
    		joueur.setText("Bravo "+currJoueur.getName().get()+" !");
    		question.setText("Bonne réponse !");
    		intitule.setVisible(false);
    		rep1.setVisible(false);
    		rep2.setVisible(false);
    		rep3.setVisible(false);
    		rep4.setVisible(false);
    		suivant.setVisible(true);
    		currJoueur.endGame(1);
    	}
    	else {
    		joueur.setText("Désolé "+currJoueur.getName().get()+" !");
    		question.setText("Mauvaise réponse !");
    		intitule.setVisible(false);
    		rep1.setVisible(false);
    		rep2.setVisible(false);
    		rep3.setVisible(false);
    		rep4.setVisible(false);
    		suivant.setVisible(true);
    		currJoueur.endGame(0);
    	}
    	
    }
    
    @FXML
    private void rep2() {
    	if(rep2.getText().equals(bonneRep)) {
    		joueur.setText("Bravo "+currJoueur.getName().get()+" !");
    		question.setText("Bonne réponse !");
    		intitule.setVisible(false);
    		rep1.setVisible(false);
    		rep2.setVisible(false);
    		rep3.setVisible(false);
    		rep4.setVisible(false);
    		suivant.setVisible(true);
    		currJoueur.endGame(1);
    	}
    	else {
    		joueur.setText("Désolé "+currJoueur.getName().get()+" !");
    		question.setText("Mauvaise réponse !");
    		intitule.setVisible(false);
    		rep1.setVisible(false);
    		rep2.setVisible(false);
    		rep3.setVisible(false);
    		rep4.setVisible(false);
    		suivant.setVisible(true);
    		currJoueur.endGame(0);
    	}
    }
    
    @FXML
    private void rep3()  {
    	if(rep3.getText().equals(bonneRep)) {
    		joueur.setText("Bravo "+currJoueur.getName().get()+" !");
    		question.setText("Bonne réponse !");
    		intitule.setVisible(false);
    		rep1.setVisible(false);
    		rep2.setVisible(false);
    		rep3.setVisible(false);
    		rep4.setVisible(false);
    		suivant.setVisible(true);
    		currJoueur.endGame(1);
    	}
    	else {
    		joueur.setText("Désolé "+currJoueur.getName().get()+" !");
    		question.setText("Mauvaise réponse !");
    		intitule.setVisible(false);
    		rep1.setVisible(false);
    		rep2.setVisible(false);
    		rep3.setVisible(false);
    		rep4.setVisible(false);
    		suivant.setVisible(true);
    		currJoueur.endGame(0);
    	}
    }
    
    @FXML
    private void rep4()  {
    	if(rep4.getText().equals(bonneRep)) {
    		joueur.setText("Bravo "+currJoueur.getName().get()+" !");
    		question.setText("Bonne réponse !");
    		intitule.setVisible(false);
    		rep1.setVisible(false);
    		rep2.setVisible(false);
    		rep3.setVisible(false);
    		rep4.setVisible(false);
    		suivant.setVisible(true);
    		currJoueur.endGame(1);
    	}
    	else {
    		joueur.setText("Désolé "+currJoueur.getName().get()+" !");
    		question.setText("Mauvaise réponse !");
    		intitule.setVisible(false);
    		rep1.setVisible(false);
    		rep2.setVisible(false);
    		rep3.setVisible(false);
    		rep4.setVisible(false);
    		suivant.setVisible(true);
    		currJoueur.endGame(0);
    	}
    }
    
    @FXML
    private void suivant()  {
    	answerd = true;
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(Main main) {
        this.main = main;
        context = main.getContext();
    }
	
    public void setViewParameters(int questionNumber, Player player,String questionIntitule, String[] mauvaisesReponses, String bonneReponse) {

    	this.currJoueur = player;
    	intitule.setText(questionIntitule);
    	question.setText(Integer.toString(questionNumber));
    	joueur.setText(currJoueur.getName().get() + ", c'est à vous !");
    	
    	List<String> rep = new ArrayList<>();
    	this.bonneRep = bonneReponse;
    	rep.add(bonneReponse);
    	for(int i = 0; i < mauvaisesReponses.length ; i++) {
    		rep.add(mauvaisesReponses[i]);
    	}
    	Collections.shuffle(rep);
    	
    	rep1.setText(rep.get(0));
    	rep2.setText(rep.get(1));
    	rep3.setText(rep.get(2));
    	rep4.setText(rep.get(3));
    	
    }
    
    
    public boolean isFinish() {
    	return answerd;
    }
    


}
