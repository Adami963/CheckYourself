/**
 * this is the king piece
 * Similiar to the queens logic but only one square movements */ 
package Phase3.src.pieces;

import java.util.ArrayList;
import java.util.List;

import Phase3.src.board.Position;

public class King extends Piece {
    

    public King(Color color, Position position){
        super(color, position);
    }

  
    /**
     * All possible moves for the king
     */
    @Override
    public List<Position> possibleMove(){
        List<Position> moves = new ArrayList<>();
        
        int currentRow = position.getRow();
        int currentCol = position.getColumn();

        /**
         * All directions that sorround the king in 8 squares
         */
        int[] rowOffsets = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] colOffsets = {-1, 0, 1, -1, 1, -1, 0, 1};

        /**
         * Checking each direction
         */
        for(int i = 0; i < rowOffsets.length; i++){
            int newRow = currentRow + rowOffsets[i];
            int newCol = currentCol + colOffsets[i];

            /**
             * if new postion is within boundaries
             */
            if(newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8){
                moves.add(new Position(newRow,newCol));
            }
        }

        return moves;
    }

    /**
     * our text representation of the king
     * @return "wK" for white, "bK" for black
     */
     @Override
    public String getTextRepresentation(){
        return(color == Color.WHITE ? "w" : "b") + "K";
    }
   
}


       

