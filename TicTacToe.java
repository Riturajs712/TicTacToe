import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    static char userSymbol;
    static char computerSymbol;
    static char currentPlayer;
    static Scanner scanner = new Scanner(System.in);

    public static void initializeBoard(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    public static void printBoard(char[][] board) {
        System.out.println("Current Board:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void toss() {
        Random rand = new Random();
        int tossResult = rand.nextInt(2);

        if (tossResult == 0) {
            currentPlayer = 'U';
            userSymbol = 'X';
            computerSymbol = 'O';
            System.out.println("User won the toss and plays first (X)");
        } else {
            currentPlayer = 'C';
            computerSymbol = 'X';
            userSymbol = 'O';
            System.out.println("Computer won the toss and plays first (X)");
        }
    }

    public static int getUserMove() {
        System.out.print("Enter a slot number (1-9): ");
        return scanner.nextInt();
    }

    public static int[] convertSlotToIndex(int slot) {
        int row = (slot - 1) / 3;
        int col = (slot - 1) % 3;
        return new int[]{row, col};
    }

    public static boolean isValidMove(char[][] board, int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-';
    }

    public static void placeMove(char[][] board, int row, int col, char symbol) {
        board[row][col] = symbol;
    }

    public static void main(String[] args) {
        char[][] board = new char[3][3];

        initializeBoard(board);
        printBoard(board);

        toss();

        int userMove = getUserMove();
        int[] index = convertSlotToIndex(userMove);

        if (isValidMove(board, index[0], index[1])) {
            placeMove(board, index[0], index[1], userSymbol);
            printBoard(board);
        } else {
            System.out.println("Invalid Move");
        }
    }
}