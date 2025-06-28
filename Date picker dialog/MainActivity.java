package com.example.sampleapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btn_sdpd; // date picker button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_sdpd = findViewById(R.id.button_date);

       

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
