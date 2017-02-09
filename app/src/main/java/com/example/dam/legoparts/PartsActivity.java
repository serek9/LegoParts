package com.example.dam.legoparts;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dam.legoparts.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.dam.legoparts.R.id.list_parts;

public class PartsActivity extends AppCompatActivity {


    public class Part{

        private int id;
        private String name;
        private String color;
        private int image;
        private int logo;

        public Part(int id, String name, String color, int image, int logo) {
            this.id = id;
            this.name = name;
            this.color = color;
            this.image = image;
            this.logo = logo;
        }

        public int getId() {return id;}
        public void setId(int id) {this.id = id;}
        public String getName() {return name;}
        public void setName(String name) {this.name = name;}
        public String getColor() {return color;}
        public void setColor(String color) {this.color = color;}
        public int getImage() {return image;}
        public void setImage(int image) {this.image = image;}
        public int getLogo() {return logo;}
        public void setLogo(int logo) {this.logo = logo;}

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parts);
        ListView  listView = (ListView) findViewById(list_parts);

        List<Part> dades = new ArrayList<>();
        dades.add(new Part(1, "Brick Special 1 x 4", "Red", R.drawable.brick, R.drawable.lego_head));
        dades.add(new Part(2, "Brick Special 1 x 6", "Blue", R.drawable.brick2, R.drawable.lego_head));
        //dades.add(new Part(3, "Brick Special 1 x 6", "Blue", R.drawable.brick2, R.drawable.lego_head));
        dades.add(new Part(4, "Brick Special 1 x 6", "Blue", R.drawable.brick2, R.drawable.lego_head));
        dades.add(new Part(5, "Brick Special 1 x 4", "Red", R.drawable.brick, R.drawable.lego_head));
        //dades.add(new Part(6, "Brick Special 1 x 4", "Red", R.drawable.brick, R.drawable.lego_head));
        //dades.add(new Part(7, "Brick Special 1 x 4", "Red", R.drawable.brick, R.drawable.lego_head));
        dades.add(new Part(8, "Brick Special 1 x 4", "Red", R.drawable.brick, R.drawable.lego_head));
        //dades.add(new Part(1, "Brick Special 1 x 4", "Red", R.drawable.brick, R.drawable.lego_head));
        dades.add(new Part(2, "Brick Special 1 x 6", "Blue", R.drawable.brick2, R.drawable.lego_head));
        //dades.add(new Part(3, "Brick Special 1 x 6", "Blue", R.drawable.brick2, R.drawable.lego_head));
        dades.add(new Part(4, "Brick Special 1 x 6", "Blue", R.drawable.brick2, R.drawable.lego_head));
        //dades.add(new Part(5, "Brick Special 1 x 4", "Red", R.drawable.brick, R.drawable.lego_head));
        dades.add(new Part(6, "Brick Special 1 x 4", "Red", R.drawable.brick, R.drawable.lego_head));
        //dades.add(new Part(7, "Brick Special 1 x 4", "Red", R.drawable.brick, R.drawable.lego_head));
        dades.add(new Part(8, "Brick Special 1 x 4", "Red", R.drawable.brick, R.drawable.lego_head));


        CatalogAdapter adapter = new CatalogAdapter(this,dades);
        listView.setAdapter(adapter);
        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(PartsActivity.this, "Click en item", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(PartsActivity.this, "Click fuera", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public class CatalogAdapter extends BaseAdapter {

            private Context context;
            private List<Part> catalog;

            public CatalogAdapter(Context context, List<Part> catalog) {
                this.context = context;
                this.catalog = catalog;
            }

            @Override
            public int getCount() {
                return catalog.size();
            }

            @Override
            public Object getItem(int position) {
                return catalog.get(position);
            }

            @Override
            public long getItemId(int position) {
                Part p = catalog.get(position);
                int id = p.getId();
                return id;
            }

            public class ViewHolder{

                public TextView partName;
                public TextView partColor;
                public ImageView partImage;
                public ImageView partLogo;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(ContextThemeWrapper.LAYOUT_INFLATER_SERVICE);
                //View myView = inflater.inflate(R.layout.llista5_item, parent, false);
                //RECICLAT DE VISTES
                View myView = convertView;
                if (myView == null){
                    myView = inflater.inflate(R.layout.list_parts, parent, false);
                    ViewHolder holder = new ViewHolder();
                    holder.partName = (TextView) myView.findViewById(R.id.name);
                    holder.partColor = (TextView) myView.findViewById(R.id.color);
                    holder.partImage = (ImageView) myView.findViewById(R.id.image);
                    holder.partLogo = (ImageView) myView.findViewById(R.id.logo);
                    myView.setTag(holder);
                }
                ViewHolder holder = (ViewHolder) myView.getTag();
                //|-Per estalviar inflates.
                Part part = catalog.get(position);
                String nom = part.getName();
                holder.partName.setText(nom);
                String color = part.getName();
                holder.partColor.setText(nom);
                int image = part.getImage();
                holder.partImage.setImageResource(image);
                int logo = part.getLogo();
                holder.partLogo.setImageResource(logo);
                return myView;
            }
        }

}