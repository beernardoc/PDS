package lab03;

import java.util.stream.Stream;
import java.util.Arrays;

public class MyJGalo implements JGaloInterface {

    private boolean isPlayer1 = false;
    private char[][] matrix;
    private char turn;

    public MyJGalo() {
        matrix = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = ' ';
            }
        }
    }

    @Override
    public char getActualPlayer() {
        isPlayer1 = !isPlayer1;
        return isPlayer1 ? 'X' : 'O';
    }

    @Override
    public boolean setJogada(int lin, int col) {
        if (matrix[lin-1][col-1] == ' ') {
            matrix[lin-1][col-1] = turn;
            turn = turn == 'X' ? 'O' : 'X';
            return true;
        }
        return false;
    }


    @Override
    public boolean isFinished() {
        int[][] winCombinations = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

        boolean allFilled = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matrix[i][j] == ' ') {
                    allFilled = false;
                    break;
                }
            }
            if (!allFilled) {
                break;
            }
        }

        boolean anyWinCombination = false;
        for (int i = 0; i < winCombinations.length; i++) {
            int[] combination = winCombinations[i];
            char c1 = matrix[combination[0]/3][combination[0]%3];
            char c2 = matrix[combination[1]/3][combination[1]%3];
            char c3 = matrix[combination[2]/3][combination[2]%3];
            if (c1 != ' ' && c1 == c2 && c2 == c3) {
                anyWinCombination = true;
                break;
            }
        }

        /*
        boolean allFilled = Arrays.stream(matrix).flatMapToInt(Arrays::stream).noneMatch(c -> c == ' ');

        boolean anyWinCombination = Arrays.stream(winCombinations)
                .anyMatch(c -> matrix[c[0]/3][c[0]%3] == matrix[c[1]/3][c[1]%3]
                        && matrix[c[1]/3][c[1]%3] == matrix[c[2]/3][c[2]%3]
                        && matrix[c[0]/3][c[0]%3] != ' ');

         */

        return allFilled || anyWinCombination;

    }

    @Override
    public char checkResult() {
        int[][] winCombinations = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
        for (int[] combo : winCombinations) {
            char c = matrix[combo[0]/3][combo[0]%3];
            if (c != ' ' && c == matrix[combo[1]/3][combo[1]%3] && c == matrix[combo[2]/3][combo[2]%3]) {
                return c;
            }
        }
        return ' ';
    }

}
