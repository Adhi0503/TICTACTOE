import java.util.Random;
import java.util.Scanner;

public class UC1 {

    static char[][] board = {
        {' ', ' ', ' '},
        {' ', ' ', ' '},
        {' ', ' ', ' '}
    };

    static char human = 'X';
    static char computer = 'O';

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        while (true) {

            printBoard();

            // HUMAN MOVE
            System.out.println("Your move (row and column 1-3): ");
            int row = sc.nextInt() - 1;
            int col = sc.nextInt() - 1;

            if (!isValidMove(row, col)) {
                System.out.println("Invalid move, try again.");
                continue;
            }

            board[row][col] = human;

            if (checkWin(human)) {
                printBoard();
                System.out.println("You win!");
                break;
            }

            if (isBoardFull()) {
                printBoard();
                System.out.println("It's a draw!");
                break;
            }

            // COMPUTER MOVE
            int compRow, compCol;
            do {
                compRow = rand.nextInt(3);
                compCol = rand.nextInt(3);
            } while (!isValidMove(compRow, compCol));

            System.out.println("Computer played: " + (compRow + 1) + " " + (compCol + 1));
            board[compRow][compCol] = computer;

            if (checkWin(computer)) {
                printBoard();
                System.out.println("Computer wins!");
                break;
            }

            if (isBoardFull()) {
                printBoard();
                System.out.println("It's a draw!");
                break;
            }
        }

        sc.close();
    }

    static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    static boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ';
    }

    static boolean checkWin(char player) {
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }

        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
               (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }

    static boolean isBoardFull() {
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == ' ') return false;
            }
        }
        return true;
    }
}