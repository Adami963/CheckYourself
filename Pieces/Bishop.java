/**
 * This is the Bishop piece
 * Does the Diagonal movement
 */
package Pieces;

import java.util.ArrayList;
import java.util.List;

import utils.PositionUtils;

public class Bishop extends Pieces {

    public Bishop(Color color, PositionUtils position){
        super(color,position);
    }

    /**
     * All possible moves for the bishop
     */
    @Override
    public List<PositionUtils> possibleMove() {
       List<PositionUtils> moves = new ArrayList<>();

       int currentRow = position.getRow();
       int currentCol = position.getColumn();

       /**
        * all four diagonal directions for the bishop
        */
       for(int i = 1; currentRow - i >= 0 && currentCol - i >= 0; i++){
        moves.add(new PositionUtils(currentRow - i , currentCol - i));
       }

       for(int i = 1; currentRow - i >= 0 && currentCol + i < 8; i++){
        moves.add(new PositionUtils(currentRow - i, currentCol + i));
       }
       
       for(int i = 1; currentRow + i < 8 && currentCol - i >= 0; i++){
        moves.add(new PositionUtils(currentRow + i, currentCol - i));
       }
       for(int i = 1; currentRow + i < 8 && currentCol + i < 8; i++){
        moves.add(new PositionUtils(currentRow + i, currentCol + i));
       }


       return moves;
    }
    

    /**
     * our text representation of the bishop
     * @return "wB" for white, "bB" for black
     */
    @Override
    public String getTextRepresentation(){
        return(color == Color.WHITE ? "w" : "b") + "B";
    }

}


  

