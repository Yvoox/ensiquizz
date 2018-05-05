package model;

import java.util.ArrayList;
import java.util.List;

import model.questions.Question;
import model.questions.QuestionFactory;

public class Partie {
	List<Player> joueurs;
	Player joueurCourant;
	int compteurQuestion;
	Question questionCourante;

	public Partie() {
		joueurs = new ArrayList<>();
		// Initialisation du compteur à 1 permet de répéter une partie
		//Permettra plus tard de gérer des scores sur plusieurs parties
		compteurQuestion = 1;
	}

	public void ajouterJoueur(Player joueur) {
		joueurs.add(joueur);
	}

	public void retirerJoueur(int index) {
		joueurs.remove(index);
	}
	
	/*
	 * Méthode start
	 * Permet de générer une partie dans un état initial
	 */

	public void start() {
		for(Player it : joueurs) {
			it.resetScore();
		}
		joueurCourant = joueurs.get(0);
		compteurQuestion = 1;
		questionCourante = generationQuestion();
		questionCourante.ask();
	}

	/*
	 *Methode joue
	 *Permet de valider la choix d'un joueur
	 *Passage à la question suivante ou fin si le nombre de question max est atteint
	 *Génération successive des questions
	 * Attention : le score de chaque joueur est mis à jour dans la classe Player
	 * On renvoie null s'il n'y a pas encore de gagnant, le joueur gagnant sinon
	 */
	public Player joue() {
		if(!verificationFinPartie()) {
			if(joueurs.indexOf(joueurCourant)+1 < joueurs.size())
				joueurCourant = joueurs.get(joueurs.indexOf(joueurCourant)+1);
			else {
				joueurCourant = joueurs.get(0);
				compteurQuestion++;
			}

			questionCourante = generationQuestion();
			questionCourante.ask();
			return null;
		}
		else {
			return estGagnant();
		}
	}

	/*
	 * Methode generationQuestion
	 * Permet de déléguer la méthode à la factory 
	 */
	private Question generationQuestion() {
		return QuestionFactory.createQuestion();
	}

	private boolean verificationFinPartie() {
		//if(compteurQuestion < Constantes.NB_QUESTIONS) {

			if(compteurQuestion < 3) {
			return false;
		}
		else return true;
	}

	/*
	 * Methode estGagnant
	 * Renvoie le gagnant de la partie
	 * Elle peut servir à trouver le joueur en tête également
	 */
	public Player estGagnant() {
		int scoreMax = 0;
		Player winner = null;
		for(Player it : joueurs) {
			if(it.getScore().get() > scoreMax) {
				scoreMax = it.getScore().get();
				winner = it;
				
			}
		}
		return winner;
	}

	public Player getJoueur(int index) {
		return joueurs.get(index);
	}

	public List<Player> getJoueurs(){
		return joueurs;
	}

	public Player getJoueurCourant() {
		return this.joueurCourant;
	}

	public Question getQuestionCourante() {
		return questionCourante;
	}

	public int getNumeroQuestion() {
		return this.compteurQuestion;
	}


}
