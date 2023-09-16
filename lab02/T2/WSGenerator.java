import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class WSGenerator {
    public static void main(String[] args) {
        String file = null;
        int n = 0;

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-i":
                    file = args[i + 1];
                    continue;
                case "-s":
                    n = Integer.parseInt(args[i + 1]);
                    continue;
            }
        }

        try {

            Scanner sc = new Scanner(new File(file));

            // get words from the file
            ArrayList<String> words = new ArrayList<String>();
            while (sc.hasNextLine()) {

                String line = sc.nextLine();
                String[] list = line.split("\\W+");
                for (int i = 0; i < list.length; i++) {
                    // word must have at least 3 characters...
                    if (list[i].length() >= 3) {
                        // change words upper case
                        words.add(list[i].toUpperCase());
                    }
                }

            }

            // initialize puzzle
            char[][] puzzle = new char[n][n];

            // for each word from the file
            for (String word : words) {
                main_loop: while (true) {

                    // choose current position
                    int initialCol = getRandNum(0, n - 1);
                    int initialRow = getRandNum(0, n - 1);

                    // back to the start if position is already occupied
                    if ("ABCDEFGHIJKLMNOPQRSTUVWXYZ".contains(String.valueOf(puzzle[initialRow][initialCol]))) {
                        continue;
                    }

                    // "while row e col == 0" because if both are zero then it doesnt get out of the same position
                    // at least one cant be 0

                    int row;
                    int col;
                    do {
                        row = getRandNum(-1, 1);
                        col = getRandNum(-1, 1);
                    } while (row == 0 && col == 0);

                    for (int i = 1; i <= word.length(); i++) {
                        try {
                            char pos = puzzle[initialRow + i * row][initialCol + i * col];
                            // in case there is a char, jumps to the beginning and starts again
                            if ("ABCDEFGHIJKLMNOPQRSTUVWXYZ".contains(String.valueOf(pos))) {
                                continue main_loop;
                            }
                        } catch (Exception e) {
                            continue main_loop;
                        }
                    }

                    for (int i = 0; i < word.length(); i++) {
                        puzzle[initialRow + i * row][initialCol + i * col] = word.charAt(i);
                    }
                    // to access the next word
                    break;

                }
            }

            for (int row = 0; row < puzzle.length; row++) {
                for (int col = 0; col < puzzle.length; col++) {
                    if ("ABCDEFGHIJKLMNOPQRSTUVWXYZ".contains(String.valueOf(puzzle[row][col]))) {
                        continue;
                    }
                    // random char are created in upper case
                    puzzle[row][col] = getRandChar();
                }
            }

            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    System.out.print(puzzle[row][col]);
                }
                System.out.println();
            }

            //print the word list in lowercase
            System.out.println(words.toString().replace("[", "").replace("]", "").toLowerCase());

        } catch (Exception e) {
            System.err.format("Exception occurred trying to read '%s'.", file);
            e.printStackTrace();
            return;
        }
    }

    private static int getRandNum(int min, int max) {
        Random r = new Random();
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        return r.nextInt((max - min) + 1) + min;
    }

    private static char getRandChar() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random r = new Random();
        return alphabet.charAt(r.nextInt(alphabet.length()));
    }
}