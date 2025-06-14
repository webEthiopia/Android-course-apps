package com.example.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static EditText username;
    private static EditText password;
    private static Button login_btn;
    private static TextView attempts;
    int attempt_counter = 5;
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
        loginButton();
    }

    public void loginButton(){
        username = (EditText) findViewById(R.id.editText_userName);
        password = (EditText) findViewById(R.id.editText_password);
        attempts = (TextView) findViewById(R.id.textview_attempts);
        login_btn = (Button) findViewById(R.id.button);

        attempts.setText(Integer.toString(attempt_counter));
        login_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(username.getText().toString().equals("believer") && password.getText().toString().equals("uncrackable")) {
                            Toast.makeText(MainActivity.this,"Username and Password are correct!",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent("com.example.login.User");
                            startActivity(intent);

                        } else{
                            Toast.makeText(MainActivity.this,"Username and Password are incorrect.",Toast.LENGTH_SHORT).show();
                            attempt_counter--;
                            attempts.setText(Integer.toString(attempt_counter));
                            if (attempt_counter == 0) {
                                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                                alert.setMessage("No cheaters allowed!!")
                                        .setCancelable(false)
                                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which){
                                                finish();
                                            }
                                        }).show();
                            }
                        }
                    }
                }
        );
    }
}
