package Phase3.src.gui;
import java.util.*;



import java.io.Serializable;

import Phase3.src.board.Board;
import Phase3.src.board.Position;
import Phase3.src.pieces.*;




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
//Dev-A
    private Board chessBoard = new Board();

    private GuiChess.Piece[][] board = new GuiChess.Piece[8][8];
    private boolean isWhiteTurn = true;
    private boolean gameOver = false;
    private String winner = null;

    private final List<GuiChess.Move> moveHistory = new ArrayList<>();
    private final List<GuiChess.Piece> capturedWhite = new ArrayList<>();
    private final List<GuiChess.Piece> capturedBlack = new ArrayList<>();
    private String lastMoveDescription = "";

    public void initializeNewGame() {
        isWhiteTurn = true;
        gameOver = false;
        winner = null;
        moveHistory.clear();
        capturedWhite.clear();
        capturedBlack.clear();
        setupStartingPosition();
        //lastMoveDescription = "New game started.";

        //Dev-A Modification
        chessBoard = new Board(); 

        SyncBoards();
        lastMoveDescription = "New game started.";
    }

//Dev-A modification
    public boolean handleMove(int fromRow, int fromCol, int toRow, int toCol) {
        if (gameOver) return false;
        if (!inBounds(fromRow, fromCol) || !inBounds(toRow, toCol)) return false;

        //Dev-A
        Position fromPos = new Position(fromRow, fromCol);
        Position toPos = new Position(toRow, toCol);

        Piece phase1Piece = chessBoard.getPiece(fromPos);
        if (phase1Piece == null) return false;

        if((isWhiteTurn && phase1Piece.getColor() != Color.WHITE)||
        (!isWhiteTurn && phase1Piece.getColor() !=Color.BLACK)){
            return false;
        }

        List<Position> possibleMoves = phase1Piece.possibleMove();
        if(!possibleMoves.contains(toPos)){
            return false;
        }

        Piece targetPiece = chessBoard.getPiece(toPos);
        if(targetPiece != null && targetPiece.getColor() == phase1Piece.getColor()){
            return false;
        }

        GuiChess.Piece mover = board[fromRow][fromCol];
        if (mover == null) return false;

        if (isWhiteTurn && mover.getColor() != GuiChess.Piece.Color.WHITE) return false;
        if (!isWhiteTurn && mover.getColor() != GuiChess.Piece.Color.BLACK) return false;

        GuiChess.Piece target = board[toRow][toCol];
        if (target != null && target.getColor() == mover.getColor()) {
            return false;
        }

        chessBoard.movePiece(fromPos, toPos);//Dev-A

        board[toRow][toCol] = mover;
        board[fromRow][fromCol] = null;

        GuiChess.Piece captured = null;
        if (target != null) {
            captured = target;
            if (target.getColor() == GuiChess.Piece.Color.WHITE) {
                capturedWhite.add(target);
            } else {
                capturedBlack.add(target);
            }
        }

        GuiChess.Move mv = new GuiChess.Move(fromRow, fromCol, toRow, toCol, mover, captured);
        moveHistory.add(mv);
        lastMoveDescription = describeMove(mv);

        if (captured != null && captured.getType() == GuiChess.Piece.Type.KING) {
            gameOver = true;
            winner = (mover.getColor() == GuiChess.Piece.Color.WHITE) ? "White" : "Black";
        }

        if (!gameOver) isWhiteTurn = !isWhiteTurn;
        return true;
    }

    private void SyncBoards(){

        board = new GuiChess.Piece[8][8];

        for(int r = 0; r < 8; r++){
            for(int c = 0; c < 8; c++){
                Position pos = new Position(r, c);
                Piece phase1Piece = chessBoard.getPiece(pos);

                if(phase1Piece != null){
                    board[r][c] = convertToGuiPiece(phase1Piece);
                }
            }
        }
    }

    private GuiChess.Piece convertToGuiPiece(Piece phase1Piece){
        GuiChess.Piece.Color color = (phase1Piece.getColor() == Color.WHITE) ? GuiChess.Piece.Color.WHITE : GuiChess.Piece.Color.BLACK;

        GuiChess.Piece.Type type;

        if(phase1Piece instanceof Pawn){
            type = GuiChess.Piece.Type.PAWN;
        }else if (phase1Piece instanceof Rook){
            type = GuiChess.Piece.Type.ROOK;
        } else if (phase1Piece instanceof Knight){
            type = GuiChess.Piece.Type.KNIGHT;
        }else if (phase1Piece instanceof Bishop){
            type = GuiChess.Piece.Type.BISHOP;
        }else if (phase1Piece instanceof Queen){
            type = GuiChess.Piece.Type.QUEEN;
        }else if (phase1Piece instanceof King){
            type = GuiChess.Piece.Type.KING;
        } else{
            type = GuiChess.Piece.Type.PAWN;
        }

        return new GuiChess.Piece(type,color);

    }

    public boolean undoMove() {
        if (moveHistory.isEmpty()) return false;
        GuiChess.Move mv = moveHistory.remove(moveHistory.size() - 1);

        Position fromPos = new Position(mv.fromRow, mv.fromCol);
        Position toPos = new Position(mv.toRow, mv.toCol);

   

        Piece phase1Piece = chessBoard.getPiece(toPos);
        if(phase1Piece != null){
            chessBoard.setPiece(fromPos, phase1Piece);
            phase1Piece.setPosition(fromPos);
            chessBoard.setPiece(toPos, null);
        }

        if(mv.pieceCaptured != null){
            Piece restoredPiece = convertToPhase1Piece(mv.pieceCaptured, toPos);
            chessBoard.setPiece(toPos, restoredPiece);
        }

        board[mv.fromRow][mv.fromCol] = mv.pieceMoved;
        board[mv.toRow][mv.toCol] = null;



        if (mv.pieceCaptured != null) {
            board[mv.toRow][mv.toCol] = mv.pieceCaptured;
            if (mv.pieceCaptured.getColor() == GuiChess.Piece.Color.WHITE) {
                removeLastFromList(capturedWhite, mv.pieceCaptured);
            } else {
                removeLastFromList(capturedBlack, mv.pieceCaptured);
            }
        }

        gameOver = false;
        winner = null;
        isWhiteTurn = (mv.pieceMoved.getColor() == GuiChess.Piece.Color.WHITE);
        lastMoveDescription = moveHistory.isEmpty() ? "Undo: back to start position."
                : "Undo: " + describeMove(moveHistory.get(moveHistory.size() - 1));
        return true;
    }
