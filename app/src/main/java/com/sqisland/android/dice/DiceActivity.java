package com.sqisland.android.dice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class DiceActivity extends Activity {
    public static final String KEY_NUM_DICE = "num_dice";
    private Random random = new Random();
    public static final String KEY_TOTAL = "total";
    private int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);

        TextView resultView = (TextView) findViewById(R.id.result);

        // roll one dice
        // String result = String.valueOf(rollOne());
        // resultView.setText(result);

        // roll multiple dice
        int numDice = this.getIntent().getIntExtra(KEY_NUM_DICE, 2); // get extra from main activity
        rollAll(resultView, numDice);

        View doneButton = findViewById(R.id.done_button);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View view) {
                    printTotal();
                }
            }
        );
    }

    private void printTotal() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(KEY_TOTAL, total);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    private void rollAll(TextView resultView, int numDice) {
        StringBuilder sb = new StringBuilder();
        total = 0;
        for (int i=0; i<numDice; i++){
            int result = rollOne();
            total += result;

            if (i > 0){
                sb.append(" + ");
            }
            sb.append(result);
        }
        sb.append(" = ");
        sb.append(total);
        resultView.setText(sb.toString());
    }

    private int rollOne(){
        return random.nextInt(6) + 1;
    }
}
