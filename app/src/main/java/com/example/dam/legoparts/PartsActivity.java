package com.example.dam.legoparts;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.util.Log;
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



import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static com.example.dam.legoparts.R.id.list_parts;

public class PartsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parts);

        final PartRepository parts = new PartRepository();

        Bundle extras = getIntent().getExtras();
        String setId = extras.getString("setId");

        String tsv = downloadParts(setId);

        parts.loadFromTsv(tsv);
        Part p = parts.getPartFromIndex(1);
        ListView  listView = (ListView) findViewById(list_parts);

        CatalogAdapter adapter = new CatalogAdapter(this, parts);
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

    public String downloadParts(String codigo) {
        String xml = "";
        RebrickableService dd = new RebrickableService(this);
        try {
            xml = dd.execute(codigo).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return xml;
    }

    public class CatalogAdapter extends BaseAdapter {

            private Context context;
            private PartRepository catalog;

            public CatalogAdapter(Context context, PartRepository catalog) {
                this.context = context;
                this.catalog = catalog;
            }

            @Override
            public int getCount() {
                return catalog.getNumOfParts();
            }

            @Override
            public Object getItem(int position) {
                return catalog.getPartFromIndex(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
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
                Part part = catalog.getPartFromIndex(position);
                String name = part.getPart_name();
                holder.partName.setText(name);
                String color = part.getColor_name();
                holder.partColor.setText(color);
                Log.d("IMAGEN-----------",part.getElement_img_url());
                Drawable image = LoadImageFromWebOperations(part.getElement_img_url());
                holder.partImage.setImageDrawable(image);
                holder.partLogo.setImageResource(R.drawable.lego_head);
                return myView;
            }
        }
    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is,"brick");
            return d;
        } catch (Exception e) {
            return null;
        }
    }
}