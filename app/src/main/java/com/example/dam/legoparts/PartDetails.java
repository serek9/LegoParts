package com.example.dam.legoparts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PartDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part_details);

        Bundle bundle = getIntent().getExtras();
        Part part = (Part)getIntent().getExtras().getSerializable("part");

        ImageView partImage = (ImageView)findViewById(R.id.partImage);
        TextView partName_val = (TextView)findViewById(R.id.partName_val);
        TextView partColor_val = (TextView)findViewById(R.id.partColor_val);

        Picasso.with(getApplicationContext()).load(part.getPart_img_url()).placeholder(R.drawable.lego_head).into(partImage);
        partName_val.setText(part.getPart_name());
        partColor_val.setText(part.getColor_name());

    }
}
