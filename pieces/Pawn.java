/** 
 * This is the pawn piece
 * special forward move, diagonal caputes and special abilities
 */

package pieces;
import java.util.ArrayList;
import java.util.List;

import board.Position;

public class Pawn extends Piece{
    
    public Pawn(Color color, Position position){
        super(color, position);
    }

   /**
    * All possible pawn moves
    */
    @Override
    public List<Position> possibleMove() {
        List<Position> moves = new ArrayList<>();

        int currentRow = position.getRow();
        int currentCol = position.getColumn();

        int direction = getDirection();

        /*
         * single move forward
         */
        addForwardMove(moves, currentRow, currentCol, direction, 1);

        /*
         * double move forward if its a first move
         */
        if(!hasMoved){
            addForwardMove(moves, currentRow, currentCol, direction, 2);
        }
        /*
         * diagonal captures
         */
        addCaptureMoves(moves, currentRow, currentCol, direction);

        return moves;

    }

    /**
     * Gets the direction of the movement based on pawn color
     * whites moves up, blacks move down
     * @return 1 for black piece, -1 for white piece
     */
    private int getDirection(){
        return(color == Color.WHITE) ? -1 : 1;
    }

    /**
     * Adds a forward move if within boundaries
     * @param moves the list to add a move
     * @param currentRow position
     * @param currentCol positon
     * @param direction movement in a specific direction
     * @param squares number of squares to move forward
     */
    private void addForwardMove(List<Position> moves, int currentRow, int currentCol, int direction, int squares){
        int newRow = currentRow + (direction * squares);
        if(newRow >= 0 && newRow < 8){
            moves.add(new Position(newRow, currentCol));
        }
    }


    /**
     * Add Diagonal captues if withing boundaries
     * @param moves the list to add a move
     * @param currentRow position
     * @param currentCol position
     * @param direction movement
     */
     private void addCaptureMoves(List<Position> moves, int currentRow, int currentCol, int direction){
        int newRow = currentRow + direction;
        
        /**
         * left diagonal caputure
         */
        if(currentCol > 0){
            moves.add(new Position(newRow, currentCol - 1));
        }
        /**
         * right diagonal caputure
         */
        if(currentCol < 7){
            moves.add(new Position(newRow, currentCol + 1));
        }
    }


    /**
     * our text representation of the pawn
     * @return "wp" for white, "bp" for black
     */
    @Override
    public String getTextRepresentation(){
        return(color == Color.WHITE ? "w" : "b") + "p";
    }

}

