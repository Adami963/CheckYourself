package pieces;

import java.util.List;

import Board.Board;
import Board.Position;

/**
 * Base class for all chess pieces.
 * We keep shared stuff here: color + where the piece is.
 * Real move rules get handled by the subclasses later.
 */
public abstract class Piece {
    protected final Color color;
    protected Position position;

    protected Piece(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    public Color getColor() { return color; }
    public Position getPosition() { return position; }

    /** Two-letter code for printing to console. Example: wP, bK. */
    public abstract String token();

    /**
     * Where can this piece go? For Phase 1, we just return an empty list
     * and fill this in during the rules phase.
     */
    public abstract List<Position> possibleMoves(Board board);

    /** Board calls this when it actually moves a piece. */
    public void moveTo(Position newPosition) {
        this.position = newPosition;
    }
}
