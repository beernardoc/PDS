//package lab01;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;

class Solver {
    char[][] PUZZLE;
    char[][] DEFAULTPUZZLE;
    ArrayList<String> WORDS;
    String direction;
    int FinderCounter = 0;
    List<String> wordsfound = new ArrayList<>();
    DataReader d1DataReader;
    String filelocation;
    int allowprint=0;
    Solver(String filelocation) {
        d1DataReader = new DataReader(filelocation);
        this.filelocation = filelocation;
        this.allowprint=1;
    }
    Solver(int allowprint) {
        this.allowprint=allowprint;
    }

    void Solve() throws FileNotFoundException {
        d1DataReader.MAKEPUZZLEANDWORDS();
        this.DEFAULTPUZZLE = d1DataReader.defaultpuzzle;
        this.PUZZLE = d1DataReader.puzzle;
        this.WORDS = d1DataReader.words;
        SubstringChecker(WORDS);//remove substrings in case of wrong wordslist
        File fileoutput = null;
        try {
            filelocation=filelocation.replace(".txt","");
            fileoutput = new File(filelocation + "_result.txt");   //output to files
            System.setOut(new PrintStream(fileoutput));
            for (String word : WORDS) {
                boolean h = horizontal(PUZZLE, word);
                boolean v = vertical(PUZZLE, word);
                boolean d = diagonal(PUZZLE, word);
                if (!h && !v && !d) {
                   System.out.println("Todas as palavras devem estar no puzzle!");
                   System.out.println("(" + word + ") não esta no puzzle");
                   System.exit(-1);
                }
                if(FinderCounter>1){
                    System.out.println("Erro no puzzle palavra("+word+") aparece mais e 1 vez");
                    return;
                  }
                  FinderCounter=0;
            }
            System.out.println();
            printPuzzle(DEFAULTPUZZLE);

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
            System.out.println("verifica o resultado no ficheiro-> " + fileoutput);
        }

    }

    void printPuzzle(char[][] puzzle) {
        for (char[] chars : puzzle) {
            for (int j = 0; j < puzzle.length; j++) {
                System.out.print(chars[j] + " ");
            }
            System.out.println();
        }
    }
    public void PutIntoDefaultPuzzle(int rangeindex, int linha, int coluna, String Direction, String word) {
            if(!direction.equals("Left")){
                System.out.printf("%-30s %-15s %-15s %-15s \n", word, word.length(), (linha + 1 + "," + (coluna + 1)),
                direction);
            }
        switch (Direction) {
            case "Left":
                for (int pz2index = coluna; pz2index < rangeindex; pz2index++) {
                    DEFAULTPUZZLE[linha][pz2index] = PUZZLE[linha][pz2index]; // linha da primeira ocorencia nao muda
                }
                break;
            case "Right":
                for (int pz2index = coluna; pz2index < rangeindex; pz2index++) {
                    DEFAULTPUZZLE[linha][pz2index] = PUZZLE[linha][pz2index]; // linha da primeira ocorencia nao muda
                }
                break;
            case "Down":
                for (int pz2index = linha; pz2index < rangeindex; pz2index++) {

                    DEFAULTPUZZLE[pz2index][coluna] = PUZZLE[pz2index][coluna];
                }
                
                break;
            case "Up":
                for (int pz2index = linha; pz2index > rangeindex; pz2index--) {
                    DEFAULTPUZZLE[pz2index][coluna] = PUZZLE[pz2index][coluna];
                }
              
                break;
            case "UpLeft":
                for (int i = 0; i < rangeindex; i++) {
                    DEFAULTPUZZLE[linha - i][coluna - i] = PUZZLE[linha - i][coluna - i];
                }
                break;
            case "DownLeft":
                for (int pz2index = linha; pz2index < rangeindex; pz2index++) {
                    int yyy = coluna; // para evitar erro da linha abaixo
                    DEFAULTPUZZLE[pz2index][yyy--] = PUZZLE[pz2index][coluna--];
                }
                break;
            case "DownRight":
                for(int i=0;i<rangeindex;i++){
                    DEFAULTPUZZLE[linha+i][coluna+i] = PUZZLE[linha+i][coluna+i];

                }
                break;
            case "UpRight":
                for (int pz2index = coluna; pz2index < rangeindex; pz2index++) {
                    int x = linha; // para evitar erro da linha abaixo
                    DEFAULTPUZZLE[linha--][pz2index] = PUZZLE[x--][pz2index];
                }
                break;
            default:
                System.out.println("error");
                break;
        }

    }

