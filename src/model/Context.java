package model;

public class Context {
	
	private int questionNumber;
	private String playerName;
	private String questionIntitule;
	private String[] mauvaisesReponses;
	private String bonneReponse;
	
	public Context(int questionNumber, String playerName, String questionIntitule, String[] mauvaisesReponses, String bonneReponse) {
    	this.questionNumber = questionNumber;
    	this.playerName = playerName;
    	this.questionIntitule = questionIntitule;
    	this.mauvaisesReponses = mauvaisesReponses;
    	this.bonneReponse = bonneReponse;
	}

	public int getQuestionNumber() {
		return questionNumber;
	}

	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getQuestionIntitule() {
		return questionIntitule;
	}

	public void setQuestionIntitule(String questionIntitule) {
		this.questionIntitule = questionIntitule;
	}

	public String[] getMauvaisesReponses() {
		return mauvaisesReponses;
	}

	public void setMauvaisesReponses(String[] mauvaisesReponses) {
		this.mauvaisesReponses = mauvaisesReponses;
	}

	public String getBonneReponse() {
		return bonneReponse;
	}

	public void setBonneReponse(String bonneReponse) {
		this.bonneReponse = bonneReponse;
	}
	
	

}
