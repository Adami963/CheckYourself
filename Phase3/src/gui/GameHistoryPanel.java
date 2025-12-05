package Phase3.src.gui;

import javax.swing.*;


//import Phase3.src.gui.*;
//import Phase3.src.gui.GuiChess;
import Phase3.src.gui.GuiChess.Move;
import Phase3.src.gui.GuiChess.Piece;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

import Phase3.src.gui.GuiChess.Move;
import Phase3.src.gui.GuiChess.Piece;

/**
 * GameHistoryPanel.java
 * 
 * Displays the move history and captured pieces for a chess game.
 * Includes an Undo button with an ActionListener callback.
 */
public class GameHistoryPanel {

    private JPanel mainPanel;
    private JTextArea moveHistoryArea;
    private JPanel capturedPiecesPanel;
    private JButton undoButton;
    private ActionListener undoListener;

    public GameHistoryPanel() {
        initializeComponents();
    }

    private void initializeComponents() {
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(250, 600));
        mainPanel.setBorder(BorderFactory.createTitledBorder("Game History"));

        // Move History Section
        JLabel historyLabel = new JLabel("Move History:");
        moveHistoryArea = new JTextArea(20, 20);
        moveHistoryArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(moveHistoryArea);

        // Captured Pieces Section
        JLabel capturedLabel = new JLabel("Captured Pieces:");
        capturedPiecesPanel = new JPanel();
        capturedPiecesPanel.setLayout(new BoxLayout(capturedPiecesPanel, BoxLayout.Y_AXIS));
        JScrollPane capturedScrollPane = new JScrollPane(capturedPiecesPanel);
        capturedScrollPane.setPreferredSize(new Dimension(230, 150));

        // Undo Button
        undoButton = new JButton("Undo Move");
        undoButton.addActionListener(e -> {
            if (undoListener != null) {
                undoListener.actionPerformed(e);
            }
        });

        // Layout organization
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(historyLabel, BorderLayout.NORTH);
        northPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(capturedLabel, BorderLayout.NORTH);
        southPanel.add(capturedScrollPane, BorderLayout.CENTER);
        southPanel.add(undoButton, BorderLayout.SOUTH);

        mainPanel.add(northPanel, BorderLayout.CENTER);
        mainPanel.add(southPanel, BorderLayout.SOUTH);
    }

    /** Returns the main JPanel component **/
    public JPanel getPanel() {
        return mainPanel;
    }

    /** Adds a move line to the move history text area **/
    public void addMove(String moveDescription) {
        moveHistoryArea.append(moveDescription + "\n");
    }

    /** Replaces the entire move history **/
    public void updateMoveHistory(List<Move> moves) {
        moveHistoryArea.setText("");
        for (Move move : moves) {
            moveHistoryArea.append(move + "\n");
        }
    }

    /** Updates captured pieces for both sides **/
    public void updateCapturedPieces(List<Phase3.src.gui.GuiChess.Piece> capturedWhite, List<Phase3.src.gui.GuiChess.Piece> capturedBlack) {
        capturedPiecesPanel.removeAll();

        // White pieces
        JLabel whiteLabel = new JLabel("White captured:");
        capturedPiecesPanel.add(whiteLabel);

        JPanel whitePiecesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        for (Piece piece : capturedWhite) {
            JLabel pieceLabel = new JLabel(piece.getSymbol());
            pieceLabel.setFont(new Font("Serif", Font.PLAIN, 20));
            whitePiecesPanel.add(pieceLabel);
        }
        capturedPiecesPanel.add(whitePiecesPanel);

        // Black pieces
        JLabel blackLabel = new JLabel("Black captured:");
        capturedPiecesPanel.add(blackLabel);

        JPanel blackPiecesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        for (Piece piece : capturedBlack) {
            JLabel pieceLabel = new JLabel(piece.getSymbol());
            pieceLabel.setFont(new Font("Serif", Font.PLAIN, 20));
            blackPiecesPanel.add(pieceLabel);
        }
        capturedPiecesPanel.add(blackPiecesPanel);

        capturedPiecesPanel.revalidate();
        capturedPiecesPanel.repaint();
    }

    /** Clears both move history and captured pieces **/
    public void clearHistory() {
        moveHistoryArea.setText("");
        capturedPiecesPanel.removeAll();
        capturedPiecesPanel.revalidate();
        capturedPiecesPanel.repaint();
    }

    /** Sets listener for Undo button **/
    public void setUndoListener(ActionListener listener) {
        this.undoListener = listener;
    }

}
