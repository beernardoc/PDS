//package lab01;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class DataReader {
    public String filelocation;

    DataReader(String filelocation) {
        this.filelocation = filelocation;
    }

    ArrayList<String> words = new ArrayList<>();
    char[][] puzzle;
    char[][] defaultpuzzle;
    List<String> data = new ArrayList<>();
    int MaxSize = 40;

    public void MAKEPUZZLEANDWORDS() {
        Scanner reader = null;
        try {
            if (!filelocation.contains(".txt")) {
                filelocation = filelocation + ".txt";
            }
            File sopa = new File(filelocation);
            reader = new Scanner(sopa);
            while (reader.hasNextLine()) {
                String LinhaContent = reader.nextLine();
                if (!LinhaContent.contains(" ") && !LinhaContent.contains(";") && !LinhaContent.contains(",")) {
                    for (char c : LinhaContent.toCharArray()) {
                        if (!Character.isUpperCase(c)) {
                            System.out.println("Todas as letras do puzzle devem estar em maiúscula! ("
                                    + c + ") esta em minuscula");
                            System.exit(-1);
                        }
                    }

                    data.add(LinhaContent);
                } else {
                    for (String word: LinhaContent.split("[;,\\s]")) {
                        if (word.length() != 0) {
                            String temporaia=word.toUpperCase();
                            if(word.equals(temporaia)){
                                System.out.println("Lista invalida a palavra "+temporaia +" nao deve estar só em maiúscula!");
                                   System.exit(-1);
                            };
                            if(!words.contains(word)){
                                words.add(word);
                            }
                        }
                    }
                }
            }
            for (String content : data) {// elements=linhas do puzzle
                boolean tester = validator(data.size(), content.length());
                if (!tester) {
                    System.exit(-1);
                }
            }
            if (words.size() == 0) {
                System.out.println("A lista de palavras nao pode estar vazia");
                System.out.println("Indique a/as palavra/as a procurar");
                System.exit(-1);
            } else {
                for (String e : words) {
                    boolean result = e.matches("[a-zA-Z]+");
                    if (!result) {
                        System.out.println("As palavras só podem ser compostas por caracteres alfabéticos.");
                        System.out.println("(" + e + ") não é alfabético");
                        System.exit(-1);
                    }
                    if (e.length() < 3) {
                        System.out.println("As palavras têm de ter pelo menos 3 caracteres a palavra (" + e + ") tem "
                                + e.length() + " caractees");
                        System.exit(-1);

                    }
                }
            }

            if (data.size() < MaxSize) {
                puzzle = new char[data.size()][data.size()];
                defaultpuzzle = new char[data.size()][data.size()];
                // put the data into puzzle
                int linha = 0;
                for (String content : data) {
                    content = content.toUpperCase();
                    for (int col = 0; col < content.length(); col++) {
                        puzzle[linha][col] = content.charAt(col);
                        defaultpuzzle[linha][col] = '*';
                    }
                    linha++;
                }
            } else {
                System.out.println("Tamanho maximo do puzzle = 40 ");
                System.exit(-1);
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro não encontrado");
            System.exit(-1);

        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    public boolean validator(int linhas, int colunas) {
        if (linhas == colunas) {
            return true;
        }
        System.out.println("puzzle nao quadrado / ou possui linhas vazias");
        return false;
    }
}
