package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Player {
	private IntegerProperty score;
	private StringProperty name;
	
	public Player(String name) {
		this.score = new SimpleIntegerProperty(0);
		this.name =new SimpleStringProperty(name);
	}
	
	public IntegerProperty getScore() {
		return this.score;
	}
	
	public void endGame(int result) {
		if(result == 1) {
			//score.add(1);
			score.set(score.get()+1);
		}
	}
	
	public StringProperty getName() {
		return this.name;
	}
	
	public void resetScore() {
		score.set(0);
	}
	
	public void setName(String name) {
		this.name = new SimpleStringProperty(name);
	}

}
