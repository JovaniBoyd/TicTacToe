import java.util.Scanner;

public class TicTacToe {
    Scanner scanner = new Scanner(System.in);

    enum CellState {
        X,
        O,
        EMPTY
    }

    enum GameState {
        WIN,
        DRAW,
        CONTINUE,

    }

    private CellState[][] board = new CellState[3][3];

    public TicTacToe() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c] = CellState.EMPTY;
            }

        }


        //changing the positions here for test
 /*      board[2][1] = CellState.X;
        board[2][0] = CellState.X;
        board[2][2] = CellState.O;
        board[0][2] = CellState.X;
        // board[1][0] = CellState.O;
        board[0][0] = CellState.X;
       //  board[1][1] = CellState.O;
         board[1][2] = CellState.X;
         board[0][1] = CellState.X; */
    }

    public void printBoard() {

        for (int r = 0; r < 3; r++) {
            if (r == 0) {
                System.out.println(" _____________________________");
            }
            if (r > 0) {
                System.out.println("|_________|_________|_________|");
            }
            System.out.println("|         |         |         |");
            for (int c = 0; c < 3; c++) {
                System.out.print("|");
                if (board[r][c].equals(CellState.EMPTY)) {
                    System.out.print("         ");
                }
                if (board[r][c].equals(CellState.X)) {
                    System.out.print("    X    ");
                }
                if (board[r][c].equals(CellState.O)) {
                    System.out.print("    O    ");
                }
                //fillEmpty(board[r][c]);
                //System.out.printf("%7s", "  " + board[r][c] + "  ");
            }
            System.out.print("|");
            System.out.println();

        }
        System.out.println("|_________|_________|_________|");
    }

    private boolean validMove(int row, int column) {
        if ((row <= 2 && row >= 0) && (column <= 2 && column >= 0) && board[row][column] == CellState.EMPTY) {// may later change from [0,2] to [1,3]
            return true;
        } else {
            return false;
        }
    }

    private GameState gameStatus() {
        //check for WIN first


        boolean isFull = true;
        for (int r = 0; r < 1; r++) {//might need r<=3 instead of r<3
            for (int c = 0; c < 1; c++) {
                for (int i = 0; i < 3; i++) {//got to figure out the value of i<
                    if ((board[r][i] == CellState.O && board[r + 1][i] == CellState.O && board[r + 2][i] == CellState.O) || (board[r][i] == CellState.X && board[r + 1][i] == CellState.X && board[r + 2][i] == CellState.X)) {
                        return GameState.WIN;
                    }
                    if ((board[i][c] == CellState.O && board[i][c + 1] == CellState.O && board[i][c + 2] == CellState.O) || (board[i][c] == CellState.X && board[i][c + 1] == CellState.X && board[i][c + 2] == CellState.X)) {
                        return GameState.WIN;
                    }
                    if ((board[r][c] == CellState.O && board[r + 1][c + 1] == CellState.O && board[r + 2][c + 2] == CellState.O) || (board[r][c] == CellState.X && board[r + 1][c + 1] == CellState.X && board[r + 2][c + 2] == CellState.X)) {
                        return GameState.WIN;
                    }
                    if ((board[r + 2][c] == CellState.O && board[r + 1][c + 1] == CellState.O && board[r][c + 2] == CellState.O) || (board[r + 2][c] == CellState.X && board[r + 1][c + 1] == CellState.X && board[r][c + 2] == CellState.X)) {
                        return GameState.WIN;
                    }
                }
            }
        }
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board[r][c] == CellState.EMPTY) {
                    isFull = false;
                    //  }else{
                    //       isFull = true;
                }

            }
        }


        if (!isFull) {
            return GameState.CONTINUE;
        } else {
            return GameState.DRAW;
        }


    }

    public void runTests() {
        board[2][0] = CellState.X;
        board[1][1] = CellState.O;
        board[0][2] = CellState.X;

        printBoard();

        if (validMove(-1, 2) == false) {
            System.out.println("correct");
        }
        if (validMove(0, 5) == false) {
            System.out.println("correct");
        }
        if (validMove(0, 2) == false) {
            System.out.println("correct");
        }
        if (validMove(0, 1) == true) {
            System.out.println("correct");
        }

        if (gameStatus() == GameState.CONTINUE) {
            System.out.println("CONTINUE");
        }
        board[0][0] = CellState.O;
        board[2][2] = CellState.X;
        board[1][2] = CellState.O;
        board[0][1] = CellState.X;
        board[1][1] = CellState.O;
        board[1][0] = CellState.X;
        board[2][1] = CellState.O;

        printBoard();

        if (gameStatus() == GameState.DRAW) {
            System.out.println("DRAW");
        }

        board[1][1] = CellState.X;

        printBoard();

        if (gameStatus() == GameState.WIN) {
            System.out.println("WIN");
        }
    }

    public void play() {
        // while game state equals "continue", print board after each turn
        // maybe add "enter name" at start and print out winner's name at the end
        System.out.println("Enter names");
        System.out.println("Player X?");
        String nameX = scanner.nextLine();
        System.out.println("Player O?");
        String nameO = scanner.nextLine();
        printBoard();
        while (gameStatus() == GameState.CONTINUE) {
            nextMoveX(nameX);
            printBoard();
            if (gameStatus() == GameState.WIN) {
                System.out.println(nameX + " Wins");
                break;
            }
            if (gameStatus() == GameState.DRAW) {
                System.out.println("Draw");
                break;
            }
            System.out.println();
            nextMoveO(nameO);
            printBoard();
            if (gameStatus() == GameState.WIN) {
                System.out.println(nameO + " Wins");
                break;
            }
            if (gameStatus() == GameState.DRAW) {
                System.out.println("Draw");
                break;
            }
        }
    }

    public void nextMoveX(String name) {
        while (true) {
            System.out.println(name + "'s turn");
            System.out.println("Enter Row (1, 2, or 3):");
            int row = Integer.valueOf(scanner.nextLine());
            System.out.println("Enter Column (1, 2, or 3):");
            int column = Integer.valueOf(scanner.nextLine());
            if (validMove(row - 1, column - 1) == false) {
                System.out.println("Invalid move: try again.");
            } else if (validMove(row - 1, column - 1) == true) {
                board[row - 1][column - 1] = CellState.X;
                break;
            }
        }
    }

    public void nextMoveO(String name) {
        while (true) {
            System.out.println(name + "'s turn");
            System.out.println("Enter Row (1, 2, or 3):");
            int row = Integer.valueOf(scanner.nextLine());
            System.out.println("Enter Column (1, 2, or 3):");
            int column = Integer.valueOf(scanner.nextLine());
            if (validMove(row - 1, column - 1) == false) {
                System.out.println("Invalid move: try again.");
            } else if (validMove(row - 1, column - 1) == true) {
                board[row - 1][column - 1] = CellState.O;
                break;
            }
        }
    }

}
