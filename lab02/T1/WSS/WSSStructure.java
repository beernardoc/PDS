package lab01.WSS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lab01.WSS.validators.WSSMatrixValidator;
import lab01.WSS.validators.WSSWordsValidator;
import lab01.classes.LetterSoup;

public class WSSStructure {
    String docContent;
    int size;

    public WSSStructure(String docContent) {
        this.docContent = docContent;
    }

    private WSSElements processDocument(){
        String[] lines = docContent.split("\n");
        int sizeH = lines[0].strip().length();
        this.size = sizeH;
        int sizeV = lines.length;
        String[] matrix = Arrays.copyOfRange(lines, 0, sizeH);
        String[] words = Arrays.copyOfRange(lines, sizeH , sizeV);

        return new WSSElements(matrix, words, sizeH);
    }
    
    private LetterSoup processElements(WSSElements elements){
        WSSMatrixValidator matrixValidator = new WSSMatrixValidator(elements.getMatrix());
        WSSWordsValidator wordsValidator = new WSSWordsValidator();

        if (matrixValidator.isValid()){
            char[][] matrix = generateMatrix(elements.getMatrix());
            List<String> words = generateWords(elements.getWords());
            boolean validWords = true;
            
            for (int w = 0; w < words.size(); w++){
                if (!wordsValidator.isValid(words.get(w),this.size)){
                    validWords = false;
                }
            }
            if (!validWords){
                System.out.print("As palavras não cumprem com os requisitos. Resolva os problemas enumerados em cima e volte a tentar.\n");
            }else{
                return new LetterSoup(matrix, words, this.size);
            }
        }else {
            System.out.print("A Sopa de letras não cumpre com os requisitos. Resolva os problemas enumerados em cima e volte a tentar.\n");
        }
        return null;
        
    }

    private List<String> generateWords(String[] wordsUnpr) {
        int size = wordsUnpr.length;
        List<String> words = new ArrayList<String>();
        for (int i = 0; i < size; i++) {
            String [] tmp = wordsUnpr[i].split("[ ,;]");
            for (String s : tmp){
                words.add(s);
            }
        }
        return words;
    }

    private char[][] generateMatrix(String[] matrixString) {
        char[][] matrix = new char[this.size][this.size];

        for (int i = 0; i < this.size; i++) {
            char[] line = ((String) matrixString[i]).strip().toCharArray();
            
            for (int x = 0; x < this.size; x++) {
                matrix[i][x] = line[x];
            }
        }
        return matrix;
    }

    public LetterSoup generateLetterSoup(){
        WSSElements elements =  processDocument();
        LetterSoup letterSoup = processElements(elements);
        return letterSoup;
    }
}
