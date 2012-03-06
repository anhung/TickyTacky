package annabel.tickytacky;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * Ticky Tacky
 * a Tic Tac Toe app for Android
 * 
 * By: Annabel Hung
 * Email: annabel.hung@gmail.com
 */
public class TickyTackyActivity extends Activity implements OnClickListener {
    
    private Button bGrid[][] = new Button[3][3];
    boolean myTurn, myWin, gameOver;
    static final String PLAYER = "X";
    static final String COMPUTER = "O";
    static final String UNMARKED = "";
    
    /**
     * Required Android stuff. ^__^
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initializeViews();
        myTurn = true;
        myWin = false;
        gameOver = false;
    }
    
    /**
     * Helper function to initialize the graphical grid
     * and other views.
     */
    private void initializeViews() {
        /**
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

    /**
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
                ((Button) view).setBackgroundResource(R.drawable.x);
                isWinner(PLAYER);
                myTurn = false;
                opponentTurn();
                myTurn = true;
            }
        }
    }
    
    /**
     * Create the options menu! Reset and quit
     * are the only options.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, Menu.FIRST, Menu.NONE, "Reset");
        menu.add(0, Menu.FIRST+1, Menu.NONE, "About");
        menu.add(0, Menu.FIRST+2, Menu.NONE, "Quit");
        return true;
    }
    
    /**
     * What to do when something on the options menu is
     * selected. Can only reset the board or quit the app.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        int id = item.getItemId();
        if (id == Menu.FIRST) {
            resetBoard();
        }
        else if (id == Menu.FIRST+1) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        }
        else {
            finish();
        }
        return false;
    }
    
    /**
     * Reset the board to start a new game.
     */
    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                bGrid[i][j].setText(UNMARKED);
                bGrid[i][j].setBackgroundResource(R.drawable.button);
                gameOver = false;
                myTurn = true;
                myWin = false;
            }
        }        
    }
    
    /**
     * Crappy AI. Just try a random button until
     * it's a valid move, then take it.
     */
    private void opponentTurn() {
        // TODO: incomplete
        if (!isGameOver()) {
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
            b.setBackgroundResource(R.drawable.o);
            isGameOver();
        }
    }
    
    /**
     * Check if the specified button is a valid move.
     * Returns true if so, otherwise false.
     */
    private boolean isValidMove(Button b) {
        return (b.getText().length() == 0);
    }
    
    /**
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
    
    /**
     * Check if the specified button is
     * part of a winning 3-in-a-row.
     * Kind of an inefficient algorithm...
     */
    private boolean isWinner(String mark) {
        /**
         * X| | |    | |X| |    | | |X
         * X| | | or | |X| | or | | |X
         * X| | |    | |X| |    | | |X
         */
        for (int i = 0; i < 3; i++) {
            if (belongsTo(bGrid[0][i], mark) &&
                    belongsTo(bGrid[1][i], mark) &&
                    belongsTo(bGrid[2][i], mark)) {
                return true;
            }
            if (belongsTo(bGrid[i][0], mark) &&
                    belongsTo(bGrid[i][1], mark) &&
                    belongsTo(bGrid[i][2], mark)) {
                return true;
            }
        }
        // diagonals
        int j = 0;
        if (belongsTo(bGrid[j][j], mark) &&
                belongsTo(bGrid[j+1][j+1], mark) &&
                belongsTo(bGrid[j+2][j+2], mark)) {
            return true;
        }
        if (belongsTo(bGrid[j][j+2], mark) &&
                belongsTo(bGrid[j+1][j+1], mark) &&
                belongsTo(bGrid[j+2][j], mark)) {
            return true;
        }
        return false;
    }
    
    /**
     * Helper to see if the specified button
     * belongs to the specified player.
     */
    private boolean belongsTo(Button b, String p) {
        return (b.getText().toString().equals(p));
    }
    
    /**
     * Check if the game has ended..
     */
    private boolean isGameOver() {
        if (!gameOver) {
            if (isWinner(PLAYER)) {
                myWin = true;
                gameOver = true;
                Toast.makeText(this, "Yay you win!", Toast.LENGTH_SHORT).show();
            }
            else if (isWinner(COMPUTER)) {
                myWin = false;
                gameOver = true;
                Toast.makeText(this, "Try again!", Toast.LENGTH_SHORT).show();
            }
            else if (isNoMoreMoves()){
                Toast.makeText(this, "Meow game?", Toast.LENGTH_SHORT).show();
                gameOver = true;
            }
        }
        return gameOver;
    }
}