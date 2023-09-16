import java.io.FileNotFoundException;
import java.util.*;
class Generator {
       static GenMethods methods=new GenMethods();
       public static void main(String[] args) throws FileNotFoundException {
        methods.GetArgs(args);
        int puzzlelength=methods.puzzlelength;
        String input=methods.input;
        ArrayList<String> words=methods.GetWords(input);
        methods.ListValidation(words,puzzlelength);
        char puzzle[][]=methods.GenPuzzle(words,puzzlelength);
        Solver validator=new Solver(0);
        validator.SubstringChecker(words);//remove substrings in case of wrong wordslist
        puzzle=methods.GenPuzzle(words, puzzlelength);
        boolean IsCorrectPuzzle=false;
        int tryconter=0;
        while(!IsCorrectPuzzle && tryconter<methods.MAXRUN){
            puzzle=methods.GenPuzzle(words, puzzlelength);
            
            for(String word:words){
                IsCorrectPuzzle=validator.horizontal(puzzle, word) || validator.vertical(puzzle, word) || validator.diagonal(puzzle, word);
            }
            tryconter++;
        }
        methods.RandonChartoPuzzle(puzzle);
        methods.PrintResult(words,puzzle);
    }
    
}