import java.sql.SQLOutput;
import java.util.Scanner ;

public class Main {

    private static final Scanner scan = new Scanner(System.in) ;
    private static Board gameBoard ;

    public static void main(String[] args) {

        //initializing game board
        gameBoard = new Board() ;
        gameBoard.init();

        gameLoop() ;
    }

    //method for tracking main game loop
    private static void gameLoop() {
        System.out.println("Welcome to 2048!");
        System.out.println("Left - 'a' | Up - 'w' | Right - 'd' | Down - 's' | Quit - 'q'");
        gameBoard.printBoard();
        System.out.print("> ");

        String input ;

        //main game loop
        while(!(input = scan.next()).equals("q")) {
            processInput(input) ;
            gameBoard.printBoard();
            System.out.print("> ");
        }
    }

    //method for processing user input and shifting the board appropriately
    private static void processInput(String input) {
        input = input.strip() ;
        if(!input.equals("a") && !input.equals("s") && !input.equals("w") && !input.equals("d")) {
            System.out.println("Invalid move - no change to the board. Try again.");
        }
        else {
            Board.shiftBoard(input) ;
        }
    }
}
