package Ex1;
 public class JGlog implements JGaloInterface{
    private char player1;
    private char player2;
    private char actualplayer;
    private int jogadas=0;
    private char winner;

    private char[][] matrix = new char[3][3];
    JGlog(char player){
        if(player=='O'){
            player1='O';
            player2='X';
        }
        else{
            player1='X';
            player2='O';
        }
    }
    JGlog(){
            player1='X';
            player2='O';
    }


    public char getPlayer2() {
        return player2;
    }
    public char getPlayer1() {
        return player1;
    }
    @Override
    public char getActualPlayer() {
        jogadas++;
        if(jogadas%2==0){
            actualplayer=getPlayer2();
        }else{
            actualplayer=getPlayer1();
        }
        return actualplayer;
        
    }


    @Override
    public boolean setJogada(int lin, int col) {
        if(lin>=1 && lin <=3 && col >=1 && col <=3){
            matrix[lin-1][col-1]=actualplayer;
            return true;

        }
        return false;
    }


    @Override
    public boolean isFinished() {
        if(jogadas==9){
            return true;
        }
        if(jogadas>4){ //check result 
            if(checkResult()=='X' || checkResult() =='O'){
                return true;
            }

        }
      return false;
    }


    public void HorizontalCheck(){
        int i=0;
        while(i<matrix.length){
            int col=0;
            String partial="";
            while(col<matrix.length){
                partial+=matrix[i][col];
                if(partial.equals("XXX")){
                    winner='X';
                    break;
                }else if(partial.equals("OOO")){
                    winner='O';
                    break;
                }
                col++;
            }
            i++;
        }
    }
    public void DiagonalCheck(){

        String partial = "";
        for (int i=0;i<matrix.length;i++){
            partial += matrix[i][i];
        }
        if(partial.equals("XXX")){
            winner = 'X';
        }else if (partial.equals("OOO")){
            winner = 'O';
            
        }
        
    
        
    }

    public void DiagonalCheckInversa(){
       
        
        String partial = "";

        partial += matrix[0][2];
        partial += matrix[1][1];
        partial += matrix[2][0];

        if(partial.equals("XXX")){
            winner = 'X';
        }else if (partial.equals("OOO")){
            winner = 'O';
            
        }

        
        
      
        
    
        
    }



    public void VerticalCheck(){
        for (int coluna=0; coluna < matrix.length; coluna++){
            int linha=0;
            String partial="";
            while (linha < matrix.length){
                partial+=matrix[linha][coluna];
                if(partial.equals("XXX")){
                    winner='X';
                    break;
                }else if(partial.equals("OOO")){
                    winner='O';
                    break;
                }
                linha ++;
            }
        
        }

    } 
    @Override
    public char checkResult() {          
            HorizontalCheck();
            VerticalCheck();
            DiagonalCheck();
            DiagonalCheckInversa();
            if(winner != 'X' && winner != 'O')winner=' ';
        return winner;
    }   


    
}