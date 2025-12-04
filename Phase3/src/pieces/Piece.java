package Phase3.src.pieces;

import java.util.List;

import Phase3.src.board.Position;

/**
 * represents the chess piece
 * base class for all chess pieces
 */

public abstract class Piece{
    protected Color color ; 
    protected Position position;
    protected boolean  hasMoved;
    

    /**
     * Constructor for class pieces
     * Color is the color of the piece
     * Position is the position of the piece
     */
    public Piece(Color color, Position position){
        this.color = color;
        this.position = position;
        this.hasMoved = false;
    }

    /*
     * Calculates all possible moves for a chess piece from its current position
     * @returns a list of all possible positions this piece can move to
     */
    public abstract List<Position> possibleMove();

    /*
     * moves piece to a new positon
     * @param newPosition is the destination where the pieces moves to
     */
    public void move(Position newPosition){
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

    public Position getPosition() { return position;}
    
    public void setPosition(Position position) { this.position = position;}


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