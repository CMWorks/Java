package matrix;

public class Determinate {

	public static String calculate(String[][] mat) {
		boolean alternate = true;
		if (mat.length == 1) {
			return mat[0][0];
		} else {
			String out = "";
			for (int i = 0; i < mat[0].length; i++) {
				String[][] newMat = new String[mat.length - 1][mat[0].length - 1];
				int xPos = 0;
				int yPos = 0;
				for (int y = 1; y < mat.length; y++) {
					for (int x = 0; x < mat[y].length; x++) {
						if (x != i) {
							newMat[yPos][xPos] = mat[y][x];
							xPos++;
						}
					}
					yPos++;
					xPos = 0;
				}
				/*	Note:	*/
				/*	have a space before and after the () if you want to format it into word	*/
				out += mat[0][i] + "(" + calculate(newMat) + ")";
				alternate = !alternate;
				if (alternate)
					out += "+";
				else
					out += "-";
			}
			out = out.substring(0, out.length()-1);
			return out;

		}
	}
	
	public static String[] calculate2(String[][] mat) {
		String[] degrees = new String[mat[0].length];
		boolean alternate;
		for (int i = 0; i < degrees.length; i++) {
			alternate = false;
			degrees[i] = "";
			for (int j = 0; j < mat[1].length; j++) {
				if (j!=i) {
					degrees[i]+=mat[1][j]+"*det(";
					
					//finds the matrix for 1,j
					for (int y = 2; y < mat.length; y++) {
						for (int x = 0; x < mat[y].length; x++) {
							if (x != i && x!=j) {
								degrees[i] += mat[y][x]+",";
							}
						}
					}
					degrees[i] = degrees[i].substring(0,degrees[i].length()-1)+")";
					if (alternate) {
						degrees[i] += "+";
					}else {
						degrees[i] += "-";
					}
					alternate = !alternate;
				}
			}
			degrees[i] = degrees[i].substring(0,degrees[i].length()-1);
		}
		return degrees;
	}

//	private static void printOut(String[][] mat) {
//		for (int i = 0; i < mat.length; i++) {
//			for (int j = 0; j < mat[i].length; j++) {
//				System.out.print(mat[i][j] + ", ");
//			}
//			System.out.print("\n");
//		}
//	}
}
