package com.example.helloworld;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static LinearLayout main;
    private static ImageView image;
    private static Button btn_switch;
    private int current_image_index;
    int[] images = {R.drawable.developers , R.drawable.developers_dark};
    private int current_background_index;
    int[] colors = {Color.parseColor("#ffffff"),Color.parseColor("#000000")};
    private int current_text_index;
    String[] texts = {"Dark mode" , "Light Mode"};
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
        onButtonClickListener();
    }

    public void onButtonClickListener(){
        main = (LinearLayout) findViewById(R.id.main);
        image = (ImageView) findViewById(R.id.imageView);
        btn_switch = (Button) findViewById(R.id.button);

        btn_switch.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        current_image_index++;
                        current_image_index %= images.length;
                        image.setImageResource(images[current_image_index]);
                        current_background_index++;
                        current_background_index %= colors.length;
                        main.setBackgroundColor(colors[current_background_index]);
                        current_text_index++;
                        current_text_index %= texts.length;
                        btn_switch.setText(texts[current_text_index]);
                    }
                }
        );
    }

}
