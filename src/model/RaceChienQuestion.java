package model;

import org.apache.jena.query.QuerySolution;
import util.DBpediaQuery;

import java.util.Arrays;
import java.util.List;

public class RaceChienQuestion extends Question {

    protected RaceChienQuestion(int categorie) {
        super(categorie);
    }

    @Override
    public void ask() {
        final String requete = "SELECT DISTINCT ?breadName ?picture\n" +
                "WHERE\n" +
                "{\n" +
                "  ?bread <http://purl.org/dc/terms/subject> <http://dbpedia.org/resource/Category:Dog_breeds> .\n" +
                "  ?bread <http://www.w3.org/2000/01/rdf-schema#label> ?breadName .\n" +
                "  ?bread <http://xmlns.com/foaf/0.1/depiction> ?p .\n" +
                "  ?p <http://xmlns.com/foaf/0.1/thumbnail> ?picture .\n" +
                "  \n" +
                "  FILTER(lang(?breadName)='fr') .\n" +
                "} LIMIT 100";
        final List<QuerySolution> solutions = DBpediaQuery.execRequete(requete);
        final Reponse reponse = creerReponse(solutions);
        enonce = "À quelle race appartient ce chien ?";
        bonneReponse = reponse.nomRace;
        for (int i = 0; i < mauvaisesReponses.length; i++) {
            String nomRace;
            // Pour éviter les doublons, on regarde si la réponse choisie
            // n'est pas déjà dans la liste des réponses.
            do {
                nomRace = creerReponse(solutions).nomRace;
            } while (Arrays.asList(mauvaisesReponses).contains(nomRace) || bonneReponse.equals(nomRace));
            mauvaisesReponses[i] = nomRace;
        }

        imgUrl = reponse.imgUrl;
    }

    private Reponse creerReponse(List<QuerySolution> solutions) {
        // On tire une réponse aléatoire
        final QuerySolution solution = solutions.get(rand.nextInt(solutions.size()));
        final String nomRace = solution.getLiteral("breadName").getString();
        final String imgUrl = solution.get("picture").asResource().getURI();
        return new Reponse(nomRace, imgUrl);
    }

    private final class Reponse {
        private final String nomRace;
        private final String imgUrl;

        private Reponse(String nomRace, String imgUrl) {
            this.nomRace = nomRace;
            this.imgUrl = imgUrl;
        }
    }
}
