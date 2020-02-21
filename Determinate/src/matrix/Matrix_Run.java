package matrix;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Matrix_Run {

	public static void main(String[] args) {

		String[][] outrix = readFile();

		try (PrintWriter writer = new PrintWriter(new FileWriter("out.txt"))) {
			PrintList(outrix);
			String[] out = Determinate.calculate2(outrix);
			for (String string : out) {
				System.out.println(string);
			}
			//writer.print(Determinate.calculate(outrix));
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("Done");
	}

	private static String[][] readFile() {
		try (Scanner scan = new Scanner(new File("m1.csv"))) {
			String line = scan.nextLine();
			int n = 0;
			for (int i = 0; i < line.length(); i++) {
				if (line.charAt(i) == ',') {
					n++;
				}
			}
			String[][] out = new String[n + 1][n + 1];
			for (int y = 0; y < out.length; y++) {
				for (int x = 0; x < out[y].length; x++) {
					int index = line.indexOf(',');
					if (index == -1) {
						index = line.length();
					}
					String value = line.substring(0, index);
					if (value.equals("")) {
						value = null;
					}
					out[y][x] = value;
					line = line.substring(line.indexOf(',') + 1);
				}
				if (scan.hasNextLine()) {
					line = scan.nextLine();
				}
			}
			return out;
		} catch (Exception e) {
			return null;
		}
	}

	private static void PrintList(String[][] list) {
		String pout = list.length+"x"+list.length+"\n";
		for (int y = 0; y < list.length; y++) {
			for (int x = 0; x < list[y].length; x++) {
				pout += list[y][x] + ", ";
			}
			pout = pout.substring(0, pout.length() - 2);
			pout += "\n";
		}
		System.out.println(pout);
	}
}
