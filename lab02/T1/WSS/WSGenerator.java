package lab01.WSS;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import lab01.classes.LetterSoup;
import lab01.classes.LetterSoupGenerator;

public class WSGenerator {
    public static void main(String[] args) {
        WSG wsg = new WSG(args);
        LetterSoup initilaLS = wsg.getRandomPuzzle();
        if(initilaLS == null){
            System.exit(0);
        }

        LetterSoupGenerator generator = new LetterSoupGenerator(initilaLS);
        
        LetterSoup finalLS = generator.generate();

        String string= "";

        for (char[] line: finalLS.getMatrix()){
            String newL = String.valueOf(line);
            string += newL + "\n";
        }
        for (String word: finalLS.getWords()){
            string += word + "\n";
        }
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter("./lab01/exemplos/letterSoup.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(string);
            printWriter.close();
        } catch (IOException e) {
            System.out.println("Erro ao escrever para o ficheiro. ");
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println(string);
        System.exit(0);
    }
        
}