private Piece convertToPhase1Piece(GuiChess.Piece guiPiece,Position position){
    Color color = (guiPiece.getColor() == GuiChess.Piece.Color.WHITE) ? Color.WHITE : Color.BLACK;

    switch(guiPiece.getType()){
        case PAWN: return new Pawn(color, position);
        case ROOK: return new Rook(color, position);
        case KNIGHT: return new Knight(color, position);
        case BISHOP: return new Bishop(color, position);
        case QUEEN: return new Queen(color, position);
        case KING: return new King(color, position);
        default: return new Pawn(color, position);
    }
}

    public GuiChess.Piece[][] getBoardState() { return copyBoard(board); }
    public String getLastMoveDescription() { return lastMoveDescription; }
    public boolean isGameOver() { return gameOver; }
    public String getWinner() { return winner; }
    public List<GuiChess.Move> getMoveHistory() { return new ArrayList<>(moveHistory); }
    public List<GuiChess.Piece> getCapturedWhitePieces() { return new ArrayList<>(capturedWhite); }
    public List<GuiChess.Piece> getCapturedBlackPieces() { return new ArrayList<>(capturedBlack); }

    public GuiChess.GameState getGameState() {
        GuiChess.GameState gs = new GuiChess.GameState();
        gs.board = copyBoard(board);
        gs.isWhiteTurn = isWhiteTurn;
        gs.gameOver = gameOver;
        gs.winner = winner;
        gs.moveHistory = new ArrayList<>(moveHistory);
        gs.capturedWhitePieces = new ArrayList<>(capturedWhite);
        gs.capturedBlackPieces = new ArrayList<>(capturedBlack);
        return gs;
    }

    public void loadedGameState(GuiChess.GameState state) {
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
    private static GuiChess.Piece[][] copyBoard(GuiChess.Piece[][] src) {
        if (src == null) return new GuiChess.Piece[8][8];
        GuiChess.Piece[][] out = new GuiChess.Piece[8][8];
        for (int r = 0; r < 8; r++) System.arraycopy(src[r], 0, out[r], 0, 8);
        return out;
    }
    private static void removeLastFromList(List<GuiChess.Piece> list, GuiChess.Piece p) {
        for (int i = list.size() - 1; i >= 0; i--) {
            GuiChess.Piece current = list.get(i);

            if (list.get(i) == p) { list.remove(i); 
                return; }
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            GuiChess.Piece current = list.get(i);
            if (current.getType() == current.getType() && current.getColor() == p.getColor()) {
                list.remove(i); 
                return;
            }
        }
    }
    private String describeMove(GuiChess.Move mv) {
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
        board = new GuiChess.Piece[8][8];
        var W = GuiChess.Piece.Color.WHITE;
        var B = GuiChess.Piece.Color.BLACK;
        var K = GuiChess.Piece.Type.KING;
        var Q = GuiChess.Piece.Type.QUEEN;
        var R = GuiChess.Piece.Type.ROOK;
        var N = GuiChess.Piece.Type.KNIGHT;
        var Bp = GuiChess.Piece.Type.BISHOP;
        var P = GuiChess.Piece.Type.PAWN;

        board[0][0] = new GuiChess.Piece(R, B);
        board[0][1] = new GuiChess.Piece(N, B);
        board[0][2] = new GuiChess.Piece(Bp, B);
        board[0][3] = new GuiChess.Piece(Q, B);
        board[0][4] = new GuiChess.Piece(K, B);
        board[0][5] = new GuiChess.Piece(Bp, B);
        board[0][6] = new GuiChess.Piece(N, B);
        board[0][7] = new GuiChess.Piece(R, B);

        for (int c = 0; c < 8; c++) board[1][c] = new GuiChess.Piece(P, B);
        for (int c = 0; c < 8; c++) board[6][c] = new GuiChess.Piece(P, W);

        board[7][0] = new GuiChess.Piece(R, W);
        board[7][1] = new GuiChess.Piece(N, W);
        board[7][2] = new GuiChess.Piece(Bp, W);
        board[7][3] = new GuiChess.Piece(Q, W);
        board[7][4] = new GuiChess.Piece(K, W);
        board[7][5] = new GuiChess.Piece(Bp, W);
        board[7][6] = new GuiChess.Piece(N, W);
        board[7][7] = new GuiChess.Piece(R, W);
    }
}
