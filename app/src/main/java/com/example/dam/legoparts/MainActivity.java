package com.example.dam.legoparts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Part parts;
    protected EditText setText;
    protected TextView setView;
    protected Button goButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setText = (EditText) findViewById(R.id.editText1);
        setView = (TextView) findViewById(R.id.textView1);
        goButton = (Button) findViewById(R.id.button1);
        setText.setText("");
        System.out.println("entra1");
        downloadParts();

    }

    public void init(){
        System.out.println("INIT");
    }

    public void downloadParts() {
        System.out.println("entra2");
        PartsDownloader dd = new PartsDownloader(this);
        dd.setOnPartsLoadedListener(new OnPartsLoadedListener() {
            @Override
            public void onPartsLoaded(boolean ok) {
                Log.d("Sergio", "Download result: " + ok);
                if (ok) init();
            }

        });
        dd.execute();
    }
}
