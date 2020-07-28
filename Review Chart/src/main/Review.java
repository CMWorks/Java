package main;

public class Review {
	String name, date, text;
	int score;
	
	public Review() {
		this.name = "";
		this.score = -1;
		this.date = "0/0/0";
		this.text = "";
	}
	
	public Review(String name, int score, String date, String text) {
		this.name = name;
		this.score = score;
		this.date = date;
		this.text = text;
	}
	
	public String toString() {
		return "";
	}

}
