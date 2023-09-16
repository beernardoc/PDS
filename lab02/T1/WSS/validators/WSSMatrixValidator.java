package lab01.WSS.validators;

import lab01.WSS.Globals;
import lab01.interfaces.MatrixValidator;


public class WSSMatrixValidator implements MatrixValidator {
    String[] matrixString;
    
    public WSSMatrixValidator(String[] matrixString) {
        this.matrixString = matrixString;
    }

    @Override
    public boolean isValid() {
        boolean isValid = true;
        int size = matrixString[0].length();
        
        if (!isCorrectSize(size)){
            isValid = false;
        }
        for (int i = 0; i < size; i++){
            if (!isCapitalAndAlphabetic(i+1,matrixString[i].strip())){
                isValid = false;
            }
            if (!isSquare(size, matrixString[i].strip().length())){
                isValid = false;
            }
        }
        return isValid;
    }

    @Override
    public boolean isCorrectSize(int size) {
        if ( size < Globals.MinSize ){
            System.out.print("O tamanho minímo do puzzle é de "+Globals.MinSize +"x"+Globals.MinSize+".\n");
            return false;
        }
        if ( size > Globals.MaxSize ){
            System.out.print("O tamanho máximo do puzzle é de "+Globals.MaxSize +"x"+Globals.MaxSize+".\n");
            return false;
        }
        return true;
    }

    @Override
    public boolean isCapitalAndAlphabetic(int lineNumber, String line) {
        
        if (line.matches("[A-Z]+")){
            return true;
        }else {
            System.out.print("Todas as letras do puzzle devem ser alfabéticas e maiúsculas, o que não acontece na linha "+lineNumber+".\n");
            return false;
        }
    }

    @Override
    public boolean isSquare(int expectedSize, int size) {
        if(expectedSize == size){
            return true;
        }else{
            System.out.println("Todas as linhas do puzzle devem ter o mesmo tamanho.\n");
            return false;
        }
    }
}
