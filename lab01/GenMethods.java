import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;
class GenMethods {
    public String input;
    public String output;
    public int puzzlelength=0; 
    public Random random = new Random();
    public int MAXRUN = 100000;

    public void  Printpuzzle(char [][] puzzle) {
        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle[i].length; j++) {
                System.out.print(puzzle[i][j]);
            }
            System.out.println();
        }
    }
    public void GetArgs(String [] args) {
        for(int i=0;i<args.length;i++){
            switch(args[i]){
                case "-i" :
                this.input=args[i+1];
                break;
                case "-o" :
                this.output=args[i+1];
                break;
                case "-s" :
                this.puzzlelength=Integer.parseInt(args[i+1]);
                if(puzzlelength>40){
                    System.out.println("Max puzzle length should be 40");
                    System.exit(-1);
                }
                break;
                default:
                break;

            }
        }
        if(this.input==null){
            System.out.println("Insira um ficheiro com a lista de palavras");
            System.exit(-1);
        }if(this.puzzlelength==0){
            System.out.println("Tamanho do puzzle deve ser informado");
        }
    }

    public void PrintResult(ArrayList <String> words,char [][] puzzle) throws FileNotFoundException {
        if(this.output!=null){
            PrintStream o = new PrintStream(new File(output));
            PrintStream console = System.out;
            System.setOut(o);
            Printpuzzle(puzzle);
            for(String word:words){
                System.out.print(word+";");
            }
            System.setOut(console);
            System.out.println("result in "+output);
     
        }else{
            Printpuzzle(puzzle);
            for(String word:words){
                System.out.print(word+";");
            }
           System.out.println();
        }

    }

    public  char [][] GenPuzzle(ArrayList<String> words, int puzzlesize){
        char puzzle[][];
        puzzle=new char[puzzlesize][puzzlesize];
        for(int i=0;i<puzzle.length;i++){
            for (int j=0; j<puzzle.length; j++) {
                puzzle[i][j] = '*';
            }
        }

        for(String word:words){
            String direction;
            boolean inserted = false;
            int tries = 0;
            while (!inserted && tries < MAXRUN) {
                tries++;
                int x = random.nextInt(puzzlelength);
                int y = random.nextInt(puzzlelength);
                direction=RandonDirection();
                inserted = InsertWordTopuzzle(word, puzzle, x, y, direction);
            }
            if (!inserted) {
                System.err.println("não foi possivel inserir a palavra: " + word);
                System.exit(1);
            }
      
        };

        return puzzle;
    }
    public ArrayList<String> GetWords(String filelocation) throws FileNotFoundException {
        if(!filelocation.contains(".txt")){
            filelocation=filelocation+".txt";
        }
        ArrayList<String> words= new ArrayList<String>();
        File f=new File(filelocation);

        Scanner sc=new Scanner(f);
        while (sc.hasNextLine()) {
            String data=sc.nextLine();            
            if(data.equals("")){
                System.out.println("A lista de palavras não pode ter linhas vazias");
                System.exit(-1);
            }
            for(String word:data.split("[;,\\s]")){
                if(word.length()>=3){
                    if(!words.contains(word)){
                        words.add(word);
                    }
                }else{
                    System.out.println("As palavras têm de ter pelo menos 3 caracteres");
                    System.out.println(word + " <-is too short");
                    System.exit(-1);
                }
            }
        }
        sc.close();
        return words;
    }
    public String RandonDirection(){
        List<String> randondirections = Arrays.asList("Right","Left","Up","Down","DownLeft","DownRight","UpLeft","UpRight");
        int randomNum =random.nextInt(8);
        String direction=randondirections.get(randomNum);
        return direction;
    }
    public void RandonChartoPuzzle(char puzzle[][]){
        for(int i=0;i<puzzle.length;i++){
            for (int j = 0; j < puzzle[i].length; j++) {
                if(puzzle[i][j]=='*'){
                    int rnd = (int) (Math.random() * 52); // or use Random or whatever
                    char base = (rnd < 26) ? 'A' : 'a';
                    char rndchar = ((char) (base + rnd % 26));
                    rndchar=Character.toUpperCase(rndchar);
                    puzzle[i][j]=rndchar;
                }
            }
        }

    }
    public void ListValidation(ArrayList <String> list,int puzzlelength){
        String temporaia;
        for(String word:list){
            temporaia=word.toUpperCase();
            if(temporaia.equals(word)){
                System.out.println("Lista invalida a palavra "+temporaia +" nao deve estar só em maiúscula!");
                System.exit(-1);
            }
            if(word.length()>puzzlelength){
                System.out.println(word + " is too long for puzzle with size " + puzzlelength);
                System.exit(-1);
            }
    
        };
    }



public boolean InsertWordTopuzzle(String word, char[][] puzzle, int row, int col, String direction) {
    int length = word.length();
    int rowIncrement = 0;
    int colIncrement = 0;
    word=word.toUpperCase();
    switch (direction) {
        case "Up":
             rowIncrement = -1;
        break;
        case "Down":
            rowIncrement = 1;  
        break;
        case "Left":
            colIncrement = -1;   
        break;
        case "Right":
            colIncrement = 1;
        break;
        case "DownLeft":
            rowIncrement = 1;
            colIncrement = -1;
        break;
        case "DownRight":
            rowIncrement = 1;
            colIncrement = 1;
        break;
        case "UpLeft":
            rowIncrement = -1;
            colIncrement = -1;
        break;
        case "UpRight":
            rowIncrement = -1;
            colIncrement = 1;
        break;
    }

    // Verifica se a palavra pode ser inserida na posição (row, col) na direção escolhida
    for (int i = 0; i < length; i++) {
        int newRow = row + i * rowIncrement;
        int newCol = col + i * colIncrement;

        // Verifica se a nova posição está dentro dos limites do array
        if (newRow < 0 || newRow >= puzzle.length || newCol < 0 || newCol >= puzzle.length) {
            return false;
        }
        // Verifica se a nova posição está vazia ou contém a mesma letra da palavra
        if (puzzle[newRow][newCol] != '*' && puzzle[newRow][newCol] != word.charAt(i)) {
            return false;
        }
    }
    // Insere a palavra na posição (row, col) na direção escolhida
    for (int i = 0; i < length; i++) {
        int newRow = row + i * rowIncrement;
        int newCol = col + i * colIncrement;
        puzzle[newRow][newCol] = word.charAt(i);
    }

    return true;
}
}