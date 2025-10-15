package players;

import pieces.Color;
import pieces.Piece;
import utils.Notation;

import java.util.Scanner;

import Board.Board;
import Board.Position;

/*
 * Player-Driven console
 *      - Ask the user to type a move in the exact format: [FROM][TO]
 *          --> Example: [E2][E4]
 *      - Validate ONLY input formatting (CHANGE IN FUTURE)
 *      - Validate user's [FROM] piece (Make sure correct piece is used)
 *      - Ask the board to perform the move 
 * 
 * Game Design:
 *      - This method keeps prompting until either:
 *              (a) a move is made successfully    ---- return true
 *              (b) the player types 'quit'   ----- return false
 *         this way game only needs to check a boolean to determine if continue/end
 */

public class Player {

    private Color color;
    private Board board; 

    Scanner scanner = new Scanner(System.in); 

    //CONSTRUCTOR
    public Player(Color color, Board board, Scanner scanner){
        this.color = color;
        this.board = board;
        this.scanner = scanner;
    }//end of public Player

    //GETTERS
    public Color getColor(){
        return color;
    }//end of Color getColor

    /*
     * Keep asking this player for a move until:
     *      - they make a valid move (returns true)
     *      - they type 'quit' (returns false --> ends game)
     */
    public boolean makeMove(){
        while(true){
            String line;

            System.out.print("Enter your move ([FROM][TO], E.G., E2 E4) or 'QUIT' to end the game: ");
            line = scanner.nextLine();

            //CHECKPOINT: is entry empty?
            if(line == null){
                //auto shut down 
                System.out.println("No entry was inputted. Exiting...");
                return false;
            }

            //clean whitespace (trim ends, collapse multiple spaces, capitalize chars)
            line = line.trim().toUpperCase();

            //allow player to exit once 'quit' is inputted
            if(line.equalsIgnoreCase("QUIT")){
                System.out.println("Exitin the game...");
                return false; //tells games to stop in loop 
            }//end of if QUIT

            //CHECKPOINT: valid entry
            String[] pt = line.split("\\s+");
            if(pt.length != 2){
                System.out.println("Invalid input. Example of correct format: E2 E4");
                continue;
            }//end of if INVALID ENTRY

            String fromText = pt[0];
            String toText = pt[1];

            //CHECKPOINT: valid squares (A1...H8)
            if(!Notation.isSquare(fromText) || !Notation.isSquare(toText)){
                System.out.println("Bad square name. A valid entry include A1 -- H8 (e.g., E2 E4).");
                continue;
            }//if INVALID SQUARE

            //convert E2 --> (row, col) indicies for Board grid
            int[] fromRC = Notation.toRowCol(fromText);
            int[] toRC   = Notation.toRowCol(toText);

            Position from = new Position(fromRC[0], fromRC[1]);
            Position to   = new Position(toRC[0], toRC[1]);

            //CHECKPOINT: piece must exist at [FROM]
            Piece pieceAtFrom = board.getPiece(from);
            if(pieceAtFrom == null){
                System.out.println("No piece on " + fromText + ". Try again!");
            }//end of if FROM EMPTY

            //CHECKPOINT: piece must be current player's piece
            if(pieceAtFrom.getColor() != color){
                System.out.println("That is not your piece (" + fromText  + "). Try again!");
                continue;
            }//end of if WRONG COLOR 

            //PHASE ONE: ask Board to move/capture from --> to
            boolean moved = board.movePiece(from, to);

            if(!moved){
                System.out.println("Move failed. Try again!");
                continue;
            }//end of if NOT MOVED

            //move was SUCCESSFUL. Swap turns now
            return true;

        }//end of while loop

    }//boolean makeMove(



    
}
