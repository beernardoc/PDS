
import java.util.ArrayList;

public class WSSolver {

	public static void main(String[] args) {
        if (args.length != 1) {
                System.err.println("Wrong number of arguments; Name one text file");
                System.exit(1);
            }

        String file = null;
        file = args[0]; 
        

		//Letter soup Object creation
		Sopa sopa = new Sopa(file);
		//Array of words to find
		ArrayList<String> words = sopa.getWords();
		//Array to fill with found words
		ArrayList<Word> found_words = new ArrayList<Word>();
        //Sopa de letras
        char[][] matrix = sopa.getMatrix();
        //Matriz to fill only with found words
        char[][] solved_matrix = sopa.getSolvedMatrix();
		
		//Arrays that contain matrix lines of each direction
		ArrayList<String> horz = sopa.getHorizontals();
		ArrayList<String> vert = sopa.getVerticals();
		ArrayList<String> horz_r = Sopa.reverse(horz);
		ArrayList<String> vert_r = Sopa.reverse(vert);
		ArrayList<String> diag_ur = sopa.getDiagonal_UR();
		ArrayList<String> diag_ul = sopa.getDiagonal_UL();
		ArrayList<String> diag_dl = Sopa.reverse(diag_ur);
		ArrayList<String> diag_dr = Sopa.reverse(diag_ul);
		
		//main programm structure, loops through word list and for each one goes through each direction array
		//checking if the word is present in said array. When the word is found in a line of an array, it finds
		//the row and column of the first letter of the word in the matrix. The method varies for each direction.
		//Afterwards a Word object is created with the word info and added to found_words array.
		loop:
		for(String word : words) {
			for(String s : horz) {
				if(s.contains(word)) {
					int col = s.indexOf(word);
					int row = horz.indexOf(s);
					Word w = new Word(word,word.length(),row + 1,col + 1,"right");
					/*for (Word fw : found_words) {
						if (fw.getText() == w.getText()) {
							System.err.println("The word " + word + " appears more than 1 time in the puzzle!");
							System.exit(1);
						}
					}*/
					found_words.add(w);
					for(int i = 0; i < word.length(); i++)
						solved_matrix[row][col + i] = matrix[row][col + i];
					
                }
			}
			for(String s : vert) {
				if(s.contains(word)) {
					int row = s.indexOf(word);
					int col = vert.indexOf(s);
					Word w = new Word(word,word.length(),row + 1,col + 1,"down");
					/*for (Word fw : found_words) {
						if (fw.getText() == w.getText()) {
							System.err.println("The word " + word + " appears more than 1 time in the puzzle!");
							System.exit(1);
						}
					}*/
					found_words.add(w);
					for(int i = 0; i < word.length(); i++)
					solved_matrix[row + i][col] = matrix[row + i][col];
				
				}
			}
			for(String s : horz_r) {
				if(s.contains(word)) {
					int col = sopa.getSize()-(s.indexOf(word));
					int row = horz_r.indexOf(s);
					Word w = new Word(word,word.length(),row,col + 1,"left");
					/*for (Word fw : found_words) {
						if (fw.getText() == w.getText()) {
							System.err.println("The word " + word + " appears more than 1 time in the puzzle!");
							System.exit(1);
						}
					}*/
					found_words.add(w);
					for(int i = 1; i < word.length() + 1; i++)
					solved_matrix[row][col - i] = matrix[row][col - i];
					
				}
			}
			for(String s : vert_r) {
				if(s.contains(word)) {
					int row = sopa.getSize()-s.indexOf(word);
					int col = vert_r.indexOf(s);
					Word w = new Word(word,word.length(),row,col + 1,"up");
					/*for (Word fw : found_words) {
						if (fw.getText() == w.getText()) {
							System.err.println("The word " + word + " appears more than 1 time in the puzzle!");
							System.exit(1);
						}
					}*/
					found_words.add(w);
					for(int i = 1; i < word.length() + 1; i++)
					solved_matrix[row - i][col] = matrix[row - i][col];
					
				}
			}
			for(String s : diag_dr) {
				if(s.contains(word)) {
					int idx = diag_dr.indexOf(s)+1;	//index of diagonal in the array starting at 1
					int drow = idx>sopa.getSize() ? idx-sopa.getSize()+1 : 1;	//row of diagonal start
					int dcol = idx>sopa.getSize() ? 1 : sopa.getSize()-idx+1;	//column of diagonal start
					int row = drow+s.indexOf(word); int col = dcol+s.indexOf(word); //row and column of word
					Word w = new Word(word,word.length(),row,col,"downright");
					/*for (Word fw : found_words) {
						if (fw.getText() == w.getText()) {
							System.err.println("The word " + word + " appears more than 1 time in the puzzle!");
							System.exit(1);
						}
					}*/
					found_words.add(w);
					for(int i = 0; i < word.length(); i++)
					solved_matrix[row - 1 + i][col - 1 + i] = matrix[row - 1 + i][col - 1 + i];
					
				}
			}
			for(String s : diag_dl) {
				if(s.contains(word)) {
					int idx = diag_dl.indexOf(s)+1;	//index of diagonal in the array starting at 1
					int drow = idx>sopa.getSize() ? idx-sopa.getSize()+1 : 1;	//row of diagonal start
					int dcol = idx>sopa.getSize() ? sopa.getSize() : idx;	//column of diagonal start
					int row = drow+s.indexOf(word); int col = dcol-s.indexOf(word); //row and column of word
					Word w = new Word(word,word.length(),row,col,"downleft");
					/*for (Word fw : found_words) {
						if (fw.getText() == w.getText()) {
							System.err.println("The word " + word + " appears more than 1 time in the puzzle!");
							System.exit(1);
						}
					}*/
					found_words.add(w);
					for(int i = 0; i < word.length(); i++)
					solved_matrix[row - 1 + i][col - 1 - i] = matrix[row - 1 + i][col - 1 - i];
					
				}
			}
			for(String s : diag_ul) {
				if(s.contains(word)) {
					int idx = diag_ul.indexOf(s)+1;	//index of diagonal in the array starting at 1
					int drow = idx>sopa.getSize() ? sopa.getSize() : idx;	//row of diagonal start
					int dcol = idx>sopa.getSize() ? (2*sopa.getSize())-idx : sopa.getSize();	//column of diagonal start
					int row = drow-s.indexOf(word); int col = dcol-s.indexOf(word); //row and column of word
					Word w = new Word(word,word.length(),row,col,"upleft");
					/*for (Word fw : found_words) {
						if (fw.getText() == w.getText()) {
							System.err.println("The word " + word + " appears more than 1 time in the puzzle!");
							System.exit(1);
						}
					}*/
					found_words.add(w);
					for(int i = 1; i < word.length() + 1; i++)
					solved_matrix[row - i][col - i] = matrix[row - i][col - i];
					
				}
			}
			for(String s : diag_ur) {
				if(s.contains(word)) {
					int idx = diag_ur.indexOf(s)+1;	//index of diagonal in the array starting at 1
					int drow = idx>sopa.getSize() ? sopa.getSize() : idx;	//row of diagonal start
					int dcol = idx>sopa.getSize() ? idx-sopa.getSize()+1 : 1;	//column of diagonal start
					int row = drow-s.indexOf(word); int col = dcol+s.indexOf(word); //row and column of word
					Word w = new Word(word,word.length(),row,col,"upright");
					/*for (Word fw : found_words) {
						if (fw.getText() == w.getText()) {
							System.err.println("The word " + word + " appears more than 1 time in the puzzle!");
							System.exit(1);
						}
					}*/
					found_words.add(w);
					for(int i = 0; i < word.length(); i++)
					solved_matrix[row - 1 - i][col - 1 + i] = matrix[row - 1 - i][col - 1 + i];
					
				}
			}
		}
		

		//Print results
				
		for (int i = 0; i < found_words.size() - 1; i++ )
			for (int j = i+1; j < found_words.size(); j++ ) {
				if ( found_words.get(i).getText() == found_words.get(j).getText() ) {
					System.err.println("The word " + found_words.get(i).getText() + " appears more than 1 time in the puzzle!");
					System.exit(1);
				}
			}
		
		for (Word w : found_words) {
			System.out.println(w);
		}

		System.out.println(found_words.get(1).getText());

     
        int size = sopa.getSize();

        System.out.println();
        for (int row = 0; row < size; row++) {
				for (int col = 0; col < size; col++) {
					
					System.out.print(solved_matrix[row][col]);
				}
                if(row<(size-1)) {
                    System.out.println();	
                }
		}
		System.out.println(words);

	}

}