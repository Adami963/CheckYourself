package Phase3.src.gui;

import javax.swing.*;
import java.awt.*;
/*
 *  BoardGUI 
 *      -- will render an 8 x 8 grid of buttons w/ alternating colors\
 *      -- will implement 'click to move' (first click selects FROM, second click is TO)
 *      -- will report moves to GUIChess via MoveListener
 *          --> Does not change game state
 */

public class BoardGUI {
    private final JPanel boardPanel = new JPanel(new GridLayout(8,8));
    private final JButton[][] squares = new JButton[8][8];

    private GuiChess.MoveListener moveListener; 
    private int selectedRow = -1, selectedCol = -1;     // -1 = nothing was selected

    //CONSTRUCTOR
    public BoardGUI(){
        buildBoardButtons();
    } //end of BoardGUI

    public JPanel getBoardPanel(){
        return boardPanel;
    }//end of getBoardPanel

    public void setMoveListener(GuiChess.MoveListener listener){
        this.moveListener = listener;
    }//end of setMoveListener

    public void initializeBoard(){}//end of initializeBoard

    //Paint piece symbols onto squares from the current board state
    public void updateBoard(GuiChess.Piece[][] state){
        for(int r = 0; r < 8; r++){
            for(int c = 0; c < 8; c++){
                GuiChess.Piece p = state[r][c];
                JButton b = squares[r][c];
                b.setText(p == null ? "" : p.getSymbol());
                b.setForeground(p == null ? Color.BLACK : (p.getColor() == GuiChess.Piece.Color.WHITE ? Color.WHITE : Color.BLACK));
                b.setFont(b.getFont().deriveFont(Font.PLAIN, 36f));
            }//end of for col
        }//end of for row
    }//end of updateBoard

    // Build the 8×8 grid: create each JButton, set colors, and attach a click handler.
    private void buildBoardButtons(){
        boolean light = true;
        for (int r = 0; r < 8; r++){
            for(int c =0; c < 8; c++){
                final int rr = r, 
                             cc = c;
                JButton btn = new JButton();

                //don't drae the dotted focus outline when clicked
                btn.setFocusPainted(false);
                // Remove default padding so symbols sit centered and large
                btn.setMargin(new Insets(0, 0, 0, 0));
                // Set alternating light/dark colors to look like a chessboard
                btn.setBackground(light ? new Color(234,235,200): new Color(119,149,86) );
                // When clicked, call our handler with the row/col that was clicked
                btn.addActionListener(e -> onSquareClick(rr, cc));

                squares[r][c] = btn;
                boardPanel.add(btn);

                // Flip color for the next square
                light = !light;
            }//end of for col

            // Flip starting color at the start of each row so the checker pattern continues
            light = !light;
        }//end of for row

        // Give the whole board a little padding from the window edges
        boardPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
    }//end of buildBoardButtons

    // Handle clicks: first click selects the FROM square; second click sends FROM->TO to GUIChess.
    private void onSquareClick(int row, int col){
        //first click = select FROM
        if(selectedRow == -1){
            selectedRow = row;
            selectedCol = col;
            squares[row][col].setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
        }//end of if selectedRow == -1 

        //second click = TO
        //clear highlight and notify listener
        else{
            squares[selectedRow][selectedCol].setBorder(UIManager.getBorder("Button.border"));
            int fromR = selectedRow, fromC = selectedCol;
            selectedRow = selectedCol = -1;

            if (moveListener != null) {
                moveListener.onMoveMade(fromR, fromC, row, col);
            }//end of if (moveListener != null)
        }//end of else
        
    }//end of onSquareClick
 } //end of public class BoardGUI 