    public boolean horizontal(char[][] puzzle, String word) {
        word = word.toUpperCase();
        int linha = 0;
        boolean founded=false;
        while (linha < puzzle.length) {
            String partialword = "";
            for (int coluna = 0; coluna < puzzle.length; coluna++) {
                if (puzzle[linha][coluna] == word.charAt(0)) {
                    // direita
                    for (int finder = coluna; finder < puzzle.length; finder++) {
                        partialword += puzzle[linha][finder];
                        if (partialword.equals(word)) {                       
                            this.direction = "Right";
                            int rangeindex = coluna + word.length();
                            if(this.allowprint!=0){
                            PutIntoDefaultPuzzle(rangeindex, linha, coluna, direction, word);
                            }
                            FinderCounter++;
                            founded=true;
                            break;
                        }
                    }
                    // esquerda
                    partialword = "";
                    for (int finder = coluna; finder >= 0; finder--) {
                        partialword += puzzle[linha][finder];
                        if (partialword.equals(word)) {
                            this.direction = "Left";
                            int rangeindex = finder + word.length();
                            if(this.allowprint!=0){
                                System.out.printf("%-30s %-15s %-15s %-15s\n", word, word.length(),
                                (coluna + 1 + "," + (coluna+1)), direction);
                                   PutIntoDefaultPuzzle(rangeindex, linha, finder, direction, word);
       
                            }
                       
                             FinderCounter++;
                             founded=true;
                             break;
                        }
                    }
                }
            }
            linha++;
        }
        return founded;
    }

    public boolean vertical(char[][] puzzle, String word) {
        word = word.toUpperCase();
        // vertical down 
        boolean founded=false;
        for (int linha = 0; linha < puzzle.length; linha++) {
            for (int coluna = 0; coluna < puzzle.length; coluna++) {
                int x = linha;
                int y = coluna;
                String partialword = "";
                if (puzzle[x][y] == word.charAt(0)) {
                    while (x < puzzle.length) {
                        partialword += puzzle[x][y];
                        if (partialword.equals(word)) {
                            this.direction = "Down";
                            int rangeindex = linha + word.length(); 
                            if(this.allowprint!=0){
                                PutIntoDefaultPuzzle(rangeindex, linha, coluna, direction, word);
                            }                                            // encontrado
                            FinderCounter++;

                            founded= true;
                            break;
                        }
                        x++;
                    }
                    partialword = "";
                }
            }
        }
        // vertical UP melhorado working
        for (int linha = puzzle.length - 1; linha >= 0; linha--) {
            for (int coluna = puzzle.length - 1; coluna >= 0; coluna--) {
                int x = linha;
                String partialword = "";
                if (puzzle[x][coluna] == word.charAt(0)) {
                    while (x >= 0) {
                        partialword += puzzle[x][coluna];
                        if (partialword.equals(word)) {
                            this.direction = "Up";
                            int rangeindex = linha - word.length();
                            if(this.allowprint!=0){
                                PutIntoDefaultPuzzle(rangeindex, linha, coluna, direction, word);
                            }
                            FinderCounter++;
                            founded=true;
                            break;
                        }
                        x--;
                    }
                }
            }

        }
        return founded;
    }

