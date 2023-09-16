package lab01.interfaces;

public interface WordValidator {
    public boolean isValid(String word, int maxSize);
    public boolean isLowerCaseOrMixed(String word);
    public boolean isCorrectSize(String word, int maxSize);
}
