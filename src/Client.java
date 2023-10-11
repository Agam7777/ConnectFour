import java.util.*;

public class Client {

    public static void main(String[] args) {
        ConnectFour game = new ConnectFour();
        Scanner scan = new Scanner(System.in);

        System.out.println(game.instructions());

        while (!game.isGameOver()) {
            System.out.println("\n" + game.toString());
            int currentPlayer = game.getNextPlayer();
            System.out.println("Player " + currentPlayer + ", enter a column (1-7):");
            int column = scan.nextInt();

            if (game.makeMove(column)) {
                if (game.isGameOver()) {
                    int winner = game.getWinner();
                    if (winner == 0) {
                        System.out.println("\nIt's a tie!");
                        System.out.println(game.toString());
                    } else {
                        System.out.println("\nPlayer " + winner + " wins!");
                        System.out.println(game.toString());
                    }
                }
            } else {
                System.out.println("Invalid move. Please try again.");
            }
        }
    }
}