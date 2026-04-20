import java.util.Random;

public class TicTacToe {

    static char userSymbol;
    static char computerSymbol;
    static char currentPlayer;

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

    public static void main(String[] args) {
        char[][] board = new char[3][3];

        initializeBoard(board);
        printBoard(board);

        toss();

        System.out.println("User Symbol: " + userSymbol);
        System.out.println("Computer Symbol: " + computerSymbol);
        System.out.println("Current Turn: " + (currentPlayer == 'U' ? "User" : "Computer"));
    }
}