package model.questions;

import org.apache.jena.query.QuerySolution;
import util.Constantes;
import util.DBpediaQuery;

import java.util.Arrays;
import java.util.List;

public class ProtocoleReseauQuestion extends Question {

    ProtocoleReseauQuestion() {
        super(Constantes.PROTOCOLE);
    }

    @Override
    public void ask() {
        final String req = "SELECT DISTINCT ?protocolName ?layerName\n" +
                "WHERE {\n" +
                "   ?protocol <http://purl.org/dc/terms/subject> <http://dbpedia.org/resource/Category:Network_protocols> .\n" +
                "   ?protocol <http://purl.org/dc/terms/subject> ?subject .\n" +
                "   ?protocol <http://www.w3.org/2000/01/rdf-schema#label> ?protocolName .\n" +
                "   ?subject <http://www.w3.org/2004/02/skos/core#broader> <http://dbpedia.org/resource/Category:Protocols_by_OSI_layer> .\n" +
                "   ?subject <http://www.w3.org/2000/01/rdf-schema#label> ?layerName .\n" +
                "\n" +
                "   FILTER(lang(?protocolName)='en')\n" +
                "}";
        final List<QuerySolution> solutions = DBpediaQuery.execRequete(req);
        final Reponse reponse = creerReponse(solutions);
        this.enonce = "Quelle est la couche OSI du protocole " + reponse.nomProtocole + " ?";
        this.bonneReponse = reponse.coucheOSI;
        // On choisit 3 mauvaises réponses dans la liste des solutions
        for (int i = 0; i < mauvaisesReponses.length; i++) {
            String coucheOSI;
            // Pour éviter les doublons, on regarde si la réponse choisie
            // n'est pas déjà dans la liste des réponses.
            do {
                coucheOSI = creerReponse(solutions).coucheOSI;
            } while (Arrays.asList(mauvaisesReponses).contains(coucheOSI) || coucheOSI.equals(bonneReponse));
            mauvaisesReponses[i] = coucheOSI;
        }
    }

    private Reponse creerReponse(List<QuerySolution> solutions) {
        // On tire une réponse aléatoire
        final QuerySolution solution = solutions.get(rand.nextInt(solutions.size()));
        String coucheOSI = solution.getLiteral("layerName").getString();
        String nomProtocole = solution.getLiteral("protocolName").getString();
        return new Reponse(nomProtocole, coucheOSI);
    }

    private final class Reponse {
        final String nomProtocole;
        final String coucheOSI;

        private Reponse(String nomProtocole, String coucheOSI) {
            this.nomProtocole = nomProtocole;
            this.coucheOSI = coucheOSI
                    .toLowerCase()
                    .replace("layer", "")
                    .replace("protocols", "")
                    .trim();
        }
    }
}
