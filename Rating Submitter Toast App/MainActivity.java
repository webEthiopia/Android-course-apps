package com.example.helloworld;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static Button btn_sbm;
    private static TextView text_v;
    private static RatingBar rating_b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        listenerForRatingBar();
        onButtonClickListener();
    }

    public void onButtonClickListener(){
        rating_b = (RatingBar) findViewById(R.id.ratingBar);
        btn_sbm = (Button) findViewById(R.id.button);

        btn_sbm.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this,"Rating submitted! Rating is " + String.valueOf(rating_b.getRating()),Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    public void listenerForRatingBar(){
        rating_b = (RatingBar) findViewById(R.id.ratingBar);
       text_v = (TextView) findViewById(R.id.textView);

       rating_b.setOnRatingBarChangeListener(
               new RatingBar.OnRatingBarChangeListener() {
                   @Override
                   public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                       text_v.setText("Rating = "+String.valueOf(rating));
                   }
               }
       );
    }

}
