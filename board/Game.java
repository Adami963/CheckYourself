package chess;

import players.Player;
import pieces.Color;

import java.util.Scanner;

/*
 * JOB: coordinates board + 2 players & runs the main loop 
 * 
 * What actually happens tho:
 *      (1) Create a board (called in Board's setupInitial())
 *      (2) Create one Scanner and share it with both players
 *      (3) Loop (until win or quit):
 *              -- Display the board (Board.display())
 *              -- Tell who's turn it is
 *              -- Ask current player to make a move 
 *                      • If  'QUIT' --> Player.makeMove() returns false -- end loop
 *                      • if a move is made --> SWAP turns and continue
 */

public class Game {
    private Board board = new Board();

    //two players , created in start()
    private Player white;
    private Player black;

    //RULE: WHITE MOVES FIRST
    private Color currTurn = Color.WHITE;

    //SETUP: create 2 players that share board  
    public void start(){
        Scanner scanner = new Scanner(System.in);

        white = new Player(Color.WHITE, board, scanner);
        black = new Player(Color.BLACK, board, scanner);
    }//end of void start

    /*
     * MAIN GAME LOOP: show board, ask curr player to make move, swap turns
     * or exit if  'QUIT'
    */
    public void play(){
        boolean isRunning = true;

        while(isRunning){
            // (1) Show board 
            board.display();

            // (2) Announce whose turn it is
            System.out.println("Turn: " +  (currTurn == Color.WHITE ? "White" : "Black"));

            // (3) Ask player to make move 
            boolean moved;

            if(currTurn == Color.WHITE){
                moved = white.makeMove();
            }//end of if white turn
            else{
                moved = black.makeMove();
            }//end of black turn 

            //if moved == false, the player typed 'QUIT', or input auto shutdown 
            if(!moved){
                isRunning = false;
                break;
            }//end of if NO MOVE

            // (4) Swap turns 
            currTurn = (currTurn == Color.WHITE ? Color.BLACK : Color.WHITE);
        }//end of while still running 
        System.out.println("GAME OVER. Thank you for playing!");
    }//end of void play
}//end of class Game
import chess.board.Board;
import chess.board.Position;
import chess.utils.Utils;
import java.util.Scanner;

/**
 * Minimal console loop :
 * - Displays board
 * - Prompts a single move in format "E2 E4"
 * - Applies move without full legality checks
 */
public class Game {
    public static void main(String[] args) {
        Board board = new Board();
        Scanner sc = new Scanner(System.in);

        System.out.println("Console Chess (Phase One)");
        while (true) {
            board.display();
            System.out.print("Enter move (e.g., E2 E4) or 'q' to quit: ");
            String line = sc.nextLine();
            if (line == null) break;
            line = line.trim();
            if (line.equalsIgnoreCase("q")) break;
            if (!Utils.isBasicMoveFormat(line)) {
                System.out.println("Invalid format. Use like: E2 E4");
                continue;
            }
            String[] parts = line.split("\s+");
            Position from = Position.fromSquare(parts[0]);
            Position to = Position.fromSquare(parts[1]);
            boolean moved = board.movePiece(from, to);
            if (!moved) {
                System.out.println("No piece at " + from + ". Try again.");
            }
        }
        System.out.println("Goodbye!");
    }
}
