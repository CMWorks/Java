package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	/*
	 * Notes Starting tag: <li id="user_review_(review id)" Name: <div class="name">
	 * <a href="(link to profile)">(actual name)</a> Date: <div class="date"> (date
	 * is below this tag) Grade: <div class="review_grade"> (some random stuff)
	 * (actual grade here) Review: <div class="review_body"> <span>(text here)
	 * 
	 */
	private static int setSize = 1178;
	private static ArrayList<Integer> list_of_size = new ArrayList<Integer>(58917);
	private static Double[] mean_set = new Double[setSize];

	public static void main(String[] args) {
//		run(0);
		countDates();
		System.out.println("DONE");
	}
	
	public static void run(int start) {
		PageReader reader = new PageReader();
		int pageNum = start;
		try {
			for (pageNum = start; pageNum < 590; pageNum++) {
				reader.readePage("https://www.metacritic.com/game/playstation-4/the-last-of-us-part-ii/user-reviews?sort-by=date&num_items=100&page="+pageNum);
				System.out.println(pageNum);
			}
		} catch (IOException e) {
			System.out.println("System time out. Restarting at "+pageNum);
			run(pageNum);
		}
	}
	
	public static void countDates() {
		int [][] dates = new int[14][11]; // [date][score]
		int [] size = new int[11]; // [score] = size
		int [] sizeGood = new int[11]; // [score] = size
		try (Scanner scan = new Scanner(new File("reviewLOU2_with_text.csv"))){
			while (scan.hasNext()) {
				String line = scan.nextLine();
				String date = line.substring(line.indexOf(",\"")+2);
				date = date.substring(0,date.indexOf(", 20")+6);
				String scoreTemp = line.substring(line.indexOf(", 20")+8,line.indexOf(", 20")+11);
				int score = Integer.parseInt(scoreTemp.substring(0,scoreTemp.lastIndexOf(",")));
				String lengthTemp = line.substring(line.indexOf(", 20")+9);
				lengthTemp = lengthTemp.substring(lengthTemp.indexOf(",")+1);
				int length = Integer.parseInt(lengthTemp.substring(0,lengthTemp.indexOf(",")));
				String text = lengthTemp.substring(lengthTemp.indexOf(",")+1);
				list_of_size.add(length);
				if (isLegitimateReview(text, length, date)) {
					sizeGood[score]++;
					
				}else {
					size[score]++;					
				}
//				System.out.println("("+date+") - ("+score+") - ("+length+")");
				
//				switch (date) {
//					case "Jun 19, 2020": dates[0][score]++;break;
//					case "Jun 20, 2020": dates[1][score]++;break;
//					case "Jun 21, 2020": dates[2][score]++;break;
//					case "Jun 22, 2020": dates[3][score]++;break;
//					case "Jun 23, 2020": dates[4][score]++;break;
//					case "Jun 24, 2020": dates[5][score]++;break;
//					case "Jun 25, 2020": dates[6][score]++;break;
//					case "Jun 26, 2020": dates[7][score]++;break;
//					case "Jun 27, 2020": dates[8][score]++;break;
//					case "Jun 28, 2020": dates[9][score]++;break;
//					case "Jun 29, 2020": dates[10][score]++;break;
//					case "Jun 30, 2020": dates[11][score]++;break;
//					case "Jul 1, 2020": dates[12][score]++;break;
//					case "Jul 2, 2020": dates[13][score]++;break;
//				
//					default:
//						System.out.println("Unexpected value: " + date);
//				}
			}
		} catch (Exception e) {
			System.out.println("\nError\n");
		}
		
//		try (FileWriter writer = new FileWriter("score by day.csv",false)){
//			for (int day = 0; day < 14; day++) {
//				writer.write("\"Jun "+(19+day)+", 2020\"");
//				for (int i = 0; i <11; i++) {
//					writer.write(","+dates[day][i]);
//				}
//				writer.write("\n");
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		for (int i = 0; i < size.length; i++) {
//			System.out.println(size[i]);
//		}
//		System.out.println("\n\nGood\n");
//		for (int i = 0; i < sizeGood.length; i++) {
//			System.out.println(sizeGood[i]);
//		}

		
		
		/*
		 * Calculating std curve
		 */
//		for (int n_size = 10; n_size < 10000; n_size*=2) {
//			String out = "n="+n_size;
//			for (int i = 0; i < n_size; i++) {
//				double xbar = 0;
//				int n = 50;
//				for (int j = 0; j < n; j++) {	// sample size of 50
//					xbar += list_of_size.get((int)(Math.random()*58917));
//				}
//				out+=","+(xbar/n);
//				
//			}
//			try (FileWriter writer = new FileWriter("plot of means.csv",true)){
//				writer.write(out+"\n");
//			} catch (Exception e) {
//				System.out.println("error for out");
//			}
//		}
		for (int i = 0; i < setSize; i++) {
			double xbar = 0;
			int n = 50;
			for (int j = 0; j < n; j++) {	// sample size of 50
				xbar += list_of_size.get((int)(Math.random()*58917));
			}
			mean_set[i] = (xbar/n);
		}
		System.out.println("mean set size = "+mean_set.length);
		Arrays.sort(mean_set);
		double mean_xbar = 0;
		double stdev = 0;
		double sum_of_dif_sq = 0;
		double q1 = mean_set[(int)(setSize/4.0)];
		double q2 = mean_set[(int)(setSize/2.0)];
		double q3 = mean_set[(int)(3*setSize/4.0)];
		double iqr = q3-q1;
		double low = q1-1.5*iqr;
		double high = q3+1.5*iqr;
		
		for (int i = 0; i < mean_set.length; i++) {
			mean_xbar += mean_set[i];
		}
		mean_xbar = mean_xbar/mean_set.length;
		for (int i = 0; i < mean_set.length; i++) {
			sum_of_dif_sq += Math.pow(mean_set[i] - mean_xbar,2);
		}
		stdev = Math.sqrt((sum_of_dif_sq)/(mean_set.length-1));
		System.out.println("xbar = "+mean_xbar);
		System.out.println("stdev = "+stdev);
		System.out.println("q1 = "+q1);
		System.out.println("q2 = "+q2);
		System.out.println("q3 = "+q3);
		System.out.println("iqr = "+iqr);
		System.out.println("low = "+low);
		System.out.println("high = "+high);
	}
	
	private static boolean isLegitimateReview(String text, int length, String date) {
		if (length < 544) {
			return false;
		}if (text.indexOf("fuck") != -1 || text.indexOf("shit") != -1 || text.indexOf("bitch") != -1 || text.indexOf("cuck") != -1 || text.indexOf("nigga") != -1 || text.indexOf("????????") != -1) {
			return false;
		}if (date.equals("Jun 19, 2020")) {
			return false;
		}
		return true;
	}

}
