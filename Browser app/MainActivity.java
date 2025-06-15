package com.example.helloworld;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static Button btn_sbm;
    private static EditText url_text;
    private static WebView browser;

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
        browse();
    }

    public void browse(){
       btn_sbm = (Button) findViewById(R.id.button);
       url_text =(EditText) findViewById(R.id.url);
       browser = (WebView) findViewById(R.id.browser);

       btn_sbm.setOnClickListener(
               new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       String url = url_text.getText().toString();
                       browser.getSettings().setLoadsImagesAutomatically(true);
                       browser.getSettings().setJavaScriptEnabled(true);
                       browser.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                       browser.loadUrl(url);
                   }
               }
       );
    }

}
