package model.questions;

import util.Constantes;

import java.util.Random;

/**
 * Classe de base pour décrire une question.
 *
 * @see QuestionFactory
 * @author Thibaud Gasser, Alexandre Colicchio
 */
public abstract class Question {

    private final int categorie;
    final Random rand;
    String imgUrl;
    String enonce;
    String bonneReponse;
    final String[] mauvaisesReponses;

    /**
     * Construit une question d'une certaine catégorie
     * @param categorie entier représentant la catégorie.
     */
    Question(int categorie) {
        this.categorie = categorie;
        this.mauvaisesReponses = new String[Constantes.NB_REPONSES - 1];
        this.imgUrl = null;
        this.rand = new Random();
    }

    /**
     * Construit une question en interrogeant le web sémantique avec une requête sparql.
     * La question construite dépend de la sous-classe de question.
     */
    public abstract void ask();

    public int getCategorie() {
        return categorie;
    }

    public String getEnonce() {
        return enonce;
    }

    public String getBonneReponse() {
        return bonneReponse;
    }

    public String[] getMauvaisesReponses() {
        return mauvaisesReponses;
    }

    /**
     * Récuperer l'image correspondant à la question
     * @return l'url de l'image dans un String, ou null si la question n'a pas d'image.
     */
    public String getImageUrl() {
        return imgUrl;
    }

    /**
     * Savoir si la question courante est une question avec image
     * @return true si la question à une image.
     */
    public boolean hasImage() {
        return this.imgUrl != null;
    }

}
