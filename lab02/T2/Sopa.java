import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Sopa {
	private int size;
	private char[][] matrix;
	private ArrayList<String> words;
    private char[][] solved_matrix;

	public Sopa(String file) {
		
		try {
			Scanner sc = new Scanner(new File(file));
			// read 1st string
			String line = sc.nextLine();
			// number of columns and rows (matrix NxN)
			this.size = line.length();
			if(size>40){
				System.err.println("Puzzle can't be bigger than 40x40");
				System.exit(1);
			}
			//create matrix
			this.matrix = new char[size][size];
            //matrix to be filled with only the found words
            this.solved_matrix = new char[size][size];

            //fill solved_matrix with '.' 
            for (int row = 0; row < size; row++) {
				for (int col = 0; col < size; col++) {
					solved_matrix[row][col] = '.';
				}
			}

			//fill matrix char by char
			for (int row = 0; row < size; row++) {
				for (int col = 0; col < size; col++) {
					if(line.isEmpty()){
						System.err.println("Puzzle can't have empty lines");
						System.exit(1);
					}
                    char c = line.charAt(col);
					if(c < 'A' || c > 'Z'){
						System.err.println("Puzzle characters need to be uppercase letters");
						System.exit(1);
					}
					matrix[row][col] = c;
				}
				if(row<(size-1)) {
					line = sc.nextLine();	
				}
			}

			this.words = new ArrayList<String>();

			//get words to be found
			while (sc.hasNextLine()) {
				line = sc.nextLine();
                if(line.isEmpty()){
						System.err.println("Word list can't have empty lines");
						System.exit(1);
				}
				String[] list = line.split("\\W+");
				for (int i = 0; i < list.length; i++) {
					if((list[i].matches("^[a-zA-Z]*$")) && list[i].length()>=3){
					words.add(list[i].toUpperCase());
					}
				}
			}

			sc.close();

		} catch (Exception e) {
			System.err.format("Exception occurred trying to read '%s'.", file);
			e.printStackTrace();
			return;
		}
	}

	public int getSize() {
		return size;
	}

	public char[][] getMatrix() {
		return matrix;
	}

    public char[][] getSolvedMatrix() {
		return solved_matrix;
	}

	public ArrayList<String> getWords() {
		return words;
	}
	
	public static ArrayList<String> reverse(ArrayList<String> arr) {
		ArrayList<String> reversed = new ArrayList<String>();
		for (String s : arr) {
			reversed.add(new StringBuilder(s).reverse().toString());
		}
		return reversed;
	}

	private char[][] reverseLines(){
		char[][] matrix_r = new char[size][size];

		for(int row = 0; row<size; row++){
			String row_r = new StringBuilder(String.valueOf(matrix[row])).reverse().toString();
			matrix_r[row] = row_r.toCharArray();
		}
		return matrix_r;
	}

	private ArrayList<String> getDiagonal(char[][] mat){
		//Default UP RIGHT diagonal
		ArrayList<String> diags = new ArrayList<String>();

        //get first half
        int row =0;
        int col;

        while(row<mat.length){
            col = 0;
			int rowTemp = row;
			String d = "";
            while(rowTemp>=0){
				d = d + mat[rowTemp][col];
                rowTemp--;
                col++;
			}
			diags.add(d);
            row++;
        }

        //get second half
        col = 1;

        while(col<mat.length){
            int colTemp = col;
			row = mat.length-1;
			String d = "";
            while(colTemp<=mat.length-1){
				d = d + mat[row][colTemp];
                row--;
                colTemp++;
            }
			diags.add(d);
            col++;
		}
		return diags;
    }

	public ArrayList<String> getHorizontals() {
		ArrayList<String> horizontais = new ArrayList<String>();
		for (int i = 0; i < matrix.length; i++) {
			horizontais.add(String.valueOf(matrix[i]));
		}
		return horizontais;
	}

	public ArrayList<String> getVerticals() {
		ArrayList<String> verticais = new ArrayList<String>();
		for (int row = 0; row < matrix.length; row++) {
			String v = "";
			for (int col = 0; col < matrix.length; col++) {
				v = v + matrix[col][row];
			}
			verticais.add(v);
		}
		return verticais;
	}

	public ArrayList<String> getDiagonal_UR() {
		ArrayList<String> diags = getDiagonal(matrix);
		return diags;
	}

	public ArrayList<String> getDiagonal_UL() {
		ArrayList<String> diags = getDiagonal(reverseLines());
		return diags;
	}
	
}