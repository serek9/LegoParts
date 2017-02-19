package com.example.dam.legoparts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    protected EditText setText;
    protected Button goButton;
    protected LinearLayout exemple1;
    protected LinearLayout exemple2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setText = (EditText) findViewById(R.id.editText1);
        goButton = (Button) findViewById(R.id.button1);
        exemple1 = (LinearLayout) findViewById(R.id.example1);
        exemple2 = (LinearLayout) findViewById(R.id.example2);

        exemple1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setText.setText("7065-1");
            }
        });

        exemple2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setText.setText("21125-1");
            }
        });

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PartsActivity.class);
                String setId = setText.getText().toString();
                intent.putExtra("setId", setId);
                startActivity(intent);
            }
        });
    }


}
