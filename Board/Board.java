package Board;

import pieces.*;

/**
 * 8x8 board that stores pieces and can print itself.
 */
public class Board {
    private Piece[][] grid = new Piece[8][8]; // [row][col]

    public Board() {
        setupInitial();
    }

    public Piece getPiece(Position pos) {
        return grid[pos.getRow()][pos.getColumn()];
    }

    public void setPiece(Position pos, Pawn piece) {
        grid[pos.getRow()][pos.getColumn()] = piece;
    }

    /**
     * Move a piece from -> to.
     * Returns false if there was nothing at 'from'.
     */
    public boolean movePiece(Position from, Position to) {
        Piece p = getPiece(from);
        if (p == null) return false;
        setPiece(to, (Pawn) p);    // capture happens automatically if target had a piece
        p.move(to);
        setPiece(from, null);
        return true;
    }

    /** Put pieces in the normal chess starting spots. */
    public final void setupInitial() {
        grid = new Piece[8][8];
        // Pawns
        for (int c = 0; c < 8; c++) {
            setPiece(new Position(6, c), new Pawn(Color.WHITE, new Position(6, c)));
            setPiece(new Position(1, c), new Pawn(Color.BLACK, new Position(1, c)));
        }
        // Rooks
        setPiece(new Position(7,0), new Rook(Color.WHITE, new Position(7,0)));
        setPiece(new Position(7,7), new Rook(Color.WHITE, new Position(7,7)));
        setPiece(new Position(0,0), new Rook(Color.BLACK, new Position(0,0)));
        setPiece(new Position(0,7), new Rook(Color.BLACK, new Position(0,7)));
        // Knights
        setPiece(new Position(7,1), new Knight(Color.WHITE, new Position(7,1)));
        setPiece(new Position(7,6), new Knight(Color.WHITE, new Position(7,6)));
        setPiece(new Position(0,1), new Knight(Color.BLACK, new Position(0,1)));
        setPiece(new Position(0,6), new Knight(Color.BLACK, new Position(0,6)));
        // Bishops
        setPiece(new Position(7,2), new Bishop(Color.WHITE, new Position(7,2)));
        setPiece(new Position(7,5), new Bishop(Color.WHITE, new Position(7,5)));
        setPiece(new Position(0,2), new Bishop(Color.BLACK, new Position(0,2)));
        setPiece(new Position(0,5), new Bishop(Color.BLACK, new Position(0,5)));
        // Queens
        setPiece(new Position(7,3), new Queen(Color.WHITE, new Position(7,3)));
        setPiece(new Position(0,3), new Queen(Color.BLACK, new Position(0,3)));
        // Kings
        setPiece(new Position(7,4), new King(Color.WHITE, new Position(7,4)));
        setPiece(new Position(0,4), new King(Color.BLACK, new Position(0,4)));
    }

    /**
     * Print the board to the console.
     * Empty squares show as "##" so it's easy to read.
     */
    public void display() {
        // Files header A..H
        System.out.print("    ");
        for (char f = 'A'; f <= 'H'; f++) System.out.print(" " + f + " ");
        System.out.println();

        for (int r = 0; r < 8; r++) {
            int rank = 8 - r;
            System.out.printf(" %d ", rank);
            for (int c = 0; c < 8; c++) {
                Piece p = grid[r][c];
                if (p == null) System.out.print(" ##");
                else System.out.printf(" %s", p.token());
            }
            System.out.printf("  %d%n", rank);
        }
        System.out.print("    ");
        for (char f = 'A'; f <= 'H'; f++) System.out.print(" " + f + " ");
        System.out.println();
    }
}
