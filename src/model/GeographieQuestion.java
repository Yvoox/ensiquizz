package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.apache.jena.query.QuerySolution;

import util.DBpediaQuery;

public class GeographieQuestion extends Question {

	protected GeographieQuestion(int categorie) {
		super(categorie);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int ask(Scanner entry) {
		// TODO Auto-generated method stub
		Random rand = new Random();
		List<String> rep = new ArrayList<>();
		System.out.println("Question de Géographie");
		
		List<QuerySolution> solutions = DBpediaQuery.execRequete(
				"prefix dbpedia-owl: <http://dbpedia.org/ontology/>\n" + 
				"prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" + 
				"\n" + 
				"\n" + 
				"select ?x where {\n" + 
				"?x rdf:type dbpedia-owl:Country .\n" + 
				"} LIMIT 100"
				);

		String pays = solutions.get(rand.nextInt(solutions.size())).get("x").toString();
		
		
		solutions = DBpediaQuery.execRequete(
				"prefix dbpedia-owl: <http://dbpedia.org/ontology/>\n" + 
				"prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" + 
				"\n" + 
				"\n" + 
				"select ?capitale where {\n<" + 
				pays +"> dbpedia-owl:capital ?capitale .\n" + 
				"}"
				);
		
		String reponse = solutions.get(solutions.size()-1).get("capitale").toString();
		
		reponse = reponse.replace("http://fr.dbpedia.org/resource/", "");
		reponse = reponse.replaceAll("_", " ");
		pays = pays.replace("http://fr.dbpedia.org/resource/", "");
		pays = pays.replaceAll("_", " ");
		
		
		rep.add(reponse);
		
		solutions = DBpediaQuery.execRequete(
				"prefix dbpedia-owl: <http://dbpedia.org/ontology/>\n" + 
				"prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" + 
				"\n" + 
				"\n" + 
				"select ?capitale where {\n" + 
				"?country a dbpedia-owl:Country;\n" +
				"dbpedia-owl:capital ?capitale .\n" + 
				"}"
				);
		
		for(int i = 0; i<3; i++) {
			rep.add(solutions.get(rand.nextInt(solutions.size()))
					.get("capitale")
					.toString()
					.replace("http://fr.dbpedia.org/resource/", "")
					.replaceAll("_", " "));
		}
		
		Collections.shuffle(rep);
		int valid = rep.indexOf(reponse);
		
		System.out.println("Quelle est la capitale de ce pays : " + pays + " ?");
		for(int i = 0; i<=3;i++) {
			System.out.println((i+1)+") "+rep.get(i));
		}
		String choose = entry.nextLine();
		
		System.out.println("Votre choix : " + choose);
		if(Integer.parseInt(choose) == valid+1) {
			System.out.println("BRAVO VOUS AVEZ TROUVÉ LA BONNE RÉPONSE !");
			return 1;
		}
		else {
			System.out.println("NON, LA BONNE RÉPONSE ÉTAIT : "+ reponse);
			return 0;
		}
	}

}
