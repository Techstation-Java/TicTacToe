import java.util.Scanner;
class TicTacToe {
    boolean gameActive = true;
    String[] board = {" ", " ", " ", " ", " ", " ", " ", " ", " "};
    int[][] winningPositions = new int[][]{{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    boolean XChance = true;
    int filledSlots = 0;

    public static void main(String[] args) {
        TicTacToe o1 = new TicTacToe();
        System.out.println("\nWelcome to 2 PLayer Tic-Tac-Toe.");
        System.out.println("--------------------------------");
        o1.printBoard();
        o1.gameLogic();
    }

    public void gameLogic() {
        Scanner sc = new Scanner(System.in);
        int slot = 0;
        System.out.print("Player X will play first. Enter a slot number to place X in: ");
        while (filledSlots != 9 && gameActive) {
            try {
                slot = sc.nextInt() - 1;
                if (!(slot >= 0 && slot < 9)) {
                    System.out.print("Invalid input; re-enter slot number:");
                    continue;
                }
            } catch (Exception e) {
                System.out.print("Invalid input; re-enter slot number:");
                sc.next();
                continue;
            }
            if (board[slot].equals(" ")) {
                filledSlots++;
                if (XChance) {
                    board[slot] = "X";
                    XChance = false;
                } else {
                    board[slot] = "O";
                    XChance = true;
                }
                printBoard();
                checkWinner();
            } else {
                System.out.print("Slot already taken; re-enter slot number: ");
            }
        }
    }

    public void checkWinner() {
        for (int[] winningPosition : winningPositions) {
            if (board[winningPosition[0]].equals(board[winningPosition[1]]) && board[winningPosition[1]].equals(board[winningPosition[2]])
                    && !board[winningPosition[0]].equals(" ")) {
                if (XChance) {
                    System.out.println("\"Congratulations! Player O won! Thanks for playing.\"");
                } else {
                    System.out.println("\"Congratulations! Player X won! Thanks for playing.\"");
                }
                gameActive = false;
            }

        }
        if (filledSlots == 9 && gameActive) {
            gameActive = false;
            System.out.println("It's a DRAW! Thanks for playing.");
        }
        else if(gameActive) {
            if (XChance) {
                System.out.print("X's turn; enter a slot number to place X in:");
            } else {
                System.out.print("O's turn; enter a slot number to place O in:");
            }
        }
    }

    public void printBoard(){
        System.out.println("┌-----------┐"); // alt+218:┌  alt+191:┐
        System.out.println("| " + board[0] + " | " + board[1] + " | " + board[2] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[3] + " | " + board[4] + " | " + board[5] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[6] + " | " + board[7] + " | " + board[8] + " |");
        System.out.println("└-----------┘"); // alt+192:└  alt+217:┘
    }
}


