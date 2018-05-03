package model;

import java.util.ArrayList;
import java.util.List;

import util.Constantes;

public class Partie {
	List<Player> joueurs;
	Player joueurCourant;
	int compteurQuestion;
	Question questionCourante;

	public Partie() {
		joueurs = new ArrayList<>();
		compteurQuestion = 1;

	}

	public void ajouterJoueur(Player joueur) {
		joueurs.add(joueur);
	}

	public void retirerJoueur(int index) {
		joueurs.remove(index);
	}

	public void start() {
		joueurCourant = joueurs.get(0);
		compteurQuestion = 1;
		questionCourante = generationQuestion();
		questionCourante.ask();
	}

	public Player joue() {
		if(!verificationFinPartie()) {
			System.out.println(joueurs.indexOf(joueurCourant)+1);
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

	private Question generationQuestion() {
		return QuestionFactory.createQuestion();
	}

	private boolean verificationFinPartie() {
		if(compteurQuestion < Constantes.NB_QUESTIONS) {
			return false;
		}
		else return true;
	}

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
