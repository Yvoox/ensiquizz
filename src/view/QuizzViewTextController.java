package view;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Context;


public class QuizzViewTextController {
	
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

    	
        
        
    }
    
    @FXML
    private void rep1() {
    	question.setText("ezp1");
    }
    
    @FXML
    private void rep2() {
    	question.setText("rep2");
    }
    
    @FXML
    private void rep3()  {
    	question.setText("rep3");
    }
    
    @FXML
    private void rep4()  {
    	question.setText("rep4");
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
	
    public void setViewParameters(int questionNumber, String playerName,String questionIntitule, String[] mauvaisesReponses, String bonneReponse) {

    	
    	intitule.setText(questionIntitule);
    	question.setText(Integer.toString(questionNumber));
    	joueur.setText(playerName + ", c'est à vous !");
    	
    	List<String> rep = new ArrayList<>();
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

}
