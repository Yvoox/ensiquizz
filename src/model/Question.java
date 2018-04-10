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
	

	public void ask()
	{		
	}

	public int getCategorie() {
		return categorie;
	}

	public void setCategorie(int categorie) {
		this.categorie = categorie;
	}

	public String getEnonce() {
		return enonce;
	}

	public void setEnonce(String enonce) {
		this.enonce = enonce;
	}

	public String getBonneReponse() {
		return bonneReponse;
	}

	public void setBonneReponse(String bonneReponse) {
		this.bonneReponse = bonneReponse;
	}

	public String[] getMauvaisesReponses() {
		return mauvaisesReponses;
	}

	public void setMauvaisesReponses(String[] mauvaisesReponses) {
		this.mauvaisesReponses = mauvaisesReponses;
	}
	
	

	

}
