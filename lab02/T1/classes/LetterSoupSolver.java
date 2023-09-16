package lab01.classes;

import java.util.ArrayList;
import java.util.List;

public class LetterSoupSolver extends LetterSoupModifier {
    LetterSoup letterSoup;

    public LetterSoupSolver(LetterSoup letterSoup) {
        this.letterSoup = letterSoup;
    }

    public LetterSoup getLetterSoup() {
		return letterSoup;
	}

	public LetterSoupSolution getSolution(){
        char[] firstLetters = getFirstLetters();
        char[][] matrix = letterSoup.getMatrix();
        List<String> words = letterSoup.getWords();
        int size = letterSoup.getSize();

        List<String> foundWords = new ArrayList<>();
        List<Solution> solutionList = new ArrayList<>();

        for (int lineNumber = 0; lineNumber < size; lineNumber++) {
            for (int colNumber = 0; colNumber < size; colNumber++) {
                char letter = matrix[lineNumber][colNumber];
                List<Integer> indexFL = isFirstLetterOf(firstLetters, letter);

                if (indexFL != null) {
                    for (Integer position : indexFL) {
                        String word = words.get(position);
                        if (!foundWords.contains(word)){
                            Solution foundWord = checkForWord(word,size,word.length(),colNumber,lineNumber,matrix,position);

                            if (foundWord != null){
                                solutionList.add(foundWord);
                                foundWords.add(foundWord.getWord());
                            }
                        }
                    }
                }

            }
        }

        return new LetterSoupSolution(this.getLetterSoup(), solutionList);
    }
	
    private char[] getFirstLetters(){
        char[] firstLetters = new char[letterSoup.getWords().size()];
        for (int i = 0; i < letterSoup.getWords().size(); i++){
            firstLetters[i] = letterSoup.getWords().get(i).toUpperCase().charAt(0);
        }

        return firstLetters;
    }

    private List<Integer> isFirstLetterOf(char[] firstLetters, char letter){
        final List<Integer> indexList = new ArrayList<>();
        for (int i = 0; i < firstLetters.length; i++) {
            if (letter == firstLetters[i]) {
                indexList.add(i);
            }
        }
        if (indexList.size() == 0 ){
            return null;
        }
        return indexList;
    }
    
    private Solution checkForWord(String word, int size, int wordSize, int colNumber, int lineNumber, char[][] matrix, int position){
        if (isRightPossible(size, wordSize, colNumber) && checkRight(word, lineNumber, colNumber, matrix)){
            return new Solution(position, word,wordSize,lineNumber,colNumber,direction.Right);
        }
        if (isLeftPossible(size, wordSize, colNumber) && checkLeft(word, lineNumber, colNumber, matrix)){
            return new Solution(position, word,wordSize,lineNumber,colNumber,direction.Left);
        }
        if (isDownPossible(size, wordSize, lineNumber) && checkDown(word, lineNumber, colNumber, matrix)){
            return new Solution(position, word,wordSize,lineNumber,colNumber,direction.Down);
        }
        if (isUpPossible(size, wordSize, lineNumber) && checkUp(word, lineNumber, colNumber, matrix)){
            return new Solution(position, word,wordSize,lineNumber,colNumber,direction.Up);
        }
        if (isUpRightPossible(size, wordSize,lineNumber, colNumber) && checkUpRight(word, lineNumber, colNumber, matrix)){
            return new Solution(position, word,wordSize,lineNumber,colNumber,direction.UpRight);
        }
        if (isDownRightPossible(size, wordSize,lineNumber, colNumber) && checkDownRight(word, lineNumber, colNumber, matrix)){
            return new Solution(position, word,wordSize,lineNumber,colNumber,direction.DownRight);
        }
        if (isUpLeftPossible(size, wordSize,lineNumber, colNumber) && checkUpLeft(word, lineNumber, colNumber, matrix)){
            return new Solution(position, word,wordSize,lineNumber,colNumber,direction.UpLeft);
        }
        if (isDownLeftPossible(size, wordSize, lineNumber,colNumber) && checkDownLeft(word, lineNumber, colNumber, matrix)){
            return new Solution(position, word,wordSize,lineNumber,colNumber,direction.DownLeft);
        }
        return null;
    }
    
    
}
