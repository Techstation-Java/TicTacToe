import java.util.Scanner;

public class TicTacToe {
    boolean gameActive = true;
    String[] board = {" ", " ", " ", " ", " ", " ", " ", " ", " "};
    int[][] winningPositions = new int[][]{{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {3, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    boolean XChance = true;
    int filledSlots = 0;

    public static void main(String[] args) {
        TicTacToe o1 = new TicTacToe();
        System.out.println("\nWelcome to 2 Player Tic Tac Toe."); // Printing the Welcome message
        System.out.println("--------------------------------");
        o1.printBoard(); // Printing the Board
        System.out.println("Info: Type 'exit' anytime to end the game.\n");
        o1.gameLogic();
    }


    public void gameLogic(){
        Scanner sc = new Scanner(System.in);
        int slot=0;
        String tempSlot = "";
        System.out.print("PLayer X will play first. Enter a slot number to place X in: ");
        while (filledSlots !=9 && gameActive) {
            try {
                tempSlot = sc.nextLine(); // Taking input as String to add 'exit; functionality; Part of Optional Assignment
                if(tempSlot.equalsIgnoreCase("exit")){ // If the user types 'exit', the game will end; Optional Assignment.
                    System.out.println("Good-Bye!!");
                    System.exit(0); // This funtion abruptly ends the program with the exit code 0
                }

                slot = Integer.valueOf(tempSlot) - 1; // Converting slot number to in type and subtracting 1 to get the slot number in 0-indexing
                if (!(slot >= 0 && slot < 9)) { // Check for Wrong slot number
                    System.out.println("Invalid input; re-enter slot number1: ");
                    continue;
                }
            }
            catch(Exception e){ // Check for non-int type data
                System.out.println("Invalid input; re-enter slot number: ");
                //sc.next();
                continue;
            }
            if(board[slot].equals(" ")){ // Checking whether the selected slot is empty
                filledSlots++;
                if(XChance){
                    XChance=false;
                    board[slot]="X";
                }
                else{
                    XChance=true;
                    board[slot]="O";
                }
                printBoard(); // Print the current sa=tate of the baord
                checkWinner(); // To check for the winner

            }
            else{ // if the selected slot is empty
                System.out.print("Slot already taken; re-enter slot number: ");
            }
        }
    }

    public void checkWinner() { // Checks if someone has won, and also who has won
        for (int[] winningPosition : winningPositions) {
            if (board[winningPosition[0]].equals(board[winningPosition[1]]) && board[winningPosition[1]].equals(board[winningPosition[2]]) && !board[winningPosition[1]].equals(" ")) {
                //gameActive = false;
                if(XChance) {
                    System.out.println("\"Congratulations! Player O won! Thanks for playing.\"");
                }
                else{
                    System.out.println("\"Congratulations! PLayer X won! Thanks for playing.\"");
                }
                gameActive=false;
                restart();
            }
        }
        if(filledSlots==9 && gameActive){ // If all the slots on the board are filled and we dont have a winner, it means it's a Draw.
            gameActive=false;
            System.out.println("It's a DRAW! Thanks for playing.");
            restart();
        }
        else if(gameActive){ // If we don't have a winner yet
            if(XChance)
                System.out.print("X's turn; enter a slot number to place X in:");
            else
                System.out.print("O's turn; enter a slot number to place O in:");
        }

    }

    public void restart(){ // Adds the functionality to restart the game, once it's over; Mandatory Assignment
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you wanna play again??? (Yes/No)");
        String choice = sc.nextLine();
        if(choice.equalsIgnoreCase("yes")){
            for(int i=0;i<9;i++)
                board[i]=" ";
            gameActive=true;
            XChance=true;
            filledSlots=0;
            printBoard();
            gameLogic();
        }
        else if(choice.equalsIgnoreCase("no"));

        else { // If the user inputs something other the yes or no
            System.out.println("Invalid Input; Tray Again!");
            restart();
        }
    }

    public void printBoard() { // Method to Print the Board.
        System.out.println("┌-----------┐");
        System.out.println("| " + board[0] + " | " + board[1] + " | " + board[2] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[3] + " | " + board[4] + " | " + board[5] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[6] + " | " + board[7] + " | " + board[8] + " |");
        System.out.println("└-----------┘");
    }
}