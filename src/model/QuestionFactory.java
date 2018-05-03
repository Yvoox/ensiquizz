package model;

import util.Constantes;

import java.util.Random;

public class QuestionFactory {

    /**
     * Renvoie une instance d'une sous-classe de la classe Question. Le choix de la sous-classe instanciée est aléatoire.
     *
     * @return
     */
    public static Question createQuestion() {
        Random rand = new Random();
        switch (rand.nextInt(Constantes.CATEGORIES.length)) {
            case Constantes.GEOGRAPHIE:
                return new GeographieQuestion(Constantes.GEOGRAPHIE);
            case Constantes.PROGRAMMATION:
                return new ProgrammingLanguageQuestion(Constantes.PROGRAMMATION);
            case Constantes.RACE_CHIEN:
                return new RaceChienQuestion(Constantes.RACE_CHIEN);
            default:
                // Ne devrait pas arriver ici
                return null;
        }
    }

}
