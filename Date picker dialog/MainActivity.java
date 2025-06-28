package com.example.sampleapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btn_stpd; // time picker button
    private Button btn_sdpd; // date picker button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_stpd = findViewById(R.id.button);
        btn_sdpd = findViewById(R.id.button_date);

        // Time picker button listener (classic anonymous class)
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

        // Date picker button listener (classic anonymous class)
        btn_sdpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment datePicker = new DatePickerFragment();
                datePicker.setListener(new DatePickerFragment.DateSelectedListener() {
                    @Override
                    public void onDateSelected(int year, int month, int dayOfMonth) {
                        // month is zero-based!
                        String date = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth);
                        Toast.makeText(MainActivity.this, "Selected date: " + date, Toast.LENGTH_SHORT).show();
                    }
                });
                datePicker.show(getSupportFragmentManager(), "datePicker");
            }
        });
    }
}
