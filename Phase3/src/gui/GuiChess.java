package Phase3.src.gui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//Main do not change this
public class GuiChess extends JFrame{
    //core game
    private BoardGUI boardGUI;
    private GameLogic gameLogic;

    //extra features
    private MenuManager menuManager;
    private GameHistoryPanel historyPanel;

    //game state
    private boolean gameActive = true;
    //constructor
    public GuiChess(){
        super("chess Game");
        initializeComponents();
        setupGUI();
        startNewGame();
    }

    private void initializeComponents(){
        //core game components
        this.boardGUI = new BoardGUI();
        this.gameLogic = new GameLogic();

        //extra fetures
        this.menuManager = new MenuManager(this);
        this.historyPanel = new GameHistoryPanel();

        setupComponentConnections();
    }

    private void setupComponentConnections(){

        boardGUI.setMoveListener((fromRow, fromCol, toRow, toCol) -> {

            if (gameActive){
                boolean validMove = gameLogic.handleMove(fromRow, fromCol, toRow, toCol);
                if(validMove){
                    boardGUI.updateBoard(gameLogic.getBoardState());

                    historyPanel.addMove(gameLogic.getLastMoveDescription());
                    historyPanel.updateCapturedPieces(gameLogic.getCapturedWhitePieces(), gameLogic.getCapturedBlackPieces());

                    if(gameLogic.isGameOver()){
                        handleGameEnd();
                    }
                }
            }

        });
            
        

        historyPanel.setUndoListener(e -> {
            if(gameLogic.undoMove()){
                boardGUI.updateBoard(gameLogic.getBoardState());
                historyPanel.updateMoveHistory(gameLogic.getMoveHistory());
                historyPanel.updateCapturedPieces(gameLogic.getCapturedWhitePieces(), gameLogic.getCapturedBlackPieces());

                gameActive = true;
            }
        });
    }

    private void setupGUI(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        setJMenuBar(menuManager.createMenuBar());

        add(boardGUI.getBoardPanel(), BorderLayout.CENTER);
        add(historyPanel.getPanel(), BorderLayout.EAST);

        pack();
        setLocationRelativeTo(null);
    }

    public void startNewGame(){
        gameLogic.initializeNewGame();
        boardGUI.initializeBoard();
        historyPanel.clearHistory();
        gameActive = true;
    }

    private void handleGameEnd(){
        gameActive = false;
        String winner = gameLogic.getWinner();
        JOptionPane.showMessageDialog(this, "Game Over! " + winner + " wins!", "Game Finished", JOptionPane.INFORMATION_MESSAGE);

    }

    public void saveGame(){
        menuManager.saveGame(gameLogic.getGameState());
    }

    public void loadGame(){
        GameState loadedState = menuManager.loadGame();
        if(loadedState != null) {
            gameLogic.loadedGameState(loadedState);
            boardGUI.updateBoard(gameLogic.getBoardState());
            historyPanel.updateMoveHistory(gameLogic.getMoveHistory());
            historyPanel.updateCapturedPieces(
                gameLogic.getCapturedWhitePieces(), 
                gameLogic.getCapturedBlackPieces()
            );

            gameActive =!gameLogic.isGameOver();
        }
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            GuiChess chessGame = new GuiChess();
            chessGame.setVisible(true);
        });
    }
//interface for the notification from boardGUI to gameLogic
    interface MoveListener {
        void onMoveMade(int fromRow, int fromCol, int toRow, int toCol);
    }

    //Teammate 1: All visual of the chess board 
    //Display 8x8 chessboard with alternating light/dark squares
     //Display chess pieces on their initial positions  
     //Handle user interactions (click and move)
     //Visually update the board after moves
     //Highlight selected pieces


//Teammate 2: all game rules and state of game
     //Maintain current board state
     //Handle piece movement logic  
     //Detect captures and update piece positions
     //Check for game end conditions (king capture)
     //Manage game history for undo functionality

