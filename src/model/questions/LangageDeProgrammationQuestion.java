package model.questions;

import org.apache.jena.query.QuerySolution;
import util.Constantes;
import util.DBpediaQuery;

import java.util.Arrays;
import java.util.List;

/**
 * Catégorie de question sur les langage de programmation, leur créateur et leur paradigme.
 * Le type de question est choisi en tirant au hasard un {@link LangageDeProgrammationQuestion.QuestionType}
 * <ul>
 *   <li>DESIGNER : retrouver le nom du créateur d'un langage de programmation.</li>
 *   <li>PARADIGM : retrouver le paradigme d'un langage de programmation.</li>
 *   <li>DESIGNER_REVERSE : retrouver le nom du langage qu'à crée la personne proposée.</li>
 * </ul>
 *
 * @see QuestionFactory
 * @author Alexandre Colicchio, Thibaud Gasser
 */
public class LangageDeProgrammationQuestion extends Question {

    LangageDeProgrammationQuestion() {
        super(Constantes.PROGRAMMATION);
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

        final List<QuerySolution> solutions = DBpediaQuery.execRequete(query);
        final Reponse reponse = creerReponse(solutions);
        // On tire au hasard un type de question
        final QuestionType questionType = QuestionType.values()[rand.nextInt(QuestionType.values().length)];
        switch (questionType) {
            // Trouver le nom du langage à partir de son créateur
            case DESIGNER:
                creerQuestionCreateur(solutions, reponse);
                break;
            // Idem quand dans le premier cas mais, il faut cette fois-ci trouver le nom du langage
            case DESIGNER_REVERSE:
                creerQuestionLangage(solutions, reponse);
                break;
            // Question "Quel est le paradigme du langage de programmation xxx"
            case PARADIGM:
                creerQuestionParadigme(solutions, reponse);
                break;
        }
    }

    /**
     * Retrouver le paradigme du langage de programmation proposé.
     * @param solutions liste des résultats de la requête SPARQL.
     * @param reponse objet {@link Reponse}
     */
    private void creerQuestionParadigme(List<QuerySolution> solutions, Reponse reponse) {
        enonce = "Quel est le paradigme du langage de programmation " + reponse.language;
        bonneReponse = reponse.paradigm;
        // On choisit 3 mauvaises réponses dans la liste des solutions
        for (int i = 0; i < mauvaisesReponses.length; i++) {
            String rep;
            // Pour éviter les doublons, on regarde si la réponse choisie
            // n'est pas déjà dans la liste des réponses.
            do {
                rep = creerReponse(solutions).paradigm;
            } while (Arrays.asList(mauvaisesReponses).contains(rep) || bonneReponse.equals(rep));
            mauvaisesReponses[i] = rep;
        }
    }

    /**
     * Retrouver le nom du langage à partir de son créateur.
     * @param solutions liste des résultats de la requête SPARQL.
     * @param reponse objet {@link Reponse}
     */
    private void creerQuestionLangage(List<QuerySolution> solutions, Reponse reponse) {
        enonce = "Quel langage de programmation à créé " + reponse.designer + " ?";
        bonneReponse = reponse.language;
        // On choisit 3 mauvaises réponses dans la liste des solutions
        for (int i = 0; i < mauvaisesReponses.length; i++) {
            String rep;
            // Pour éviter les doublons, on regarde si la réponse choisie
            // n'est pas déjà dans la liste des réponses.
            do {
                rep = creerReponse(solutions).language;
            } while (Arrays.asList(mauvaisesReponses).contains(rep) || bonneReponse.equals(rep));
            mauvaisesReponses[i] = rep;
        }
    }

    /**
     * Retrouver le nom du créateur d'un langage de programmation.
     * @param solutions liste des résultats de la requête SPARQL.
     * @param reponse objet {@link Reponse}
     */
    private void creerQuestionCreateur(List<QuerySolution> solutions, Reponse reponse) {
        enonce = "Quel est le créateur du langage de programmation : " + reponse.language;
        bonneReponse = reponse.designer;
        // On choisit 3 mauvaises réponses dans la liste des solutions
        for (int i = 0; i < mauvaisesReponses.length; i++) {
            String rep;
            // Pour éviter les doublons, on regarde si la réponse choisie
            // n'est pas déjà dans la liste des réponses.
            do {
                rep = creerReponse(solutions).designer;
            } while (Arrays.asList(mauvaisesReponses).contains(rep) || bonneReponse.equals(rep));
            mauvaisesReponses[i] = rep;
        }
    }

    /**
     * Tire une entrée au hasard parmi la liste des résulats de la requête.
     * @param solutions liste des résultats de la requête SPARQL.
     * @return objet {@link Reponse} qui contient le nom du langage, de son créateur et de son paradigme.
     */
    private Reponse creerReponse(List<QuerySolution> solutions) {
        // On tire une réponse aléatoire
        final QuerySolution solution = solutions.get(rand.nextInt(solutions.size()));
        final String language = solution.getLiteral("languageName").getString();
        final String designer = solution.getLiteral("designerName").getString();
        final String paradigm = solution.getLiteral("paradigmName").getString();
        return new Reponse(language, designer, paradigm);
    }

    /**
     * Type de question.
     * <ul>
     *   <li>DESIGNER : retrouver le nom du créateur d'un langage de programmation.</li>
     *   <li>PARADIGM : retrouver le paradigme d'un langage de programmation.</li>
     *   <li>DESIGNER_REVERSE : retrouver le nom du langage qu'à crée la personne proposée.</li>
     * </ul>
     */
    private enum QuestionType {
        DESIGNER, PARADIGM, DESIGNER_REVERSE
    }

    /**
     * Classe de données qui stocke les éléments utiles à la question extraits à partir des
     * résultats de la requête SPARQL.
     */
    private final class Reponse {
        private final String language;
        private final String designer;
        private final String paradigm;

        /**
         * @param language nom du langage de programmation
         * @param designer nom de son créateur
         * @param paradigm nom de son paradigme.
         */
        private Reponse(String language, String designer, String paradigm) {
            this.language = language;
            this.designer = designer;
            this.paradigm = paradigm;
        }
    }
}
