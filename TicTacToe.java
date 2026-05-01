import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    static char userSymbol;
    static char computerSymbol;
    static char currentPlayer;
    static Scanner scanner = new Scanner(System.in);
    static Random rand = new Random();

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

    public static void computerMove(char[][] board) {
        while (true) {
            int slot = rand.nextInt(9) + 1;
            int[] index = convertSlotToIndex(slot);

            if (isValidMove(board, index[0], index[1])) {
                placeMove(board, index[0], index[1], computerSymbol);
                System.out.println("Computer selected slot: " + slot);
                break;
            }
        }
    }

    public static boolean checkWin(char[][] board, char symbol) {
        for (int i = 0; i < 3; i++) {
            boolean rowWin = true;
            boolean colWin = true;

            for (int j = 0; j < 3; j++) {
                if (board[i][j] != symbol) {
                    rowWin = false;
                }
                if (board[j][i] != symbol) {
                    colWin = false;
                }
            }

            if (rowWin || colWin) {
                return true;
            }
        }

        boolean diag1 = true;
        boolean diag2 = true;

        for (int i = 0; i < 3; i++) {
            if (board[i][i] != symbol) {
                diag1 = false;
            }
            if (board[i][2 - i] != symbol) {
                diag2 = false;
            }
        }

        return diag1 || diag2;
    }

    public static boolean isBoardFull(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-')
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] board = new char[3][3];

        initializeBoard(board);
        printBoard(board);

        toss();

        while (true) {

            if (currentPlayer == 'U') {
                int userMove = getUserMove();
                int[] index = convertSlotToIndex(userMove);

                if (isValidMove(board, index[0], index[1])) {
                    placeMove(board, index[0], index[1], userSymbol);
                    printBoard(board);

                    if (checkWin(board, userSymbol)) {
                        System.out.println("User Wins!");
                        break;
                    }

                    if (isBoardFull(board)) {
                        System.out.println("Game Draw!");
                        break;
                    }

                    currentPlayer = 'C';
                } else {
                    System.out.println("Invalid Move");
                }

            } else {
                computerMove(board);
                printBoard(board);

                if (checkWin(board, computerSymbol)) {
                    System.out.println("Computer Wins!");
                    break;
                }

                if (isBoardFull(board)) {
                    System.out.println("Game Draw!");
                    break;
                }

                currentPlayer = 'U';
            }
        }
    }
}
