/**
 * This is a Rook piece
 * Only does horizontal and vertical movement 
 */
package Pieces;

import java.util.ArrayList;
import java.util.List;
import utils.PositionUtils;

public class Rook extends Pieces{
    

 
    public Rook(Color color, PositionUtils position){
        super(color, position);
    }

  

    /**
     * All possible Rook moves
     */
    @Override
    public List<PositionUtils> possibleMove() {
        List<PositionUtils> moves = new ArrayList<>();
        
        int currentRow = position.getRow();
        int currentCol = position.getColumn();

        /**
         * creates all horizontal moves
         */
       for(int col = 0; col < 8; col++){
        if(col != currentCol){
            moves.add(new PositionUtils(currentRow, col));
        }
       } 

       /*
        * creates all vertical moves
        */
       for(int row = 0; row < 8; row++){
        if(row != currentRow){
            moves.add(new PositionUtils(row, currentCol));
        }
       }
       return moves;
    }

    /**
     * our text representation of the rook
     * @return "wR" for white, "bR" for black
     */
    @Override
    public String getTextRepresentation(){
        return (color == Color.WHITE ? "w" : "b") + "R";
    }

}  
    
    
