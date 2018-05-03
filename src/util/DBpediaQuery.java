package util;

import org.apache.jena.query.*;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;

import java.util.List;

public abstract class DBpediaQuery {

    private static final String DBPEDIA_URL = "http://dbpedia.org/sparql";

    public static List<QuerySolution> execRequete(String requete) {
        Query query = QueryFactory.create(requete);
        List<QuerySolution> results = null;
        // Remote execution.
        try (QueryExecution qexec = QueryExecutionFactory.sparqlService(DBPEDIA_URL, query)) {
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
