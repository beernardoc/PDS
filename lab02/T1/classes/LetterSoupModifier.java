package lab01.classes;

public class LetterSoupModifier {
    LetterSoup letterSoup;

    public boolean isRightPossible(int size, int wordSize, int colNumber){
        return size - colNumber >= wordSize;
    }
    public boolean isLeftPossible(int size, int wordSize, int colNumber){
        return colNumber+ 1 >= wordSize;
    }
    public boolean isDownPossible(int size, int wordSize, int lineNumber){
        return size - lineNumber >= wordSize;
    }
    public boolean isUpPossible(int size, int wordSize, int lineNumber){
        return lineNumber + 1 >= wordSize;
    }
    public boolean isUpRightPossible(int size, int wordSize, int lineNumber, int colNumber){
        return lineNumber  >= wordSize && size - colNumber >= wordSize;
    }
    public boolean isDownRightPossible(int size, int wordSize, int lineNumber, int colNumber){
        return size-lineNumber>=wordSize && size-colNumber>=wordSize;
    }
    public boolean isUpLeftPossible(int size, int wordSize,int lineNumber, int colNumber){
        return lineNumber >= wordSize && colNumber >= wordSize;
    }
    public boolean isDownLeftPossible(int size, int wordSize,int lineNumber, int colNumber){
        return size-lineNumber >= wordSize && colNumber >= wordSize;
    }
    public boolean checkRight(String word, int lineNumber,int colNumber,char[][] matrix) {
		boolean check = true;
        int wordSize = word.length();
		int j=0;
			for (int i = colNumber;i < colNumber + wordSize;i++) {
				if (j < wordSize) {
					if (word.toUpperCase().charAt(j++) != matrix[lineNumber][i] ) {
						check = false;
						return check;
					}
				}
			}
			return check;
	}
	public boolean checkLeft(String word, int lineNumber,int colNumber,char[][] matrix) {
		boolean check = true;
        int wordSize = word.length();	
		int j=0;
			for (int i = colNumber; i > colNumber - wordSize; i--) {
				if (j < wordSize) {
					if (word.toUpperCase().charAt(j++) != matrix[lineNumber][i] ) {
						check = false;
						return check;
					}
				}
			}
			return check;
	}
	public boolean checkDown(String word, int lineNumber,int colNumber,char[][] matrix) {
		boolean check = true;
        int wordSize = word.length();
		int j=0;
			for (int i = lineNumber;i<lineNumber + wordSize;i++) {
				if (j < wordSize) {
					if (word.toUpperCase().charAt(j++) != matrix[i][colNumber] ) {
						check = false;
						return check;
					}
				}
			}
			return check;
	}
	public boolean checkUp(String word, int lineNumber,int colNumber,char[][] matrix) {
		boolean check = true;
        int wordSize = word.length();
		int j=0;
			for (int i = lineNumber;i>lineNumber - wordSize;i--) {
				if (j < wordSize) {
					if (word.toUpperCase().charAt(j++) != matrix[i][colNumber] ) {
						check = false;
						return check;
					}
				}
			}
			return check;
	}
	public boolean checkDownLeft(String word, int lineNumber,int colNumber,char[][] matrix) {
		boolean check = true;
        int wordSize = word.length();
		int j=0;
		int z = colNumber;
			for (int i = lineNumber;i< lineNumber + wordSize;i++) {
				if (j < word.length() && z>colNumber- wordSize) {
					if (word.toUpperCase().charAt(j++) != matrix[i][z] ) {
						check = false;
						return check;
					}
				}
				z--;
			}
			return check;
	}
	public boolean checkUpLeft(String word, int lineNumber,int colNumber,char[][] matrix) {
		boolean check = true;
        int wordSize = word.length();
		int j=0;
		int z = colNumber;
			for (int i = lineNumber; i> lineNumber - wordSize; i--) {
				if (j < word.length() && z>colNumber- wordSize) {
					if (word.toUpperCase().charAt(j++) != matrix[i][z] ) {
						check = false;
						return check;
					}
				}
				z--;
			}
			return check;
	}
	public boolean checkDownRight(String word, int lineNumber,int colNumber,char[][] matrix) {
		boolean check = true;
        int wordSize = word.length();
		int j=0;
		int z = colNumber;
			for (int i = lineNumber; i< lineNumber + wordSize; i++) {
				if (j < word.length() && z<colNumber + wordSize ) {
					if (word.toUpperCase().charAt(j++) != matrix[i][z] ) {
						check = false;
						return check;
					}
				}
				z++;
			}
			return check;
	}
	public boolean checkUpRight(String word, int lineNumber,int colNumber,char[][] matrix) {
		boolean check = true;
        int wordSize = word.length();
		int j=0;
		int z = colNumber;
			for (int i = lineNumber; i> lineNumber - wordSize; i--) {
				if (j < wordSize && z<colNumber + word.length() ) {
					if (word.toUpperCase().charAt(j++) != matrix[i][z] ) {
						check = false;
						return check;
					}
				}
				z++;
			}
			return check;
	}	
}