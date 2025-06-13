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
    private CheckBox check1,check2 ,check3;
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
        addListenerToCheckBox();
    }



    public void addListenerToCheckBox() {
        check1 = (CheckBox)findViewById(R.id.checkBox_dog);
        check1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(((CheckBox)v).isChecked()) {
                            Toast.makeText(MainActivity.this,
                                    "Dog is selected",Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
        check2 = (CheckBox)findViewById(R.id.checkBox_cat);
        check2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(((CheckBox)v).isChecked()) {
                            Toast.makeText(MainActivity.this,
                                    "Cat is selected",Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
        check3 = (CheckBox)findViewById(R.id.checkBox_cow);
        check3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(((CheckBox)v).isChecked()) {
                            Toast.makeText(MainActivity.this,
                                    "Cow is selected",Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    public void addListenerOnButton() {
        check1 = (CheckBox)findViewById(R.id.checkBox_dog);
        check2 = (CheckBox)findViewById(R.id.checkBox_cat);
        check3 = (CheckBox)findViewById(R.id.checkBox_cow);
        button_sel = (Button)findViewById(R.id.button);

        button_sel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        StringBuffer result = new StringBuffer();
                        result.append("Dog : ").append(check1.isChecked()).append(' ');
                        result.append(" Cat : ").append(check2.isChecked()).append(' ');
                        result.append(" Cow : ").append(check3.isChecked());

                        Toast.makeText(MainActivity.this,result.toString(),
                                Toast.LENGTH_LONG).show();
                    }
                }

        );

    }


}
