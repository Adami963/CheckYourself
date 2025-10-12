package Pieces;

import utils.PositionUtils;
import java.util.List;

/**
 * represents the chess piece
 * base class for all chess pieces
 */

public abstract class Pieces{
    protected Color color ; 
    protected PositionUtils position;
    protected boolean  hasMoved;
    

    /**
     * Constructor for class pieces
     * Color is the color of the piece
     * Position is the position of the piece
     */
    public Pieces(Color color, PositionUtils position){
        this.color = color;
        this.position = position;
        this.hasMoved = false;
    }

    /*
     * Calculates all possible moves for a chess piece from its current position
     * @returns a list of all possible positions this piece can move to
     */
    public abstract List<PositionUtils> possibleMove();

    /*
     * moves piece to a new positon
     * @param newPosition is the destination where the pieces moves to
     */
    public void move(PositionUtils newPosition){
        this.position = newPosition;
        this.hasMoved = true;
    }

    /*
     * the text representation of the piece
     */
    public abstract String getTextRepresentation();

    /*
     * Getters & Setters
     */
    public Color getColor(){ return color; }

    public void setColor(Color color) {this.color = color;}

    public PositionUtils getPosition() { return position;}
    
    public void setPosition(PositionUtils position) { this.position = position;}


    /**
     * checks if the piece has moved from its insitial spot
     * @return true if peice has moved or false if it hasnt
     */
    public boolean hasMoved(){ return hasMoved; }

    public void sethasMoved(boolean hasMoved) { this.hasMoved = hasMoved; }

    @Override
    public String toString(){
        return getTextRepresentation() + " at " + position.toChessNotation();
    }




}