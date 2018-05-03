package view;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import application.Main;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import model.Player;


/*
 * 			Image im = new Image("");
			image.setImage(im);
 */

public class QuizzViewTextController {
	private String bonneRep;
	Timeline timer;
	Timeline progressBarTimer;
	
	@FXML
	private ProgressBar progressBar;
	@FXML
	private ImageView image;

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
	@FXML
	private Button accueil;





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
		progressBar.setVisible(true);
		accueil.setVisible(false);
		image.setVisible(false);
		

	}

	@FXML
	/*
	 * Pour chaque Methode rep#
	 * Recuperation de la réponse
	 * On recherche si la réponse est juste ou fausse
	 * Ajouter le point sur le joueur ayant répondu
	 */
	private void rep1() {
		if(rep1.getText().equals(bonneRep)) {
			joueur.setText("Bravo "+main.getPartie().getJoueurCourant().getName().get()+" !");
			question.setText("Bonne réponse !");
			intitule.setVisible(false);
			rep1.setVisible(false);
			rep2.setVisible(false);
			rep3.setVisible(false);
			progressBar.setVisible(false);
			rep4.setVisible(false);
			image.setVisible(false);
			suivant.setVisible(true);
			main.getPartie().getJoueurCourant().endGame(1);
		}
		else {
			joueur.setText("Désolé "+main.getPartie().getJoueurCourant().getName().get()+" !");
			question.setText("Mauvaise réponse !");
			intitule.setText("La réponse était : "+bonneRep);
			rep1.setVisible(false);
			rep2.setVisible(false);
			rep3.setVisible(false);
			image.setVisible(false);
			progressBar.setVisible(false);
			rep4.setVisible(false);
			suivant.setVisible(true);
			main.getPartie().getJoueurCourant().endGame(0);
		}

	}

	@FXML
	private void rep2() {
		if(rep2.getText().equals(bonneRep)) {
			joueur.setText("Bravo "+main.getPartie().getJoueurCourant().getName().get()+" !");
			question.setText("Bonne réponse !");
			intitule.setVisible(false);
			rep1.setVisible(false);
			rep2.setVisible(false);
			rep3.setVisible(false);
			image.setVisible(false);
			progressBar.setVisible(false);
			rep4.setVisible(false);
			suivant.setVisible(true);
			main.getPartie().getJoueurCourant().endGame(1);
		}
		else {
			joueur.setText("Désolé "+main.getPartie().getJoueurCourant().getName().get()+" !");
			question.setText("Mauvaise réponse !");
			intitule.setText("La réponse était : "+bonneRep);
			rep1.setVisible(false);
			rep2.setVisible(false);
			rep3.setVisible(false);
			image.setVisible(false);
			progressBar.setVisible(false);
			rep4.setVisible(false);
			suivant.setVisible(true);
			main.getPartie().getJoueurCourant().endGame(0);
		}
	}

	@FXML
	private void rep3()  {
		if(rep3.getText().equals(bonneRep)) {
			joueur.setText("Bravo "+main.getPartie().getJoueurCourant().getName().get()+" !");
			question.setText("Bonne réponse !");
			intitule.setVisible(false);
			rep1.setVisible(false);
			rep2.setVisible(false);
			rep3.setVisible(false);
			image.setVisible(false);
			progressBar.setVisible(false);
			rep4.setVisible(false);
			suivant.setVisible(true);
			main.getPartie().getJoueurCourant().endGame(1);
		}
		else {
			joueur.setText("Désolé "+main.getPartie().getJoueurCourant().getName().get()+" !");
			question.setText("Mauvaise réponse !");
			intitule.setText("La réponse était : "+bonneRep);
			rep1.setVisible(false);
			rep2.setVisible(false);
			rep3.setVisible(false);
			rep4.setVisible(false);
			image.setVisible(false);
			progressBar.setVisible(false);
			suivant.setVisible(true);
			main.getPartie().getJoueurCourant().endGame(0);
		}
	}

	@FXML
	private void rep4()  {
		if(rep4.getText().equals(bonneRep)) {
			joueur.setText("Bravo "+main.getPartie().getJoueurCourant().getName().get()+" !");
			question.setText("Bonne réponse !");
			intitule.setVisible(false);
			rep1.setVisible(false);
			rep2.setVisible(false);
			rep3.setVisible(false);
			rep4.setVisible(false);
			image.setVisible(false);
			progressBar.setVisible(false);
			suivant.setVisible(true);
			main.getPartie().getJoueurCourant().endGame(1);

		}
		else {
			joueur.setText("Désolé "+main.getPartie().getJoueurCourant().getName().get()+" !");
			question.setText("Mauvaise réponse !");
			intitule.setText("La réponse était : "+bonneRep);
			rep1.setVisible(false);
			rep2.setVisible(false);
			rep3.setVisible(false);
			rep4.setVisible(false);
			image.setVisible(false);
			progressBar.setVisible(false);
			suivant.setVisible(true);
			main.getPartie().getJoueurCourant().endGame(0);
		}
	}
	
	@FXML
	private void accueil() {
		main.showPlayers();
	}

	@FXML
	/*
	 * Methode suivant
	 * Permet de passer au joueur ou à la question suivante
	 * Permet également le chargement de l'interface de gagnant s'il y en a un
	 */
	private void suivant()  {
		progressBarTimer.stop();
		timer.stop();
		if(main.getPartie().joue() != null)
			setViewEndGame();
		else {
			setViewParameters(
					main.getPartie().getNumeroQuestion()
					, main.getPartie().getQuestionCourante().getEnonce()
					, main.getPartie().getQuestionCourante().getMauvaisesReponses()
					, main.getPartie().getQuestionCourante().getBonneReponse()
					);
			intitule.setVisible(true);
			rep1.setVisible(true);
			rep2.setVisible(true);
			rep3.setVisible(true);
			rep4.setVisible(true);
			suivant.setVisible(false);
			progressBar.setVisible(true);
		}
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(Main main) {
		this.main = main;
	}

	/*
	 * Methode setViewParameters
	 * Permet l'actualisation des éléments visuels
	 * Prépare également la liste de réponses à partir de la bonne et des mauvaises
	 * Gère le temps pour chaque question
	 */
	public void setViewParameters(int questionNumber, String questionIntitule, String[] mauvaisesReponses, String bonneReponse) {

		progressBarReset();

		progressBarTimer = new Timeline(new KeyFrame(
		        Duration.millis(1000),
		        actionEvent -> progressBarAdd()));
		progressBarTimer.setCycleCount(Animation.INDEFINITE);
		progressBarTimer.play();
		
		timer = new Timeline(new KeyFrame(
		        Duration.millis(10000),
		        actionEvent -> timeOut()));
		timer.play();
		
		
		intitule.setText(questionIntitule);
		question.setText(Integer.toString(questionNumber));
		joueur.setText(main.getPartie().getJoueurCourant().getName().get() + ", c'est à vous !");

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

	/*
	 * Methode setViewEndGame
	 * Permet l'actualisation de l'interface graphique pour renseigner le joueur gagnant
	 */
	public void setViewEndGame() {
		suivant.setVisible(false);
		rep1.setVisible(false);
		rep2.setVisible(false);
		rep3.setVisible(false);
		rep4.setVisible(false);
		image.setVisible(false);
		joueur.setVisible(false);
		intitule.setVisible(true);
		accueil.setVisible(true);
		progressBar.setVisible(false);
		String classement = "Classement : ";
		question.setText("Bravo, " + main.getPartie().estGagnant().getName().get() + " a gagné avec "+main.getPartie().estGagnant().getScore().get()+" points");
		for(Player it : main.getPartie().getJoueurs()) {
			if(!it.equals(main.getPartie().estGagnant()))
			classement+=it.getName().get()+" avec "+it.getScore().get()+" points ";
		}
		intitule.setText(classement);
	}
	
	public void timeOut() {
		timer.stop();
		progressBarTimer.stop();
		joueur.setText("Désolé "+main.getPartie().getJoueurCourant().getName().get()+" !");
		question.setText("Temps écoulé !");
		intitule.setText("Il faudra répondre plus rapidement la prochaine fois !");
		rep1.setVisible(false);
		rep2.setVisible(false);
		rep3.setVisible(false);
		rep4.setVisible(false);
		image.setVisible(false);
		suivant.setVisible(true);
		main.getPartie().getJoueurCourant().endGame(0);		
	}
	
	public void progressBarAdd() {
		progressBar.setProgress(progressBar.getProgress()+0.1);
	}
	
	public void progressBarReset() {
		progressBar.setProgress(0);
	}
}
