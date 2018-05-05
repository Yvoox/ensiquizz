package model.questions;

import org.apache.jena.query.QuerySolution;
import util.Constantes;
import util.DBpediaQuery;

import java.util.Arrays;
import java.util.List;

/**
 * Retrouver le nom d'une race de chien à partir d'une photo.
 *
 * @see QuestionFactory
 * @author Alexandre Colicchio, Thibaud Gasser
 */
public class RaceChienQuestion extends Question {

    RaceChienQuestion() {
        super(Constantes.RACE_CHIEN);
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
                "}";
        final List<QuerySolution> solutions = DBpediaQuery.execRequete(requete);
        // On construit la question et sa bonne réponse
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

    /**
     * Tire une entrée au hasard parmi la liste des résulats de la requête.
     * @param solutions liste des résultats de la requête SPARQL.
     * @return objet Reponse qui contient le nom de la race et une url vers sa photo.
     */
    private Reponse creerReponse(List<QuerySolution> solutions) {
        // On tire une réponse aléatoire
        final QuerySolution solution = solutions.get(rand.nextInt(solutions.size()));
        final String nomRace = solution.getLiteral("breadName").getString();
        final String imgUrl = solution.get("picture").asResource().getURI();
        return new Reponse(nomRace, imgUrl);
    }

    /**
     * Classe de données qui stocke les éléments utiles à la question extraits à partir des
     * résultats de la requête SPARQL.
     */
    private final class Reponse {
        private final String nomRace;
        private final String imgUrl;

        /**
         * @param nomRace nom de la race.
         * @param imgUrl url vers la photo.
         */
        private Reponse(String nomRace, String imgUrl) {
            this.nomRace = nomRace;
            this.imgUrl = imgUrl;
        }
    }
}
