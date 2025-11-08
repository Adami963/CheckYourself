import java.util.*;
import java.io.Serializable;

/**
 * Teammate 2: Game state + rules (Phase 2: minimal logic).
 * - Maintains board state (Piece[][])
 * - Click-to-move to ANY square (no chess rules), blocks moving onto same-color
 * - Captures opponent pieces (including King -> game over)
 * - Move history + undo
 * - Save/Load via GameState
 *
 * Uses GUIChess.Piece, GUIChess.Move, GUIChess.GameState types from GUIChess.java.
 */
public class GameLogic {

    private GUIChess.Piece[][] board = new GUIChess.Piece[8][8];
    private boolean isWhiteTurn = true;
    private boolean gameOver = false;
    private String winner = null;

    private final List<GUIChess.Move> moveHistory = new ArrayList<>();
    private final List<GUIChess.Piece> capturedWhite = new ArrayList<>();
    private final List<GUIChess.Piece> capturedBlack = new ArrayList<>();
    private String lastMoveDescription = "";

    public void initializeNewGame() {
        isWhiteTurn = true;
        gameOver = false;
        winner = null;
        moveHistory.clear();
        capturedWhite.clear();
        capturedBlack.clear();
        setupStartingPosition();
        lastMoveDescription = "New game started.";
    }

    public boolean handleMove(int fromRow, int fromCol, int toRow, int toCol) {
        if (gameOver) return false;
        if (!inBounds(fromRow, fromCol) || !inBounds(toRow, toCol)) return false;

        GUIChess.Piece mover = board[fromRow][fromCol];
        if (mover == null) return false;

        if (isWhiteTurn && mover.getColor() != GUIChess.Piece.Color.WHITE) return false;
        if (!isWhiteTurn && mover.getColor() != GUIChess.Piece.Color.BLACK) return false;

        GUIChess.Piece target = board[toRow][toCol];
        if (target != null && target.getColor() == mover.getColor()) {
            return false;
        }

        board[toRow][toCol] = mover;
        board[fromRow][fromCol] = null;

        GUIChess.Piece captured = null;
        if (target != null) {
            captured = target;
            if (target.getColor() == GUIChess.Piece.Color.WHITE) {
                capturedWhite.add(target);
            } else {
                capturedBlack.add(target);
            }
        }

        GUIChess.Move mv = new GUIChess.Move(fromRow, fromCol, toRow, toCol, mover, captured);
        moveHistory.add(mv);
        lastMoveDescription = describeMove(mv);

        if (captured != null && captured.getType() == GUIChess.Piece.Type.KING) {
            gameOver = true;
            winner = (mover.getColor() == GUIChess.Piece.Color.WHITE) ? "White" : "Black";
        }

        if (!gameOver) isWhiteTurn = !isWhiteTurn;
        return true;
    }

    public boolean undoMove() {
        if (moveHistory.isEmpty()) return false;
        GUIChess.Move mv = moveHistory.remove(moveHistory.size() - 1);

        board[mv.fromRow][mv.fromCol] = mv.pieceMoved;
        board[mv.toRow][mv.toCol] = null;

        if (mv.pieceCaptured != null) {
            board[mv.toRow][mv.toCol] = mv.pieceCaptured;
            if (mv.pieceCaptured.getColor() == GUIChess.Piece.Color.WHITE) {
                removeLastFromList(capturedWhite, mv.pieceCaptured);
            } else {
                removeLastFromList(capturedBlack, mv.pieceCaptured);
            }
        }

        gameOver = false;
        winner = null;
        isWhiteTurn = (mv.pieceMoved.getColor() == GUIChess.Piece.Color.WHITE);
        lastMoveDescription = moveHistory.isEmpty() ? "Undo: back to start position."
                : "Undo: " + describeMove(moveHistory.get(moveHistory.size() - 1));
        return true;
    }

    public GUIChess.Piece[][] getBoardState() { return copyBoard(board); }
    public String getLastMoveDescription() { return lastMoveDescription; }
    public boolean isGameOver() { return gameOver; }
    public String getWinner() { return winner; }
    public List<GUIChess.Move> getMoveHistory() { return new ArrayList<>(moveHistory); }
    public List<GUIChess.Piece> getCapturedWhitePieces() { return new ArrayList<>(capturedWhite); }
    public List<GUIChess.Piece> getCapturedBlackPieces() { return new ArrayList<>(capturedBlack); }

