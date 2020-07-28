package main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PageReader {
	Deque<Review> reviews = new ArrayDeque<Review>();
	
	public PageReader() {
		// TODO Auto-generated constructor stub
	}
	
	public void readePage(String path) throws IOException {
		String out = "";
		Document doc = null;
		System.out.print("getting...");
		doc = Jsoup.connect(path).get();
		int size = 0;
		Elements tags = doc.getElementsByTag("li");
		System.out.print("parsing...");
		for (Element list : tags) {
			if (list.toString().length() > 11 && list.toString().substring(0,12).equals("<li id=\"user")) {
				//is a review
				String body = list.text();
				
				int end = body.indexOf(" ");
				String name = body.substring(0,end);
				body = body.substring(end+1);
				end = body.indexOf(", 20")+6;
				String date = body.substring(0,end);
				body = body.substring(end+1);
				end = body.indexOf(" ");
				int score = Integer.parseInt(body.substring(0,end));
				body = body.substring(end+1);
				out += "\""+name+"\",\""+date+"\","+score+","+body.length()+",\""+body+"\"\n";
			}
		}
		System.out.println("writing...");
		try (FileWriter writer = new FileWriter("reviewLOU2_with_text.csv",true)){
			writer.write(out);
		} catch (Exception e) {
			System.out.println("crash");
		}
	}
}
