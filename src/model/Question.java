package model;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.jena.query.QuerySolution;

import util.Constantes;
import util.DBpediaQuery;

public abstract class Question {
	
	protected int categorie;
	protected String enonce;
	protected String bonneReponse;
	protected String[] mauvaisesReponses;
	
	protected Question(int categorie)
	{
		this.categorie=categorie;
		this.mauvaisesReponses= new String[3];
	}
	
	/**
	 * Pose la question à l'utilisateur, attend sa réponse et renvoie 1 si la réponse est bonne, 0 sinon.
	 * @param entry
	 * @return
	 */
	public int ask(Scanner entry)
	{		
		
		//List<QuerySolution> solutions = DBpediaQuery.execRequete("select * where {<http://fr.dbpedia.org/resource/Paris> ?r ?p}");
	/*	Iterator it = solutions.get(0).varNames();
		while(it.hasNext()) {
			System.out.println(it.next());
		}*/
		/*for(QuerySolution it : solutions) {
			System.out.println(it.get("p"));
		}*/
		return 0;
	}

	

}
