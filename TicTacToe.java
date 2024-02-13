import java.util.Scanner;

public class TicTacToe {
    private char[][] board;
    private char currentPlayer;

    public TicTacToe() {
        board = new char[3][3];
        currentPlayer = 'X';
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    public void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkWin() {
        return (checkRows() || checkColumns() || checkDiagonals());
    }

    private boolean checkRows() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[i][0], board[i][1], board[i][2])) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumns() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[0][i], board[1][i], board[2][i])) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonals() {
        return (checkRowCol(board[0][0], board[1][1], board[2][2]) ||
                checkRowCol(board[0][2], board[1][1], board[2][0]));
    }

    private boolean checkRowCol(char c1, char c2, char c3) {
        return ((c1 != '-') && (c1 == c2) && (c2 == c3));
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        int row, col;
        boolean playAgain = true;

        while (playAgain) {
            initializeBoard();
            while (!isBoardFull() && !checkWin()) {
                printBoard();
                System.out.println("Player " + currentPlayer + ", enter your move (row [0-2] and column [0-2]): ");
                if (scanner.hasNextInt()) {
                    row = scanner.nextInt();
                    if (scanner.hasNextInt()) {
                        col = scanner.nextInt();
                        if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-') {
                            board[row][col] = currentPlayer;
                            if (checkWin()) {
                                printBoard();
                                System.out.println("Player " + currentPlayer + " wins!");
                                break;
                            }
                            switchPlayer();
                        } else {
                            System.out.println("Invalid move! Try again.");
                            scanner.nextLine(); // Consume the newline character
                        }
                    } else {
                        System.out.println("Invalid input! Try again.");
                        scanner.nextLine(); // Consume the invalid input
                    }
                } else {
                    System.out.println("Invalid input! Try again.");
                    scanner.nextLine(); // Consume the invalid input
                }
            }

            if (!checkWin()) {
                printBoard();
                System.out.println("It's a draw!");
            }

            boolean validAnswer = false;
            while (!validAnswer) {
                System.out.println("Do you want to play again? (yes/no): ");
                String answer = scanner.next().toLowerCase();
                if (answer.equals("yes") || answer.equals("no")) {
                    playAgain = answer.equals("yes");
                    validAnswer = true;
                } else {
                    System.out.println("Invalid answer! Please type 'yes' or 'no'.");
                }
            }
        }

        scanner.close();
        System.out.println("Bye!");
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.playGame();
    }
}

