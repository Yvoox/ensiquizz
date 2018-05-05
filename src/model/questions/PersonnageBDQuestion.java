package model.questions;

import org.apache.jena.query.QuerySolution;
import util.Constantes;
import util.DBpediaQuery;

import java.util.Arrays;
import java.util.List;

/**
 * Retrouver le nom d'un personnage de BD à partir d'une image.
 *
 * @see QuestionFactory
 * @author Alexandre Colicchio, Thibaud Gasser
 */
public class PersonnageBDQuestion extends Question {

    PersonnageBDQuestion() {
        super(Constantes.PERSONNAGE_BD);
    }

    @Override
    public void ask() {
        final String req = "SELECT ?characterName ?picture\n" +
                "WHERE {\n" +
                "  ?char a <http://dbpedia.org/ontology/ComicsCharacter> .\n" +
                "  ?char <http://www.w3.org/2000/01/rdf-schema#label> ?characterName .\n" +
                "  ?char <http://xmlns.com/foaf/0.1/depiction> ?p .\n" +
                "  ?p <http://xmlns.com/foaf/0.1/thumbnail> ?picture .\n" +
                "  FILTER(lang(?characterName)='fr') .\n" +
                "}";
        final List<QuerySolution> solutions = DBpediaQuery.execRequete(req);
        // On construit la question et sa bonne réponse
        final Reponse reponse = creerReponse(solutions);
        enonce = "Quel est le nom de ce personnage de bande dessinée ?";
        imgUrl = reponse.imgUrl;
        bonneReponse = reponse.nomPerso;
        // On choisit 3 mauvaises réponses dans la liste des solutions
        for (int i = 0; i < mauvaisesReponses.length; i++) {
            String nomPerso;
            // Pour éviter les doublons, on regarde si la réponse choisie
            // n'est pas déjà dans la liste des réponses.
            do {
                nomPerso = creerReponse(solutions).nomPerso;
            } while (Arrays.asList(mauvaisesReponses).contains(nomPerso) || bonneReponse.equals(nomPerso));
            mauvaisesReponses[i] = nomPerso;
        }
    }

    /**
     * Tire une entrée au hasard parmi la liste des résulats de la requête.
     * @param solutions liste des résulats de la requête SPARQL.
     * @return objet Reponse qui contient le nom du personnage et une url vers son image.
     */
    private Reponse creerReponse(List<QuerySolution> solutions) {
        final QuerySolution solution = solutions.get(rand.nextInt(solutions.size()));
        final String nomPerso = solution.getLiteral("characterName").getString();
        final String imgUrl = solution.get("picture").asResource().getURI();
        return new Reponse(nomPerso, imgUrl);
    }

    /**
     * Classe de données qui stocke les éléments utiles à la question extraits à partir des
     * résultats de la requête SPARQL.
     */
    private final class Reponse {
        final String nomPerso;
        final String imgUrl;

        /**
         * @param nomPerso nom du personnage de BD.
         * @param imgUrl url vers une image du personnage.
         */
        private Reponse(String nomPerso, String imgUrl) {
            this.nomPerso = nomPerso;
            this.imgUrl = imgUrl;
        }
    }
}
