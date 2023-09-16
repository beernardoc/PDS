package lab01.classes;

import java.util.Arrays;
import java.util.List;

public class LetterSoup {
    private char[][] matrix;
    private final List<String> words;
    private final int size;
    
    public LetterSoup(char[][] matrix, List<String> words, int size) {
        this.matrix = matrix;
        this.words = words;
        this.size = size;
    }
    public char[][] getMatrix() {
        return matrix;
    }
    public List<String> getWords() {
        return words;
    }
    public int getSize() {
        return size;
    }
    public void setMatrix(char[][] matrix) {
        this.matrix = matrix;
    }
    @Override
    public String toString() {
        String str = "";
        return "LetterSoup [matrix=" + Arrays.toString(matrix) + ", words=" + words + ", size=" + size + "]";
    }
}
