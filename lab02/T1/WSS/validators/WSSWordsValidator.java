package lab01.WSS.validators;

import lab01.WSS.Globals;
import lab01.interfaces.WordValidator;

public class WSSWordsValidator implements WordValidator {

    
    @Override
    public boolean isValid(String word, int maxSize) {
        if (!isLowerCaseOrMixed(word) || !isCorrectSize(word, maxSize)){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public boolean isLowerCaseOrMixed(String word) {
        if (word.matches("[A-Z]+")){
            System.out.println("A palavra "+word+" está escrita toda em letras maiusculas. Por favor corrija de forma a que esteja escrita em minusculas ou misturado.\n");
        }else if(word.matches("[a-zA-Z]+")){
            return true;
        }
        return false;
    }

    @Override
    public boolean isCorrectSize(String word, int maxSize) {
        int wordSize = word.length();
        if (wordSize < Globals.MinSize){
            System.out.println("A palavra "+word+" não pode ser utilizado uma vez que tem menos de " + Globals.MinSize+ " caracteres.\n");
            return false;
        }
        if (wordSize > maxSize){
            System.out.println("A palavra "+word+" não pode ser utilizado uma vez que tem mais caracteres do que o tamanho possível do puzzle.\n");
            return false;
        }
        return true;
    }
    
}
