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
    
    private Button grid[][] = new Button[3][3];;
    private Button reset;
    private int buttonsPressed;
    boolean myTurn, myStart, gameOver;
    
    /*
     * Required Android stuff. ^__^
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        loadViews();
        myTurn = true;
        myStart = true;
        gameOver = false;
    }
    
    /*
     * Helper function to load all the views 
     * and set their listeners.
     */
    private void loadViews() {
        /*
         *  Grid set up
         *  00 | 01 | 02
         *  10 | 11 | 12
         *  20 | 21 | 22
         */
        
        reset = (Button) findViewById(R.id.reset);
        reset.setOnClickListener(this); 
        
        grid[0][0] = (Button) findViewById(R.id.button1);
        grid[0][1] = (Button) findViewById(R.id.button2);
        grid[0][2] = (Button) findViewById(R.id.button3);
        grid[1][0] = (Button) findViewById(R.id.button4);
        grid[1][1] = (Button) findViewById(R.id.button5);
        grid[1][2] = (Button) findViewById(R.id.button6);
        grid[2][0] = (Button) findViewById(R.id.button7);
        grid[2][1] = (Button) findViewById(R.id.button8);
        grid[2][2] = (Button) findViewById(R.id.button9);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j].setOnClickListener(this);
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
        // TODO: incomplete
        if (((Button)view).equals(reset)) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    grid[i][j].setText("");
                }
            }
            myStart = !myStart;
        }
        else if (isValidMove((Button)view)) {
            if (myTurn && !gameOver) {
                ((Button) view).setText("X");
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
        Random r = new Random();
        Button b = grid[r.nextInt(3)][r.nextInt(3)];
        while (!isValidMove(b)) {
            b = grid[r.nextInt(3)][r.nextInt(3)];
        }
        b.setText("O");
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
                b = grid[i][j];
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
     */
    private boolean isWinner(Button b) {
        // TODO: incomplete
        return false;
    }
}