//both teammates can use this  data structures
    
    static class Piece implements Serializable {
        private static final long serialVersionUID = 1L;
        
        public enum Type {
            KING, QUEEN, ROOK, BISHOP, KNIGHT, PAWN
        }
        
        public enum Color {
            WHITE, BLACK
        }
        
        private Type type;
        private Color color;
        
        public Piece(Type type, Color color) {
            this.type = type;
            this.color = color;
        }
        
        public Type getType() { return type; }
        public Color getColor() { return color; }
        
       
        public String getSymbol() {
            switch(type) {
                case KING: return color == Color.WHITE ? "♔" : "♚";
                case QUEEN: return color == Color.WHITE ? "♕" : "♛";
                case ROOK: return color == Color.WHITE ? "♖" : "♜";
                case BISHOP: return color == Color.WHITE ? "♗" : "♝";
                case KNIGHT: return color == Color.WHITE ? "♘" : "♞";
                case PAWN: return color == Color.WHITE ? "♙" : "♟";
                default: return "";
            }
        }
        
        @Override
        public String toString() {
            return color + " " + type;
        }
    }
    
    
    static class Move implements Serializable {
        private static final long serialVersionUID = 1L;
        int fromRow, fromCol, toRow, toCol;
        Piece pieceMoved;
        Piece pieceCaptured;
        
        Move(int fromRow, int fromCol, int toRow, int toCol, Piece pieceMoved, Piece pieceCaptured) {
            this.fromRow = fromRow;
            this.fromCol = fromCol;
            this.toRow = toRow;
            this.toCol = toCol;
            this.pieceMoved = pieceMoved;
            this.pieceCaptured = pieceCaptured;
        }
    }
    
  
    static class GameState implements Serializable {
        private static final long serialVersionUID = 1L;
        Piece[][] board;
        boolean isWhiteTurn;
        boolean gameOver;
        String winner;
        List<Move> moveHistory;
        List<Piece> capturedWhitePieces;
        List<Piece> capturedBlackPieces;
        
        GameState(Piece[][] board, boolean isWhiteTurn, boolean gameOver, String winner,
                 List<Move> moveHistory, List<Piece> capturedWhitePieces, List<Piece> capturedBlackPieces) {
            this.board = board;
            this.isWhiteTurn = isWhiteTurn;
            this.gameOver = gameOver;
            this.winner = winner;
            this.moveHistory = moveHistory;
            this.capturedWhitePieces = capturedWhitePieces;
            this.capturedBlackPieces = capturedBlackPieces;
        }
        
        GameState() {
            // Default constructor
        }
    }


//Extra Feature- Adami

static class MenuManager{
    private GuiChess mainFrame;

    public MenuManager(GuiChess frame){
        this.mainFrame = frame;
    }
    /**
     * Main Menu Bar
     */

    public JMenuBar createMenuBar(){
        JMenuBar menuBar = new JMenuBar();

        JMenu gameMenu = new JMenu("Game");

        JMenuItem newGameItem = new JMenuItem("New Game");
        newGameItem.addActionListener(e -> mainFrame.startNewGame());

        JMenuItem saveGameItem = new JMenuItem("Save Game");
        saveGameItem.addActionListener(e -> mainFrame.saveGame());

        JMenuItem loadGameItem = new JMenuItem("Load Game");
        loadGameItem.addActionListener(e -> mainFrame.loadGame());

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        
        gameMenu.add(newGameItem);
        gameMenu.add(saveGameItem);
        gameMenu.add(loadGameItem);

        gameMenu.addSeparator();
        gameMenu.add(gameMenu);

        return menuBar;
    }

    /**
     * Save Game File
     */

     public void saveGame(GameState gameState){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Game");
        
        int userSelection = fileChooser.showOpenDialog(mainFrame);
        if (userSelection == JFileChooser.APPROVE_OPTION){
            File fileToSave = fileChooser.getSelectedFile();
            try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileToSave)))
            {
                oos.writeObject(gameState);
                JOptionPane.showMessageDialog(mainFrame, "Game saved successfully!", "Save Game", JOptionPane.INFORMATION_MESSAGE);
            }catch(IOException ex){
                JOptionPane.showMessageDialog(mainFrame, "Error saving game: " + ex.getMessage(), "Save Error", JOptionPane.ERROR_MESSAGE);
            }
        }
     }
/**
 * Load Game File
 */

 public GameState loadGame(){
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Load Game");

    int userSelection = fileChooser.showOpenDialog(mainFrame);
    if (userSelection == JFileChooser.APPROVE_OPTION){
        File fileToLoad = fileChooser.getSelectedFile();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileToLoad)))
        {
            GameState state = (GameState) ois.readObject();
            JOptionPane.showMessageDialog(mainFrame, "Game loaded successfully!", "Load Game", JOptionPane.INFORMATION_MESSAGE);
            return state;
        } catch(IOException | ClassNotFoundException ex){
            JOptionPane.showMessageDialog(mainFrame, "Error loading game: " + ex.getMessage(), "Load Error", JOptionPane.ERROR_MESSAGE);

        }
    }
    return null;
 }

 


}}
