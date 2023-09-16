package lab01.WSS;

import java.util.Arrays;
import java.util.List;

import lab01.classes.LetterSoup;
import lab01.classes.LetterSoupSolution;
import lab01.classes.LetterSoupSolver;
import lab01.classes.Solution;
import lab01.classes.direction;

public class WSS {
    final String fileContent;

    public WSS(String fileContent) {
        this.fileContent = fileContent;
    }

    public String solve(){
        WSSStructure structure = new WSSStructure(fileContent);
    
        LetterSoup letterSoup = structure.generateLetterSoup();

        if (letterSoup == null){
            return null;
        }
        System.out.println(letterSoup.getWords());
        
        LetterSoupSolver solver = new LetterSoupSolver(letterSoup);
        
        LetterSoupSolution solution = solver.getSolution();
        String solutionString = generateSolutionString(solution);


        return solutionString;

    }

    private String generateSolutionString(LetterSoupSolution solution){
        List<Solution> solutions = solution.getAllSolutions();
        char[][] solutionArray = new char[solution.getLetterSoup().getSize()][solution.getLetterSoup().getSize()];
        for (int i = 0; i < solution.getLetterSoup().getSize(); i++) {
            for (int j = 0; j<solution.getLetterSoup().getSize(); j++) {
                solutionArray[i][j] = '.';
            }
        }
        
        String string = "\n";
        for (Solution s : solutions){
            String newS = String.format("%-12s %-5d %2d,%-2d %-6s\n",s.getWord(),s.getWordSize(),s.getLine()+1,s.getColumn()+1,s.getDirection());
            string += newS;

            solutionArray = insertIntoSolutionMatrix(solutionArray, s.getDirection(), s.getWord(), s.getLine(),s.getColumn());
        }
        string += "\n";

        for (char[] line: solutionArray){
            String newL = String.valueOf(line);
            string += newL + "\n";
        }
        
        string += "\n";
        return string;
    
    }

    private char[][] insertIntoSolutionMatrix(char[][] currentMatrix, direction direction, String word, int lineNumber,int colNumber){
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

}