    public boolean diagonal(char[][] puzzle, String word) {
        // diagonal UpLeft
        word = word.toUpperCase();
        boolean founded=false;
        for (int linha = 0; linha < puzzle.length; linha++) {
            for (int coluna = 0; coluna < puzzle.length; coluna++) {
                if (puzzle[linha][coluna] == word.charAt(0)) {
                    int x = linha;
                    int y = coluna;
                    String partialword = "";
                    while (x >= 0 && y >= 0) {
                        partialword += puzzle[x][y];
                        if (partialword.equals(word)) {
                            this.direction = "UpLeft";
                            int rangeindex = word.length();
                            if(this.allowprint!=0){
                                PutIntoDefaultPuzzle(rangeindex, linha, coluna, direction, word);
                            }
                            FinderCounter++;
                            founded=true;                            
                            break;
                        }
                        x--;
                        y--;
                    }
                }
            }
        }
        // diagonal up UpRigth
        for (int linha = puzzle.length - 1; linha >= 0; linha--) {
            for (int coluna = puzzle.length - 1; coluna >= 0; coluna--) {
                if (puzzle[linha][coluna] == word.charAt(0)) {
                    int x = linha;
                    int y = coluna;
                    String partialword = "";
                    while (x >= 0 && y < puzzle.length) {
                        partialword += puzzle[x][y];
                        if (partialword.equals(word)) {
                            this.direction = "UpRight";
                            int rangeindex = coluna + word.length();
                            if(this.allowprint!=0){
                                PutIntoDefaultPuzzle(rangeindex, linha, coluna, direction, word);

                            }
                            FinderCounter++;
                            founded= true;
                            break;
                        }
                        x--;
                        y++;
                    }
                }
            }
        }
        // diagonal DownLeft
        for (int linha = 0; linha < puzzle.length; linha++) {
            for (int coluna = 0; coluna < puzzle.length; coluna++) {
                if (puzzle[linha][coluna] == word.charAt(0)) {
                    int x = linha;
                    int y = coluna;
                    String partialword = "";
                    while (x < puzzle.length && y >= 0) {
                        partialword += puzzle[x][y];
                        if (partialword.equals(word)) {
                            this.direction = "DownLeft";
                            int rangeindex = linha + word.length();
                            if(this.allowprint!=0){
                                PutIntoDefaultPuzzle(rangeindex, linha, coluna, direction, word);

                            }
                            FinderCounter++;

                            founded= true;
                            break;
                        }
                        x++;
                        y--;
                    }
                }
            }
        }
        // diagonal DownRight
        rerun:
        for (int linha = 0; linha < puzzle.length; linha++) {
            for (int coluna = 0; coluna < puzzle.length; coluna++) {
                if (puzzle[linha][coluna] == word.charAt(0)) {
                    int x = linha;
                    int y = coluna;
                    String partialword = "";
                    while (x < puzzle.length && y < puzzle.length) {
                        partialword += puzzle[x][y];
                        if (partialword.equals(word)) {
                            this.direction = "DownRight";
                            int rangeindex = word.length();
                            if(this.allowprint!=0){
                                PutIntoDefaultPuzzle(rangeindex, linha, coluna, direction, word);

                            }
                            FinderCounter++;
                            founded= true;
                            break rerun;
                        }

                        x++;
                        y++;
                    }
                }
            }
        }

        return founded;
    }


    public void SubstringChecker(ArrayList<String> words) { 
        HashMap <String,Integer> map=new HashMap<String,Integer>();
        for(String w1:words){
            int length=w1.length();
            for(String w2:words){
                if(w1.contains(w2)|| w2.contains(w1)){
                    if(w2.length()>length){
                        map.put(w1,w1.length());
                        map.put(w2, w2.length());
                    }

                }
            }
        }
        int maxlength=0;
        for(String key:map.keySet()){
            if(map.get(key)>maxlength){
                maxlength=map.get(key);
            }
        }
        for(String key:map.keySet()){
            if(map.get(key)<maxlength){
                words.remove(key);
            }
        }
    }
}
