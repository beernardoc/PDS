package lab01.classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import lab01.WSS.validators.WSSMatrixValidator;

public class LetterSoupGenerator extends LetterSoupModifier {
    LetterSoup letterSoup;

    public LetterSoupGenerator(LetterSoup letterSoup) {
        this.letterSoup = letterSoup;
    }
    
    public LetterSoup generate(){
        int puzzleSize = letterSoup.getSize();
        List<String> words = letterSoup.getWords();
        Collections.sort(words, Comparator.comparingInt(String::length));
        List<String> insertedWords = new ArrayList<>();
        char[][] currentMatrix = letterSoup.getMatrix();

        Random rand = new Random(); 

        for (String word: words) {
            int wordSize= word.length();
            boolean done = false;

            while (done == false){
                int randomLine = rand.nextInt(puzzleSize); 
                int randomColumn = rand.nextInt(puzzleSize);
                int randomOrder = rand.nextInt(7);
                
                switch(randomOrder) {
                    case 0: 
                        if (isRightPossible(puzzleSize, wordSize, randomColumn) && checkRightEmpty(word, randomLine,randomColumn, currentMatrix)){
                            //System.out.println(word +" "+ "RIGHT");
                            done = true;
                            currentMatrix = insertIntoMatrix(currentMatrix,  direction.Right, word, randomLine,randomColumn);
                            insertedWords.add(word);
                        }
                        break;
                    case 1:
                        if (isDownPossible(puzzleSize, wordSize, randomLine) && checkDownEmpty(word, randomLine,randomColumn, currentMatrix)){
                            //System.out.println(word +" "+ "DOWN");
                            done = true;
                            currentMatrix = insertIntoMatrix(currentMatrix,  direction.Down, word, randomLine,randomColumn);
                            insertedWords.add(word);
                        }
                        break;
                    case 2:
                        if(isLeftPossible(puzzleSize, wordSize, randomColumn) && checkLeftEmpty(word, randomLine,randomColumn, currentMatrix)){
                            //System.out.println(word +" "+ "LEFT");
                            done = true;
                            currentMatrix = insertIntoMatrix(currentMatrix,  direction.Left, word, randomLine,randomColumn);
                            insertedWords.add(word);
                        }
                        break;
                    case 3: 
                        if(isUpPossible(puzzleSize, wordSize, randomLine) && checkUpEmpty(word, randomLine,randomColumn, currentMatrix)){
                            //System.out.println(word +" "+ "UP");
                            done = true;
                            currentMatrix = insertIntoMatrix(currentMatrix,  direction.Up, word, randomLine,randomColumn);
                            insertedWords.add(word);
                        } 
                        break;
                    case 4: 
                        if (isUpLeftPossible(puzzleSize, wordSize, randomLine, randomColumn) && checkUpLeftEmpty(word, randomLine,randomColumn, currentMatrix)) {
                            //System.out.println(word +" "+ "UL");
                            done = true;
                            currentMatrix = insertIntoMatrix(currentMatrix,  direction.UpLeft, word, randomLine,randomColumn);
                            insertedWords.add(word);
                        }
                        break;
                    case 5: 
                        if (isUpRightPossible(puzzleSize, wordSize, randomLine, randomColumn) && checkUpRightEmpty(word, randomLine,randomColumn, currentMatrix)) {
                            //System.out.println(word +" "+ "UPRIGHT");
                            done = true;
                            currentMatrix = insertIntoMatrix(currentMatrix,  direction.UpRight, word, randomLine,randomColumn);
                            insertedWords.add(word);
                        }
                        break;
                    case 6: 
                        if (isDownRightPossible(puzzleSize, wordSize, randomLine, randomColumn) && checkDownRightEmpty(word, randomLine,randomColumn, currentMatrix)) {
                            //System.out.println(word +" "+ "DOWNRIGHT");
                            done = true;
                            currentMatrix = insertIntoMatrix(currentMatrix,  direction.DownRight, word, randomLine,randomColumn);
                            insertedWords.add(word);
                        }
                        break;
                    case 7: 
                        if (isDownLeftPossible(puzzleSize, wordSize, randomLine, randomColumn) && checkDownLeftEmpty(word, randomLine,randomColumn, currentMatrix)) {
                            //System.out.println(word +" "+ "DOWNLEFT");
                            done = true;
                            currentMatrix = insertIntoMatrix(currentMatrix,  direction.DownLeft, word, randomLine,randomColumn);
                            insertedWords.add(word);
                        }
                        break;
                }
            }
        }

        //currentMatrix = fillMatrix(currentMatrix, puzzleSize);
        //if (currentMatrix == null){
        //    System.out.println("A matriz não cumpre com os requisitos. Resolva os problemas enumerados em cima e volte a tentar. \n");
        //}

        return new LetterSoup(currentMatrix, insertedWords, puzzleSize);
    }
   
