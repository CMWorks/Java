package lib;

import java.util.ArrayList;

public class Card {

	private int id;
	private String prompet, definition;
	private String typeOfCard; // 3 types: "Term", "MC Choice", "True/False"
	private ArrayList<String> choices = new ArrayList<String>();
	private char mcAns;
	private boolean tfAns;

	public Card(int id) {
		this.id = id;
	}
	
	/* Getters and setters */

	public int getId() {
		return id;
	}

	public String getPrompet() {
		return prompet;
	}

	public String getDefinition() {
		return definition;
	}

	public String getTypeOfCard() {
		return typeOfCard;
	}

	public ArrayList<String> getChoices() {
		return choices;
	}

	public char getMcAns() {
		return mcAns;
	}

	public boolean isTfAns() {
		return tfAns;
	}

	public void setPrompet(String prompet) {
		this.prompet = prompet;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public void setTypeOfCard(String typeOfCard) {
		this.typeOfCard = typeOfCard;
	}

	public void addChoice(String choice) {
		this.choices.add(choice);
	}

	public void setMcAns(char mcAns) {
		this.mcAns = mcAns;
	}

	public void setTfAns(boolean tfAns) {
		this.tfAns = tfAns;
	}

	public boolean equals(Card card) {
		if (this.id == card.id) {
			return true;
		} else {
			return false;
		}
	}

	public String toString() {
		String out = "" + id + ", " + typeOfCard;
		return out;
	}

}
