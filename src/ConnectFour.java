import java.util.*;

// A class to represent a game of Connect-Four that implements the
// AbstractStrategyGame interface.
public class ConnectFour implements AbstractStrategyGame {

    private int[][] board;
    private int currentPlayer;

    // Constructs a new ConnectFour game.
    public ConnectFour() {
        board = new int[6][7];
        currentPlayer = 1;
    }

    // Returns a String containing instructions to play the game.
    public String instructions() {
        String instructions = "";
        instructions += "Welcome to Connect Four! The goal of the game is to be\n";
        instructions += "the first player to connect four of your tokens in a row,\n";
        instructions += "either horizontally, vertically, or diagonally. Player 1's\n";
        instructions += "token will be X and Player 2's will be O. Players will play\n";
        instructions += "their turn alternatively with player 1 going first and to\n";
        instructions += "play their turn the player simply has to choose the column\n";
        instructions += "(1-7) number they want to slide their token in. The token will\n";
        instructions += "be placed bottom-most available space. The game ends in a draw \n";
        instructions += "if the board is full with no player slotting four tokens in a\n";
        instructions += "row";
        return instructions;
    }

    // Returns a String representation of the current state of the board.
    public String toString() {
        String result = "";
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                if (board[row][col] == 0) {
                    result += " ";
                } else {
                    if (board[row][col] == 1) {
                        result += "X";
                    } else {
                        result += "O";
                    }
                }
                result += " | ";
            }
            result += "\n";
        }
        return result;
    }

    // Returns whether the playing board is full (true) or not
    // (false).
    private boolean isBoardFull() {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                if (board[row][col] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    // Returns whether or not the game is over.
    public boolean isGameOver() {
        return getWinner() != -1 || isBoardFull();
    }

    // Returns the index of the player who has won the game, or -1
    // if the game is not over yet. Player 1 token is X, Player 2's
    // is O.
    public int getWinner() {
        int winner = -1;

        // Check horizontally
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col <= 7 - 4; col++) {
                int player = board[row][col];
                if (player != 0 && player == board[row][col + 1] &&
                        player == board[row][col + 2] &&
                        player == board[row][col + 3]) {
                    return player;
                }
            }
        }

        // Check vertically
        for (int col = 0; col < 7; col++) {
            for (int row = 0; row <= 6 - 4; row++) {
                int player = board[row][col];
                if (player != 0 && player == board[row + 1][col] &&
                        player == board[row + 2][col] &&
                        player == board[row + 3][col]) {
                    return player;
                }
            }
        }

        // Check diagonals (top-left to bottom-right)
        for (int row = 0; row <= 6 - 4; row++) {
            for (int col = 0; col <= 7 - 4; col++) {
                int player = board[row][col];
                if (player != 0 && player == board[row + 1][col + 1] &&
                        player == board[row + 2][col + 2] &&
                        player == board[row + 3][col + 3]) {
                    return player;
                }
            }
        }
        // Check diagonals (top-right to bottom-left)
        for (int row = 0; row <= 6 - 4; row++) {
            for (int col = 7 - 1; col >= 3; col--) {
                int player = board[row][col];
                if (player != 0 && player == board[row + 1][col - 1]
                        && player == board[row + 2][col - 2] &&
                        player == board[row + 3][col - 3]) {
                    return player;
                }
            }
        }

        return winner;
    }

    // Returns the index of which player's turn it is.
    // 1 if player 1 (X), 2 if player 2 (O).
    public int getNextPlayer() {
        return currentPlayer;
    }

    // Given input in the form of the column number, places an X
    // or an O where the player specifies. Returns false if the
    // position is invalid. Particularly, this is if the position
    // is out of bounds or already occupied to the top. Returns true
    // otherwise. Board bounds are [1, 7] for column number.
    public boolean makeMove(int column) {
        if (column < 1 || column > 7) {
            return false;
        }

        int col = column - 1;
        for (int row = 6 - 1; row >= 0; row--) {
            if (board[row][col] == 0) {
                board[row][col] = currentPlayer;
                currentPlayer = (currentPlayer == 1) ? 2 : 1;
                return true;
            }
        }
        return false;
    }
}

