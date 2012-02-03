package annabel.tickytacky;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuItem;

public class TickyTackyActivity extends Activity implements OnClickListener {
    
    private Button grid[][] = new Button[3][3];;
    private Button reset;
    boolean myTurn, myStart;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        loadViews();
        myTurn = true;
        myStart = true;
    }
    
    private void loadViews() {
        /*
            00 | 01 | 02
            10 | 11 | 12
            20 | 21 | 22
        */
        reset = (Button) findViewById(R.id.reset);
       
        
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
        
        reset.setOnClickListener(this);
        
    }

    @Override
    public void onClick(View view) {
        // reset button clicked
        if (((Button)view).equals(reset)) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    grid[i][j].setText("");
                }
            }
            myStart = !myStart;
        }
        // empty button clicked
        else if (((Button)view).getText().length() == 0) {
            if (myTurn) {
                ((Button) view).setText("BOO");
                myTurn = false;
            
                // AI
                myTurn = true;
            }
        }
        // non-empty button clicked
            // do nothing
    }
    
    
    
}