package com.sqisland.android.dice;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
  public static final int REQUEST_CODE_DICE = 1000;
  private TextView totalText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    totalText = (TextView) findViewById(R.id.total);

    View button = findViewById(R.id.roll_button);
    button.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view) {
        roll();
      }
    });
  }

  private void roll() {
    Intent intent = new Intent(this, DiceActivity.class);
    intent.putExtra(DiceActivity.KEY_NUM_DICE, 3); // set extra in main activity
    startActivityForResult(intent, REQUEST_CODE_DICE);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == REQUEST_CODE_DICE && resultCode == Activity.RESULT_OK){
      // print result
      totalText.setText(String.valueOf(data.getIntExtra(DiceActivity.KEY_TOTAL, 0)));
      totalText.setVisibility(View.VISIBLE);
      return;
    }
    super.onActivityResult(requestCode, resultCode, data);
  }
}