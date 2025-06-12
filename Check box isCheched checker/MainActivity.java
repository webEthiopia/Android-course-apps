package com.example.helloworld;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private CheckBox checkBox1 , checkBox2 , checkBox3;
    private Button button_sel;

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
        addListenerOnButton();
    }

    public void addListenerOnButton(){
        checkBox1 = (CheckBox) findViewById(R.id.checkBox_dog);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox_cat);
        checkBox3 = (CheckBox) findViewById(R.id.checkBox_cow);
        button_sel = (Button) findViewById(R.id.button);

        button_sel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        StringBuffer result = new StringBuffer();
                        result.append("Let's see what you like. ");
                        result.append("Dog : ").append(checkBox1.isChecked()).append(' ');
                        result.append(" Cat : ").append(checkBox2.isChecked()).append(' ');
                        result.append(" Cow : ").append(checkBox3.isChecked());

                        Toast.makeText(MainActivity.this,result.toString(),Toast.LENGTH_LONG).show();
                 }
                }


        );
    }



}
