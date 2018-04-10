package model;

import java.util.Random;

import util.Constantes;

public class QuestionFactory {

	/**
	 * Renvoie une instance d'une sous-classe de la classe Question. Le choix de la sous-classe instanciée est aléatoire.
	 * @return
	 */
	public static Question createQuestion()
	{
		Random rand = new Random();
		//switch(rand.nextInt(3)) {
		switch(0) {
		case Constantes.GEOGRAPHIE : return new GeographieQuestion(Constantes.GEOGRAPHIE);
		//case Constantes.HISTOIRE : return new HistoireQuestion(Constantes.HISTOIRE);
		//case Constantes.SPORT : return new SportQuestion(Constantes.SPORT);
		default : return null;
		}
	}

}
