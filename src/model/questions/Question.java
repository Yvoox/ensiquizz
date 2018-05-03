package model.questions;

import util.Constantes;

import java.util.Random;

public abstract class Question {

    private final int categorie;
    final Random rand;
    String imgUrl;
    String enonce;
    String bonneReponse;
    final String[] mauvaisesReponses;

    Question(int categorie) {
        this.categorie = categorie;
        this.mauvaisesReponses = new String[Constantes.NB_REPONSES - 1];
        this.imgUrl = null;
        this.rand = new Random();
    }

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

    public String getImageUrl() {
        return imgUrl;
    }

    public boolean hasImage() {
        return this.imgUrl != null;
    }

}
