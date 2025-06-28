package com.example.sampleapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btn_stpd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_stpd = findViewById(R.id.button);

        btn_stpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerFragment timePicker = new TimePickerFragment();
                timePicker.setListener(new TimePickerFragment.TimeSelectedListener() {
                    @Override
                    public void onTimeSelected(int hour, int minute) {
                        String time = String.format("%02d:%02d", hour, minute);
                        Toast.makeText(MainActivity.this, "Selected time: " + time, Toast.LENGTH_SHORT).show();
                    }
                });
                timePicker.show(getSupportFragmentManager(), "timePicker");
            }
        });
    }
}