    private char[][] fillMatrix(char[][] currentMatrix, int size){
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random random = new Random();
		
        for(int i = 0; i < size; i++){
            for (int k = 0; k < size; k++){
                if (currentMatrix[i][k] == '.'){
                    char c = chars.charAt(random.nextInt(chars.length()));    
                    currentMatrix[i][k] = c;
                }
                
            } 
        }
        String[] stringMatrix = new String[size];
        for (int i = 0; i< size; i++){
            stringMatrix[i] = String.valueOf(currentMatrix[i]);
        }
        WSSMatrixValidator validator = new WSSMatrixValidator(stringMatrix);
        if (!validator.isValid()){
            return null;
        }
        return currentMatrix;
    }
    private char[][] insertIntoMatrix(char[][] currentMatrix, direction direction, String word, int lineNumber,int colNumber){
        int wordSize = word.length();
        int j = 0;
        int z = 0;
        switch (direction){
            case Up:
                j=0;
                for ( int i = lineNumber; i > lineNumber - wordSize; i-- ) {
                    if (j < wordSize) {
                        currentMatrix[i][colNumber] = word.toUpperCase().charAt(j++) ;
                    }
                }
                break;
            case Down:
                j=0;
                for ( int i = lineNumber; i < lineNumber + wordSize; i++ ) {
                    if (j < wordSize) {
                        currentMatrix[i][colNumber] = word.toUpperCase().charAt(j++) ;
                    }
                }
                break;
            case Right:
                j = 0;
                for ( int i = colNumber; i < colNumber + wordSize; i++ ) {
                    if (j < wordSize) {
                        currentMatrix[lineNumber][i] = word.toUpperCase().charAt(j++) ;
                    }
                }
                break;
            case Left:
                j = 0;
                for (int i = colNumber; i > colNumber - wordSize; i--) {
                    if (j < wordSize) {
                        currentMatrix[lineNumber][i] = word.toUpperCase().charAt(j++) ;
                    }
                }
                break;
            case UpRight:
                j=0;
                z = colNumber;
                for (int i = lineNumber; i> lineNumber - wordSize; i--) {
                    if (j < wordSize && z < colNumber + wordSize ) {
                        currentMatrix[i][z] = word.toUpperCase().charAt(j++);
                    }
                    z++;
                }
                break;
            case DownRight:
                j=0;
                z = colNumber;
                for (int i = lineNumber; i< lineNumber + wordSize; i++) {
                    if (j < wordSize && z < colNumber + wordSize ) {
                        currentMatrix[i][z] = word.toUpperCase().charAt(j++);
                    }
                    z++;
                }
                break;
            case UpLeft:
                j=0;
                z = colNumber;
                for (int i = lineNumber; i > lineNumber - wordSize; i--) {
                    if (j < wordSize && z > colNumber - wordSize) {
                        currentMatrix[i][z] = word.toUpperCase().charAt(j++);
                    }
                    z--;
                }
                break;
            case DownLeft:
                j=0;
                z = colNumber;
                for (int i = lineNumber;i < lineNumber + wordSize;i++) {
                    if (j < wordSize && z > colNumber- wordSize) {
                        currentMatrix[i][z] = word.toUpperCase().charAt(j++);
                    }
                    z--;
                }
                break;    
        }

        return currentMatrix;
    }
    	
