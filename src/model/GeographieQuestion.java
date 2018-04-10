package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.apache.jena.query.QuerySolution;

import util.Constantes;
import util.DBpediaQuery;

public class GeographieQuestion extends Question {

	protected GeographieQuestion(int categorie) {
		super(categorie);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ask() {
		// TODO Auto-generated method stub
		Random rand = new Random();
		
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
			this.mauvaisesReponses[i] = solutions.get(rand.nextInt(solutions.size()))
					.get("capitale")
					.toString()
					.replace("http://fr.dbpedia.org/resource/", "")
					.replaceAll("_", " ");
		}
		this.bonneReponse = reponse;
		
		this.enonce = "Quelle est la capitale de ce pays : "+ pays + "?";
		

	}

}
