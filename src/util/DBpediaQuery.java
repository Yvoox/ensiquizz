package util;

import org.apache.jena.query.*;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;

import java.util.List;

public abstract class DBpediaQuery {

    public static List<QuerySolution> execRequete(String requete) {
        return getQuerySolutions(requete, Constantes.DBPEDIA_URL);
    }

    public static List<QuerySolution> execRequeteFr(String requete) {
        return getQuerySolutions(requete, Constantes.DBPEDIA_FR_URL);
    }

    private static List<QuerySolution> getQuerySolutions(String requete, String url) {
        Query query = QueryFactory.create(requete);
        List<QuerySolution> results = null;
        // Remote execution.
        try (QueryExecution qexec = QueryExecutionFactory.sparqlService(url, query)) {
            // Set the DBpedia specific timeout.
            ((QueryEngineHTTP) qexec).addParam("timeout", "10000");

            // Execute.
            ResultSet rs = qexec.execSelect();
            //ResultSetFormatter.out(System.out, rs, query);
            results = ResultSetFormatter.toList(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }
}