    public boolean checkRightEmpty(String word, int lineNumber,int colNumber,char[][] matrix) {
		boolean check = true;
        int wordSize = word.length();
		int j=0;
			for (int i = colNumber;i < colNumber + wordSize;i++) {
				if (j < wordSize) {
					if ( matrix[lineNumber][i] != '.' && matrix[lineNumber][i] != word.toUpperCase().charAt(j) ) {
						check = false;
						return check;
					}
                    j++;
				}
			}
			return check;
	}
	public boolean checkLeftEmpty(String word, int lineNumber,int colNumber,char[][] matrix) {
		boolean check = true;
        int wordSize = word.length();	
		int j=0;
			for (int i = colNumber; i > colNumber - wordSize; i--) {
				if (j < wordSize) {
					if (matrix[lineNumber][i] != '.' && word.toUpperCase().charAt(j) != matrix[lineNumber][i] ) {
						check = false;
						return check;
					}
                    j++;
				}
			}
			return check;
	}
	public boolean checkDownEmpty(String word, int lineNumber,int colNumber,char[][] matrix) {
		boolean check = true;
        int wordSize = word.length();
		int j=0;
			for (int i = lineNumber;i<lineNumber + wordSize;i++) {
				if (j < wordSize) {
					if (matrix[lineNumber][i] != '.' && word.toUpperCase().charAt(j) != matrix[i][colNumber] ) {
						check = false;
						return check;
					}
                    j++;
				}
			}
			return check;
	}
	public boolean checkUpEmpty(String word, int lineNumber,int colNumber,char[][] matrix) {
		boolean check = true;
        int wordSize = word.length();
		int j=0;
			for (int i = lineNumber;i>lineNumber - wordSize;i--) {
				if (j < wordSize) {
					if (matrix[lineNumber][i] != '.' && word.toUpperCase().charAt(j) != matrix[i][colNumber] ) {
						check = false;
						return check;
					}
                    j++;
				}
			}
			return check;
	}
	public boolean checkDownLeftEmpty(String word, int lineNumber,int colNumber,char[][] matrix) {
		boolean check = true;
        int wordSize = word.length();
		int j=0;
		int z = colNumber;
			for (int i = lineNumber;i< lineNumber + wordSize;i++) {
				if (j < word.length() && z>colNumber- wordSize) {
					if (matrix[lineNumber][i] != '.' && word.toUpperCase().charAt(j) != matrix[i][z] ) {
						check = false;
						return check;
					}
                    j++;
				}
				z--;
			}
			return check;
	}
	public boolean checkUpLeftEmpty(String word, int lineNumber,int colNumber,char[][] matrix) {
		boolean check = true;
        int wordSize = word.length();
		int j=0;
		int z = colNumber;
			for (int i = lineNumber; i> lineNumber - wordSize; i--) {
				if (j < word.length() && z>colNumber- wordSize) {
					if (matrix[lineNumber][i] != '.' && word.toUpperCase().charAt(j) != matrix[i][z] ) {
						check = false;
						return check;
					}
                    j++;
				}
				z--;
			}
			return check;
	}
	public boolean checkDownRightEmpty(String word, int lineNumber,int colNumber,char[][] matrix) {
		boolean check = true;
        int wordSize = word.length();
		int j=0;
		int z = colNumber;
			for (int i = lineNumber; i< lineNumber + wordSize; i++) {
				if (j < word.length() && z<colNumber + wordSize ) {
					if (matrix[lineNumber][i] != '.' && word.toUpperCase().charAt(j) != matrix[i][z] ) {
						check = false;
						return check;
					}
                    j++;
				}
				z++;
			}
			return check;
	}
	public boolean checkUpRightEmpty(String word, int lineNumber,int colNumber,char[][] matrix) {
		boolean check = true;
        int wordSize = word.length();
		int j=0;
		int z = colNumber;
			for (int i = lineNumber; i> lineNumber - wordSize; i--) {
				if (j < wordSize && z<colNumber + word.length() ) {
					if (matrix[lineNumber][i] != '.' && word.toUpperCase().charAt(j) != matrix[i][z] ) {
						check = false;
						return check;
					}
                    j++;
				}
				z++;
			}
			return check;
	}	

}
