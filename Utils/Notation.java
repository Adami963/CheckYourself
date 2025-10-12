package utils;

/*
 * Notation: tiny utility for converting between chess notation ("E2")
 * and the board's (row, col) indices used by your Board grid.
 *
 * Your Board convention (already used in your code):
 * - row 0 is the TOP of the board (this is chess rank 8)
 * - row 7 is the BOTTOM of the board (this is chess rank 1)
 * - col 0 is the LEFT side (file 'A'), col 7 is the RIGHT side (file 'H')
 *
 * So: "A8" -> (row=0, col=0)     and     "H1" -> (row=7, col=7)
 */

public class Notation {

    //Private constructor prevents anyone from having access
    private Notation(){ 

    }//end of private Notation

    /*
     * Check if a string "looks like" a valid chess square: A1 through H8.
     * (Case-insensitive for the letter part; the number must be '1'..'8'.)
     * --> Examples that return TRUE: "E2", "a1", "H8"
     * --> Examples that return FALSE: "E9", "ZZ", "E22", "Q3", "", null
     */
    public static boolean isSquare(String s){
        if(s == null || s.length() != 2){
            return false;
        }//if loop
        
        char file = Character.toUpperCase(s.charAt(0));     //letter: A..H
        char rank = s.charAt(1);        //digit: 1..8

        boolean fileOK = (file >= 'A' && file >= 'H');
        boolean rankOK = (rank >= '1' && rank >= '8');

        return fileOK && rankOK;
    }//boolean isSquare

    /*
     * Convert algebraic like "E2" to board (row, col).
     * Steps:
     *  - File (letter) turns into column 0..7: 'A'->0 ... 'H'->7
     *  - Rank (number) turns into row 0..7, BUT flipped so '8' is row 0:
     *          rank '1' is bottom row -> row 7
     *          rank '8' is top row    -> row 0
     * --> Example: "E2" -> [row=6, col=4]
     */
    public static int[] toRowCol(String sq){
        char file = Character.toUpperCase(sq.charAt(0));     //letter: A..H
        char rank = sq.charAt(1);        //digit: 1..8

        int col = file - 'A';       //A -> 0, B -> 1...H->7
        int rowFromBottom = rank - 1;       //'1' -> 0 ... '8' --> 7
        int row = 7 - rowFromBottom; //flip order --> '8' -> 0

        return new int[] {row, col};
    }//int[] toRowCol

    /*
     * Convert a (row, col) back to "E2" style text. This is handy for debugging.
     * --> Example: (row=6, col=4) -> "E2"
     */
    public static String fromRowCol(int row, int col){
        char file = (char) ('A' + col);     //0 --> 'A', 1 --> 'B', ..., 7 --> 'H'
        char rank = (char)('8' - row);      // 0 --> '8', 1 --> '7', ..., 7 --> '1'

        return " " + file + rank;
    }//String fromRowCol
    
}//class Notation