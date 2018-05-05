package util;

public abstract class Constantes {
	
	/**
	 * Nombre de questions dans une partie
	 */
	public static final int NB_QUESTIONS=10;
	
	/**
	 * Nombre de réponses proposées pour chaque question
	 */
	public static final int NB_REPONSES=4;

	public static final int PERSONNAGE_BD = 0;
	public static final int PROGRAMMATION = 1;
	public static final int RACE_CHIEN = 2;
	public static final int PROTOCOLE = 3;

	public static final String[] CATEGORIES = {
			"Personnage de bande dessinée",
			"Programmation",
			"Race de chien",
			"Protocole réseau"
	};
}
