package lab01.WSS;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import lab01.WSS.validators.WSSMatrixValidator;
import lab01.WSS.validators.WSSWordsValidator;
import lab01.classes.LetterSoup;

public class WSG {
    String[] args;

    public WSG(String[] args) {
        this.args = args;
    }
    
    public LetterSoup getRandomPuzzle(){
        boolean check = checkArguments();
        if (check){
            int size = Integer.parseInt(args[3]);
            Path file = Paths.get(args[1]);
            List<String> words = getWords(file, size);
            if (words == null){
                return null;
            }
            char[][] matrix = generateEmptyMatrix(size);
            return new LetterSoup(matrix, words, size);
        }else{
            return null;
        }
    }

    private boolean checkArguments(){
        if (args.length > 4){
            System.out.println("Demasiados argumentos. Deve seguir esta estrutura: java WSGenerator -i <wordlist.txt> -s <puzzle size>\n");
            return false;
        }
        if (args.length < 4){
            System.out.println("Não foram definidos todos os argumentos. Deve seguir esta estrutura: java WSGenerator -i <wordlist.txt> -s <puzzle size>\n");
            return false;
        }
        if (!args[0].equals("-i") || !args[2].equals("-s")){
            System.out.println("Os argumentos não foram bem definidos. Deve seguir esta estrutura: java WSGenerator -i <wordlist.txt> -s <puzzle size>\n");
            return false;
        }else {
            try{
                int size = Integer.parseInt(args[3]);
                Path file = Paths.get(args[1]);
                return true;
            }catch(Exception e){
                System.out.println("Os argumentos não foram bem definidos. Deve seguir esta estrutura: java WSGenerator -i <wordlist.txt> -s <puzzle size>\n");
                return false;
            }
        }

    }

    private List<String> getWords(Path file, int puzzleSize){
        try {
            String fileString = Files.readString(file);
            List<String> words =  new ArrayList<String>(Arrays.asList(fileString.split("\n")));
            WSSWordsValidator validator = new WSSWordsValidator();
            for (String w: words){
                if (!validator.isValid(w, puzzleSize)){
                    System.out.print("As palavras não cumprem com os requisitos. Resolva os problemas enumerados em cima e volte a tentar.\n");
                    return null;
                }
            }
            return words;
        } catch (IOException e) {
            System.out.println("Problemas a encontrar o ficheiro. Por favor tente novamente.\n");
            return null;
        }

    }

    private char[][] generateEmptyMatrix(int size){
        
        char[][] matrix = new char[size][size];
		
        for(int i = 0; i < size; i++){
            for (int k = 0; k < size; k++){    
                matrix[i][k] = '.';
            } 
        }
        return matrix;
    }
}
