/**
 * This is the Queen piece
 * combines the rook and bishop logic moves
 */
package pieces;
import java.util.ArrayList;
import java.util.List;

import board.Position;

public class Queen extends Piece {

    public Queen(Color color, Position position){
        super(color,position);
    }

    /**
     * All possible moves for the queen
     */
    @Override
    public List<Position> possibleMove() {
        List<Position> moves = new ArrayList<>();

        int currentRow = position.getRow();
        int currentCol = position.getColumn();

        /*
         * Rook-like move logic
         */

        for(int col = 0; col < 8; col++){
            if(col != currentCol){
                moves.add(new Position(currentRow, col));
            }
        }
        
        for(int row = 0; row < 8; row++){
            if(row!= currentRow){
                moves.add(new Position(row, currentCol));
            }
        }

        /*
         * Bishop-like move logic
         */

       for(int i = 1; currentRow - i >= 0 && currentCol - i >= 0; i++){
        moves.add(new Position(currentRow - i , currentCol - i));
       }

       for(int i = 1; currentRow - i >= 0 && currentCol + i < 8; i++){
        moves.add(new Position(currentRow - i, currentCol + i));
       }
       
       for(int i = 1; currentRow + i < 8 && currentCol - i >= 0; i++){
        moves.add(new Position(currentRow + i, currentCol - i));
       }
       for(int i = 1; currentRow + i < 8 && currentCol + i < 8; i++){
        moves.add(new Position(currentRow + i, currentCol + i));
       }

       return moves;
       
    }


    /**
     * our text representation of the queen
     * @return "wQ" for white, "bQ" for black
     */
    @Override
    public String getTextRepresentation(){
        return(color == Color.WHITE ? "w" : "b") + "Q";
    }
}


        
   