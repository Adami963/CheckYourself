package chess.pieces;

import chess.board.Board;
import chess.board.Position;
import java.util.Collections;
import java.util.List;

/**
 * Pawn: rules coming later. For now it just exists and prints a token.
 */
public class Pawn extends Piece {
    public Pawn(Color color, Position position) {
        super(color, position);
    }
    @Override public String token() {
        return (color == Color.WHITE ? "w" : "b") + "P";
    }
    @Override public List<Position> possibleMoves(Board board) {
        // TODO Phase 2/3: add real Pawn moves
        return Collections.emptyList();
    }
}
