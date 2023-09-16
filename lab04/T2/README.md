# Aula03 - Notes

This is the implementation of the MyJGalo class, which implements the JGaloInterface. This class represents a tic-tac-toe game board and provides methods to interact with it.

**Class Members**

The MyJGalo class has the following members:

**Fields**

- isPlayer1 (type boolean): A boolean indicating whether the current player is player 1.
- matrix (type char[][]): A 2-dimensional char array representing the game board.
- turn (type char): A char representing the current turn ('X' or 'O').

**Constructor**

- MyJGalo(): Initializes the matrix field with an empty 3x3 char array.

**Methods**

- getActualPlayer(): Returns the current player ('X' or 'O') and switches the isPlayer1 field to the opposite value.
- setJogada(int lin, int col): Sets a player's move on the board, if the selected cell is empty, and switches the turn field to the opposite value. Returns true if the move was successful, false otherwise.
- isFinished(): Checks if the game is finished by verifying if all cells are filled or if there is a winning combination. Returns true if the game is finished, false otherwise.
- checkResult(): Checks the result of the game by verifying if there is a winning combination. Returns the winning player ('X' or 'O') or a space character if there is no winner.

**Implementation Details**

The isFinished() method checks for all filled cells and winning combinations by iterating over a pre-defined set of winning combinations. The checkResult() method also iterates over the winning combinations and returns the winning player or a space character.

Note that there is a commented alternative implementation for the isFinished() method that uses Java 8 streams to simplify the code.
