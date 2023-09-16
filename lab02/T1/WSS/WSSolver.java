package lab01.WSS;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WSSolver {
    public static void main(String[] args) {
        String fileString = "";
		try {
            Path path = Paths.get(args[0]); 
            //Path path = Paths.get("/Users/diogosilva/Documents/UA/PDS/practical-g4_12/lab01/exemplos/ws1.txt");
            fileString = Files.readString(path);
        } catch (Exception e) {
			//System.out.println("Erro a ler o ficheiro. Tente novamente.");
            System.exit(0);
		}
        WSS wss = new WSS(fileString);

        String solution = wss.solve();
        
        if (solution == null){
            System.exit(0);
        }else{
            String fileName = "solution_" ;
            //String fileName = "solution.txt";
            FileWriter fileWriter;
            try {
                fileWriter = new FileWriter(fileName);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.print(solution);
                printWriter.close();
            } catch (IOException e) {
                System.out.println("Erro ao escrever para o ficheiro. ");
			    e.printStackTrace();
                System.exit(0);
            }
            System.out.print(solution);
            System.exit(0);
        }
    }
}
