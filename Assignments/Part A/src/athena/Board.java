package athena;

/**
 * Made by Ammar Ahmed and Nam Nguyen
 * Student IDs: Ammar 728926, Nam 718604
 * User names: Ammar - amamra, Nam - namn1
 * For COMP30024 Part A
 */

import java.util.ArrayList;

public class Board
{
    private int n;
    private ArrayList<String[]> board;
    private ArrayList<Piece> horizontalPieces = new ArrayList<>();
    private ArrayList<Piece> verticalPieces = new ArrayList<>();
    private ArrayList<Obstacle> obstacles = new ArrayList<>();

    public void setN(int n) { this.n = n; }

    public int getN() { return this.n; }

    /**
     * Constructor for this class
     * @param boardArray 2-D board array
     */
    public Board (ArrayList<String[]> boardArray)
    {
        this.board = boardArray;
    }

    /**
     * This method sets up the pieces on the board by looking around them
     * and setting the boolean values of their directions correctly
     */
    public void setUpPieces ()
    {
        for (int i = 0; i < n; i++)
        {
            int j = 0;
            for (String c : board.get(i))
            {
                if (c.equals("H"))
                {
                    Horizontal horizontal = new Horizontal(i, j);
                    /** Looking down, right, checking the case when the piece is
                     * on the right edge of the board and up respectively */
                    if (i > 0 && board.get(i-1)[j].equals("+")) { horizontal.setUpTrue(); }

                    if (j < n - 1 && board.get(i)[j+1].equals("+")) { horizontal.setRightTrue(); }

                    else if (j == n - 1) { horizontal.setRightTrue(); }

                    if (i < n - 1 && board.get(i+1)[j].equals("+")) { horizontal.setDownTrue(); }

                    horizontalPieces.add(horizontal);
                }

                else if (c.equals("V"))
                {
                    Vertical vertical = new Vertical(i, j);
                    /** Looking  up, right, checking the case when the piece is
                     * on the top edge of the board and left respectively */
                    if (i < n - 1 && board.get(i+1)[j].equals("+")) { vertical.setUpTrue(); }

                    else if (i == n - 1) { vertical.setUpTrue(); }

                    if (j < n - 1 && board.get(i)[j+1].equals("+")) { vertical.setRightTrue(); }

                    if (j > 0 && board.get(i)[j-1].equals("+")) { vertical.setLeftTrue(); }

                    verticalPieces.add(vertical);
                }

                else if (c.equals("B"))
                {
                    Obstacle obs = new Obstacle(i, j);
                    obstacles.add(obs);
                }
                j++;
            }
        }
    }

    /**
     * Calculates the number of total horizontal legal moves
     * by first setting them for the piece and adding it to total
     * @return number of legal horizontal moves
     */
    public int numLegalHMoves()
    {
        int count = 0;

        for( Piece p : horizontalPieces)
        {
            p.setLegalMoves();
            count += p.getNumLegalMoves();
        }
        return count;
    }

    /**
     * Calculates the number of total vertical legal moves
     * by first setting them for the piece and adding it to total
     * @return number of legal vertical moves
     */
    public int numLegalVMoves()
    {
        int count = 0;

        for (Piece p : verticalPieces)
        {
            p.setLegalMoves();
            count += p.getNumLegalMoves();
        }
        return count;
    }
}