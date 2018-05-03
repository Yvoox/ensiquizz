package model;

import org.apache.jena.query.QuerySolution;
import util.DBpediaQuery;

import java.util.List;
import java.util.Random;

public class ProgrammingLanguageQuestion extends Question {

    protected ProgrammingLanguageQuestion(int categorie) {
        super(categorie);
    }

    @Override
    public void ask() {
        String query = "SELECT DISTINCT ?languageName ?paradigmName ?designerName\n" +
                "WHERE \n" +
                "{\n" +
                "  ?page <http://dbpedia.org/ontology/programmingLanguage> ?language .\n" +
                "  ?language <http://www.w3.org/2000/01/rdf-schema#label> ?languageName .\n" +
                "  ?language <http://dbpedia.org/property/paradigm> ?paradigm .\n" +
                "  ?paradigm <http://www.w3.org/2000/01/rdf-schema#label> ?paradigmName .\n" +
                "  ?language <http://dbpedia.org/ontology/designer> ?designer .\n" +
                "  ?designer <http://xmlns.com/foaf/0.1/name> ?designerName .\n" +
                "\n" +
                "  FILTER(lang(?languageName)='en') .\n" +
                "  FILTER(lang(?paradigmName)='fr') .\n" +
                "}";

        List<QuerySolution> solutions = DBpediaQuery.execRequete(query);
        final Reponse reponse = creerReponse(solutions);
        // On tire au hasard un type de question
        QuestionType questionType = QuestionType.values()[rand.nextInt(QuestionType.values().length)];
        switch (questionType) {
            // Trouver le nom du langage à partir de son créateur
            case DESIGNER:
                enonce = "Quel est le créateur du langage de programmation : " + reponse.language;
                bonneReponse = reponse.designer;
                // On choisit 3 mauvaises réponses dans la liste des solutions
                for (int i = 0; i < mauvaisesReponses.length; i++) {
                    mauvaisesReponses[i] = creerReponse(solutions).designer;
                }
                break;
            // Idem quand dans le premier cas mais, il faut cette fois-ci trouver le nom du langage
            case DESIGNER_REVERSE:
                enonce = "Quel langage de programmation à créé " + reponse.designer + " ?";
                bonneReponse = reponse.language;
                // On choisit 3 mauvaises réponses dans la liste des solutions
                for (int i = 0; i < mauvaisesReponses.length; i++) {
                    mauvaisesReponses[i] = creerReponse(solutions).language;
                }
                break;
            // Question "Quel est le paradigme du langage de programmation xxx"
            case PARADIGM:
                enonce = "Quel est le paradigme du langage de programmation " + reponse.language;
                bonneReponse = reponse.paradigm;
                // On choisit 3 mauvaises réponses dans la liste des solutions
                for (int i = 0; i < mauvaisesReponses.length; i++) {
                    mauvaisesReponses[i] = creerReponse(solutions).paradigm;
                }
                break;
        }
    }

    private Reponse creerReponse(List<QuerySolution> solutions) {
        // On tire une réponse aléatoire
        final QuerySolution solution = solutions.get(rand.nextInt(solutions.size()));
        final String language = solution.getLiteral("languageName").getString();
        final String designer = solution.getLiteral("designerName").getString();
        final String paradigm = solution.getLiteral("paradigmName").getString();
        return new Reponse(language, designer, paradigm);
    }

    private final class Reponse {
        private String language;
        private String designer;
        private String paradigm;

        private Reponse(String language, String designer, String paradigm) {
            this.language = language;
            this.designer = designer;
            this.paradigm = paradigm;
        }
    }

    /**
     * TODO : documentation
     */
    private enum QuestionType {
        DESIGNER, PARADIGM, DESIGNER_REVERSE
    }
}
