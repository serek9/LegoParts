package com.example.dam.legoparts;

import android.content.Context;
import android.content.Intent;
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
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;

import static com.example.dam.legoparts.R.id.list_parts;

public class PartsActivity extends AppCompatActivity {

    public PartsActivity() {
    }

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
        ListView listView = (ListView) findViewById(list_parts);

        final CatalogAdapter adapter = new CatalogAdapter(this, parts);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getBaseContext(), PartDetails.class);
                intent.putExtra("part", (Serializable) adapter.getItem(i));
                startActivity(intent);
            }
        });
    }

    public String downloadParts(String setId) {
        String xml = "";
        RebrickableService rebrickableService = new RebrickableService(this);
        try {
            xml = rebrickableService.execute(setId).get();
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

        public class ViewHolder {

            public TextView partName;
            public TextView partColor;
            public ImageView partImage;
            public ImageView partLogo;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View myView = convertView;
            if (myView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(ContextThemeWrapper.LAYOUT_INFLATER_SERVICE);
                myView = inflater.inflate(R.layout.list_parts, parent, false);
                ViewHolder holder = new ViewHolder();
                holder.partName = (TextView) myView.findViewById(R.id.name);
                holder.partColor = (TextView) myView.findViewById(R.id.color);
                holder.partImage = (ImageView) myView.findViewById(R.id.image);
                holder.partLogo = (ImageView) myView.findViewById(R.id.logo);
                myView.setTag(holder);
            }
            CatalogAdapter.ViewHolder holder = (CatalogAdapter.ViewHolder) myView.getTag();
            Part part = catalog.getPartFromIndex(position);
            String name = part.getPart_name();
            holder.partName.setText(name);
            String color = part.getColor_name();
            holder.partColor.setText(color);
            holder.partLogo.setImageResource(R.drawable.lego_head);
            if (!catalog.getPartFromIndex(position).getPart_img_url().equals("")) {
                Picasso.with(context).load(part.getPart_img_url()).placeholder(R.drawable.lego_head).into(holder.partImage);
            }
            return myView;
        }
    }
}