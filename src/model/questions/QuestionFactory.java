package model.questions;

import util.Constantes;

import java.util.Random;

/**
 * Cette classe fournit une méthode statique createQuestion() pour la création de questions.
 * <p>
 * La question créée est d'une des catégories suivantes :
 * <ul>
 *     <li>LangageDeProgrammationQuestion : la question peut être d'un des types suivants :</li>
 *     <ul>
 *         <li>DESIGNER : retrouver le nom du créateur d'un langage de programmation.</li>
 *         <li>PARADIGM : retrouver le paradigme d'un langage de programmation.</li>
 *         <li>DESIGNER_REVERSE : retrouver le nom du langage qu'à crée la personne proposée.</li>
 *     </ul>
 *     <li>RaceChienQuestion : retrouver le nom de la race de chien en photo.</li>
 *     <li>ProtocoleReseauQuestion : retrouver à quelle couche du modèle OSI appartient un protocole réseau.</li>
 *     <li>PersonnageBDQuestion : retrouver le nom d'un personnage de BD à partir d'une image.</li>
 * </ul>
 * </p>
 *
 * @see Question
 * @author Thibaud Gasser, Alexandre Colicchio
 */
public class QuestionFactory {

    /**
     * Renvoie une instance d'une sous-classe de la classe Question.
     * Le choix de la sous-classe instanciée est aléatoire parmi les catégories proposées.
     *
     * @return une instance d'une sous-classe de Question
     */
    public static Question createQuestion() {
        final Random rand = new Random();
        switch (rand.nextInt(Constantes.CATEGORIES.length)) {
            case Constantes.PROGRAMMATION:
                return new LangageDeProgrammationQuestion();
            case Constantes.RACE_CHIEN:
                return new RaceChienQuestion();
            case Constantes.PROTOCOLE:
                return new ProtocoleReseauQuestion();
            case Constantes.PERSONNAGE_BD:
                return new PersonnageBDQuestion();
            default:
                // Ne devrait pas arriver ici
                return null;
        }
    }

}
