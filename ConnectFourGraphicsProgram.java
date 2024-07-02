import acm.program.*;
import acm.graphics.*;
import java.awt.Color;
import java.awt.event.*;

public class ConnectFourGraphicsProgram extends GraphicsProgram
{
    private static final int PIECE_DIAM = 100;
    private static final int NUM_COLS = 7;
    private static final int NUM_ROWS = 6;
    public static final int APPLICATION_WIDTH = NUM_COLS * PIECE_DIAM;
    public static final int APPLICATION_HEIGHT = NUM_ROWS * PIECE_DIAM;

    /** Dimensions of game board in pixels (usually the same) */
    private static final int WIDTH = APPLICATION_WIDTH;
    private static final int HEIGHT = APPLICATION_HEIGHT;

    private ConnectFourGame game; //game itself
    private boolean gameIsBeingPlayed;
    private Color currentDrawingColor;

    public void run()
    {
        currentDrawingColor = Color.RED;

        game = new ConnectFourGame(NUM_ROWS, NUM_COLS);
        gameIsBeingPlayed = true;
        while (game.getWinner()==0)
        {
            pause(10);
        }
        gameIsBeingPlayed = false;
        Color winnerColor;
        String colorStr;
        if (game.getWinner()==1)
        {
            winnerColor = Color.red;
            colorStr = "red";
        }
        else
        {
            winnerColor = Color.black;
            colorStr = "black";
        }
        GLabel label = new GLabel("Game over! Congrats to Team " + colorStr, 150, 150);
        label.setColor(winnerColor);
        if (currentDrawingColor == Color.red)
            label.setColor(Color.black);
        else
            label.setColor(Color.red);
        add(label);
        // add code here in version 0.4
    }

    public void mouseClicked(MouseEvent event)
    {
        if (!gameIsBeingPlayed)
            return;
        int x = event.getX();
        int col = x / PIECE_DIAM;
        int row = game.dropChecker(col);
        if (row == -1)
            return;
        else
        {
            GOval checker = new GOval(col*PIECE_DIAM,row*PIECE_DIAM , PIECE_DIAM, PIECE_DIAM);
            checker.setFilled(true);
            checker.setColor(currentDrawingColor);
            add(checker);
            if (currentDrawingColor == Color.red)
                currentDrawingColor = Color.black;
            else
                currentDrawingColor = Color.red;
        }
    }

}
