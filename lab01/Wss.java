//package lab01;

import java.io.FileNotFoundException;

public class Wss {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println();
        String filelocation = "";
        if (args.length == 1) {
            filelocation = args[0];
            if(!filelocation.contains(".txt")){
                filelocation=filelocation+".txt";
            }
        } else {
            System.out.println("O programa so aceita um ficheiro de entrada");
            System.exit(-1);
        }
        Solver s1 = new Solver(filelocation);
        s1.Solve();

    }
}