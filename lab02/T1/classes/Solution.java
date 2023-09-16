package lab01.classes;

public class Solution {
        int order;
        String word;
        int wordSize;
        int line;
        int column;
        direction direction;
        public Solution(int order, String word, int wordSize, int line, int column, lab01.classes.direction direction) {
            this.order = order;
            this.word = word;
            this.wordSize = wordSize;
            this.line = line;
            this.column = column;
            this.direction = direction;
        }
        public int getOrder() {
            return order;
        }
        public String getWord() {
            return word;
        }
        public int getWordSize() {
            return wordSize;
        }
        public int getLine() {
            return line;
        }
        public int getColumn() {
            return column;
        }
        public direction getDirection() {
            return direction;
        }
        
        public String print() {
            return word + "\t\t" + wordSize + "\t" + (line+1) + "," + (column+1) + "\t" + direction;
        }
        



        
}
