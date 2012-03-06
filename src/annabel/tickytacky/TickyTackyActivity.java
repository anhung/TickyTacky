package annabel.tickytacky;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/*
 * Ticky Tacky
 * a Tic Tac Toe app for Android
 * 
 * By: Annabel Hung
 * Email: annabel.hung@gmail.com
 */
public class TickyTackyActivity extends Activity implements OnClickListener {
    
    private Button bGrid[][] = new Button[3][3];
    boolean myTurn, myStart, gameOver;
    static final String PLAYER = "X";
    static final String COMPUTER = "O";
    static final String UNMARKED = "";
    
    /*
     * Required Android stuff. ^__^
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initializeViews();
        myTurn = true;
        myStart = true;
        gameOver = false;
    }
    
    /*
     * Helper function to initialize the graphical grid
     * and other views.
     */
    private void initializeViews() {
        /*
         *  Grid set up
         *  00 | 01 | 02
         *  10 | 11 | 12
         *  20 | 21 | 22
         */
        
        bGrid[0][0] = (Button) findViewById(R.id.button1);
        bGrid[0][1] = (Button) findViewById(R.id.button2);
        bGrid[0][2] = (Button) findViewById(R.id.button3);
        bGrid[1][0] = (Button) findViewById(R.id.button4);
        bGrid[1][1] = (Button) findViewById(R.id.button5);
        bGrid[1][2] = (Button) findViewById(R.id.button6);
        bGrid[2][0] = (Button) findViewById(R.id.button7);
        bGrid[2][1] = (Button) findViewById(R.id.button8);
        bGrid[2][2] = (Button) findViewById(R.id.button9);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                bGrid[i][j].setOnClickListener(this);
            }
        }
    }

    /*
     * What to when a button is pressed.
     * Reset button resets the grid.
     * Grid buttons will be marked with
     * an 'X' to represent the player's move.
     */
    @Override
    public void onClick(View view) {
        if (!isGameOver() && isValidMove((Button)view)) {
            if (myTurn) {
                ((Button) view).setText(PLAYER);
                myTurn = false;
                opponentTurn();
                myTurn = true;
            }
        }
    }
    
    /*
     * Crappy AI. Just try a random button until
     * it's a valid move, then take it.
     */
    private void opponentTurn() {
        // TODO: incomplete
        if (!isNoMoreMoves()) {
            Random r = new Random();
            int i = r.nextInt(3);
            int j = r.nextInt(3);
            Button b = bGrid[i][j];
            while (!isValidMove(b)) {
                i = r.nextInt(3);
                j = r.nextInt(3);
                b = bGrid[i][j];
            }
            b.setText(COMPUTER);
        }
    }
    
    /*
     * Check if the specified button is a valid move.
     * Returns true if so, otherwise false.
     */
    private boolean isValidMove(Button b) {
        return (b.getText().length() == 0);
    }
    
    /*
     * Check if there are no more moves.
     * Returns true if so, otherwise false.
     */
    private boolean isNoMoreMoves() {
        Button b;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                b = bGrid[i][j];
                if (isValidMove(b)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /*
     * Check if the specified button is
     * part of a winning 3-in-a-row.
     * Kind of an inefficient algorithm...
     */
    private boolean isWinner(String mark) {
        // CHECKING THE ROWS
        for (int i = 0; i < 3; i++) {
            if (getMark(bGrid[i][0]).equals(mark) &&
                getMark(bGrid[i][1]).equals(mark) &&
                getMark(bGrid[i][2]).equals(mark)) {
                return true;
            }
        }
        
        // CHECKING THE COLUMNS
        for (int j = 0; j < 3; j++) {
            if (getMark(bGrid[0][j]).equals(mark) &&
                getMark(bGrid[1][j]).equals(mark) &&
                getMark(bGrid[2][j]).equals(mark)) {
                return true;
            }
        }
        
        // CHECK TWO DIAGONALS
        if (getMark(bGrid[0][0]).equals(mark) &&
            getMark(bGrid[1][1]).equals(mark) &&
            getMark(bGrid[2][2]).equals(mark)) {
            return true;
        }
        if (getMark(bGrid[0][2]).equals(mark) &&
            getMark(bGrid[1][1]).equals(mark) &&
            getMark(bGrid[2][0]).equals(mark)) {
            return true;
        }
            
        return false;
    }

    private String getMark(Button b) {
        return b.getText().toString();
    }
    
    private boolean isGameOver() {
        return isWinner(PLAYER) || isWinner(COMPUTER);
    }
}