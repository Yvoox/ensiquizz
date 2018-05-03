package model.questions;

import org.apache.jena.query.QuerySolution;
import util.Constantes;
import util.DBpediaQuery;

import java.util.Arrays;
import java.util.List;

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

    private Reponse creerReponse(List<QuerySolution> solutions) {
        final QuerySolution solution = solutions.get(rand.nextInt(solutions.size()));
        final String nomPerso = solution.getLiteral("characterName").getString();
        final String imgUrl = solution.get("picture").asResource().getURI();
        return new Reponse(nomPerso, imgUrl);
    }

    private final class Reponse {
        final String nomPerso;
        final String imgUrl;

        private Reponse(String nomPerso, String imgUrl) {
            this.nomPerso = nomPerso;
            this.imgUrl = imgUrl;
        }
    }
}