    public GUIChess.GameState getGameState() {
        GUIChess.GameState gs = new GUIChess.GameState();
        gs.board = copyBoard(board);
        gs.isWhiteTurn = isWhiteTurn;
        gs.gameOver = gameOver;
        gs.winner = winner;
        gs.moveHistory = new ArrayList<>(moveHistory);
        gs.capturedWhitePieces = new ArrayList<>(capturedWhite);
        gs.capturedBlackPieces = new ArrayList<>(capturedBlack);
        return gs;
    }

    public void loadedGameState(GUIChess.GameState state) {
        if (state == null) return;
        board = copyBoard(state.board);
        isWhiteTurn = state.isWhiteTurn;
        gameOver = state.gameOver;
        winner = state.winner;
        moveHistory.clear();
        capturedWhite.clear();
        capturedBlack.clear();
        if (state.moveHistory != null) moveHistory.addAll(state.moveHistory);
        if (state.capturedWhitePieces != null) capturedWhite.addAll(state.capturedWhitePieces);
        if (state.capturedBlackPieces != null) capturedBlack.addAll(state.capturedBlackPieces);
        lastMoveDescription = moveHistory.isEmpty() ? "Game loaded."
                : "Game loaded. Last: " + describeMove(moveHistory.get(moveHistory.size() - 1));
    }

    private static boolean inBounds(int r, int c) { return r >= 0 && r < 8 && c >= 0 && c < 8; }
    private static GUIChess.Piece[][] copyBoard(GUIChess.Piece[][] src) {
        if (src == null) return new GUIChess.Piece[8][8];
        GUIChess.Piece[][] out = new GUIChess.Piece[8][8];
        for (int r = 0; r < 8; r++) System.arraycopy(src[r], 0, out[r], 0, 8);
        return out;
    }
    private static void removeLastFromList(List<GUIChess.Piece> list, GUIChess.Piece p) {
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i) == p) { list.remove(i); return; }
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            GUIChess.Piece q = list.get(i);
            if (q.getType() == p.getType() && q.getColor() == p.getColor()) {
                list.remove(i); return;
            }
        }
    }
    private String describeMove(GUIChess.Move mv) {
        String from = coord(mv.fromRow, mv.fromCol);
        String to = coord(mv.toRow, mv.toCol);
        String mover = mv.pieceMoved.getColor() + " " + mv.pieceMoved.getType();
        String cap = (mv.pieceCaptured == null) ? "" : " x " + mv.pieceCaptured.getColor() + " " + mv.pieceCaptured.getType();
        return mover + " " + from + " → " + to + cap;
    }
    private static String coord(int row, int col) {
        char file = (char) ('a' + col);
        int rank = 8 - row;
        return "" + file + rank;
    }
    private void setupStartingPosition() {
        board = new GUIChess.Piece[8][8];
        var W = GUIChess.Piece.Color.WHITE;
        var B = GUIChess.Piece.Color.BLACK;
        var K = GUIChess.Piece.Type.KING;
        var Q = GUIChess.Piece.Type.QUEEN;
        var R = GUIChess.Piece.Type.ROOK;
        var N = GUIChess.Piece.Type.KNIGHT;
        var Bp = GUIChess.Piece.Type.BISHOP;
        var P = GUIChess.Piece.Type.PAWN;

        board[0][0] = new GUIChess.Piece(R, B);
        board[0][1] = new GUIChess.Piece(N, B);
        board[0][2] = new GUIChess.Piece(Bp, B);
        board[0][3] = new GUIChess.Piece(Q, B);
        board[0][4] = new GUIChess.Piece(K, B);
        board[0][5] = new GUIChess.Piece(Bp, B);
        board[0][6] = new GUIChess.Piece(N, B);
        board[0][7] = new GUIChess.Piece(R, B);

        for (int c = 0; c < 8; c++) board[1][c] = new GUIChess.Piece(P, B);
        for (int c = 0; c < 8; c++) board[6][c] = new GUIChess.Piece(P, W);

        board[7][0] = new GUIChess.Piece(R, W);
        board[7][1] = new GUIChess.Piece(N, W);
        board[7][2] = new GUIChess.Piece(Bp, W);
        board[7][3] = new GUIChess.Piece(Q, W);
        board[7][4] = new GUIChess.Piece(K, W);
        board[7][5] = new GUIChess.Piece(Bp, W);
        board[7][6] = new GUIChess.Piece(N, W);
        board[7][7] = new GUIChess.Piece(R, W);
    }
}
