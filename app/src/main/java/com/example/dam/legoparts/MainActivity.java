package com.example.dam.legoparts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    protected EditText setText;
    protected Button goButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setText = (EditText) findViewById(R.id.editText1);
        goButton = (Button) findViewById(R.id.button1);
        setText.setText("");
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
