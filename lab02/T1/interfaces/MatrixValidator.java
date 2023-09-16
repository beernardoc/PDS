package lab01.interfaces;

public interface MatrixValidator {
    public boolean isValid();
    public boolean isCorrectSize(int size);
    public boolean isSquare(int expectedSize, int size);
    public boolean isCapitalAndAlphabetic(int lineNumber, String line);
}
