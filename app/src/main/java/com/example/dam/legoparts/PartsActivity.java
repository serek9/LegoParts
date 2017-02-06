package com.example.dam.legoparts;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.dam.legoparts.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PartsActivity extends AppCompatActivity {

    public class Part extends HashMap<String, Object>{
        public Part(int id, String name, String color, int image, int logo){
            this.put("id", id);
            this.put("name", name);
            this.put("color", color);
            this.put("logo", logo);
            this.put("image", image);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parts);
        ListView  listView = (ListView) findViewById(R.id.list_parts);

        List<Part> dades = new ArrayList<>();
        dades.add(new Part(1, "Brick Special 1 x 4", "Red", R.drawable.brick, R.drawable.lego_head));
        dades.add(new Part(2, "Brick Special 1 x 6", "Blue", R.drawable.brick2, R.drawable.lego_head));
        dades.add(new Part(3, "Brick Special 1 x 6", "Blue", R.drawable.brick2, R.drawable.lego_head));
        dades.add(new Part(4, "Brick Special 1 x 6", "Blue", R.drawable.brick2, R.drawable.lego_head));
        dades.add(new Part(5, "Brick Special 1 x 4", "Red", R.drawable.brick, R.drawable.lego_head));
        dades.add(new Part(6, "Brick Special 1 x 4", "Red", R.drawable.brick, R.drawable.lego_head));
        dades.add(new Part(7, "Brick Special 1 x 4", "Red", R.drawable.brick, R.drawable.lego_head));
        dades.add(new Part(8, "Brick Special 1 x 4", "Red", R.drawable.brick, R.drawable.lego_head));
        dades.add(new Part(1, "Brick Special 1 x 4", "Red", R.drawable.brick, R.drawable.lego_head));
        dades.add(new Part(2, "Brick Special 1 x 6", "Blue", R.drawable.brick2, R.drawable.lego_head));
        dades.add(new Part(3, "Brick Special 1 x 6", "Blue", R.drawable.brick2, R.drawable.lego_head));
        dades.add(new Part(4, "Brick Special 1 x 6", "Blue", R.drawable.brick2, R.drawable.lego_head));
        dades.add(new Part(5, "Brick Special 1 x 4", "Red", R.drawable.brick, R.drawable.lego_head));
        dades.add(new Part(6, "Brick Special 1 x 4", "Red", R.drawable.brick, R.drawable.lego_head));
        dades.add(new Part(7, "Brick Special 1 x 4", "Red", R.drawable.brick, R.drawable.lego_head));
        dades.add(new Part(8, "Brick Special 1 x 4", "Red", R.drawable.brick, R.drawable.lego_head));

        SimpleAdapter adapter = new SimpleAdapter(
                PartsActivity.this, dades,
                R.layout.list_parts,
                new String[] {"name","color", "logo", "image"},
                new int[] { R.id.name, R.id.color, R.id.logo, R.id.image }
        );

        listView.setAdapter(adapter);
    }
}