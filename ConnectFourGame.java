import java.awt.Color;
import info.gridworld.grid.*;

public class ConnectFourGame
{
    private int[][] board;
    private int currentTurnNumber;

    public ConnectFourGame(int rows, int cols)
    {
        board = new int[rows][cols];
        // you need to fill board with 0's
        for (int r=0; r<board.length; r++)
        {
            for (int c=0; c<board[0].length; c++)
                board[r][c] = 0;
        }
        currentTurnNumber = 1;
    }

    // returns 0, 1, or 2
    public int getWinner() //first, detect if 4 (1s or 2s) in a row horizontally. then, run to detect horiz run. then, vertically. last, diagonally.
    {
        int num = checkHorizontally();
        if (num!=0)
            return num;

        num = checkVertically();
        if (num!=0)
            return num;

        num = checkDiagonallyBottomLeftToTopRight();
        if (num!=0)
            return num;

        num = checkDiagonallyTopLeftToBottomRight();
        if (num!=0)
            return num;
        return 0; // feel free to eventually change this line.  it is just here so the rest will compile
    }

    public int checkHorizontally()
    {
        for (int r=0; r<board.length; r++)
        {
            for (int c=0; c<board[0].length-3; c++)
            {
                if (board[r][c] == 1 && board[r][c+1] == 1 && board[r][c+2] == 1 && board[r][c+3] == 1)
                {
                    return 1;
                }
                if (board[r][c] == 2 && board[r][c+1] == 2 && board[r][c+2] == 2 && board[r][c+3] == 2)
                {
                    return 2;
                }
            }
        }
        return 0;
    }

    public int checkVertically()
    {
        for (int r=0; r<board.length-3; r++)
        {
            for (int c=0; c<board[0].length; c++)
            {
                if (board[r][c] == 1 && board[r+1][c] == 1 && board[r+2][c] == 1 && board[r+3][c] == 1)
                {
                    return 1;
                }
                if (board[r][c] == 2 && board[r+1][c] == 2 && board[r+2][c] == 2 && board[r+3][c] == 2)
                {
                    return 2;
                }
            }
        }
        return 0;
    }

    public int checkDiagonallyTopLeftToBottomRight()
    {
        for (int r=0; r<board.length-3; r++)
        {
            for (int c=0; c<board[0].length-3; c++)
            {
                if (board[r][c] == 1 && board[r+1][c+1] == 1 && board[r+2][c+2] == 1 && board[r+3][c+3] == 1)
                {
                    return 1;
                }
                if (board[r][c] == 2 && board[r+1][c+1] == 2 && board[r+2][c+2] == 2 && board[r+3][c+3] == 2)
                {
                    return 2;
                }
            }
        }
        return 0;
    }

    public int checkDiagonallyBottomLeftToTopRight()
    {
        for (int r=3; r<board.length; r++)
        {
            for (int c=0; c<board[0].length-3; c++)
            {
                if (board[r][c] == 1 && board[r-1][c+1] == 1 && board[r-2][c+2] == 1 && board[r-3][c+3] == 1)
                {
                    return 1;
                }
                if (board[r][c] == 2 && board[r-1][c+1] == 2 && board[r-2][c+2] == 2 && board[r-3][c+3] == 2)
                {
                    return 2;
                }
            }
        }
        return 0;
    }
    // updates the state of the game (board and currentTurnColor)
    // returns the row in which the checker would end up
    // returns -1 if the column col is completely full and no checker can be dropped
    public int dropChecker(int col)
    {
        for (int r=board.length-1; r>=0; r--)
        {
            if (board[r][col] == 0)
            {
                board[r][col] = currentTurnNumber;
                if (currentTurnNumber == 1)
                    currentTurnNumber = 2;
                else
                    currentTurnNumber = 1;
                return r;
            }
        }
        return -1;
    }
}