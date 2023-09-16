package lab01.WSS;

public class WSSElements {
    final String[] matrix;
    final String[] words;
    final int size;
    
    public WSSElements(String[] matrix, String[] words, int size) {
        this.matrix = matrix;
        this.words = words;
        this.size = size;
    }

    public String[] getMatrix() {
        return matrix;
    }

    public String[] getWords() {
        return words;
    }

    public int getSize() {
        return size;
    }
}
