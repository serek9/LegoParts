package com.example.dam.legoparts;


import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PartRepository {
    private ArrayList<Part> partRepository;

    public PartRepository() {
        partRepository = new ArrayList<Part>();
    }

    public ArrayList<Part> getParts() { return this.partRepository; }

    public Part getPartFromIndex(int index){
        return this.partRepository.get(index);
    }

    public int getNumOfParts(){
        return this.partRepository.size();
    }

    public boolean loadFromTsv(String f){
        List<String> lineas = Arrays.asList(f.split("\n"));

        for(int i = 10; i < lineas.size(); i++){
            List<String> items = Arrays.asList(lineas.get(i).split("\t"));
            //public Part(String part_id, int qty, int ldraw_color, String part_name, String color_name, String part_img_url, String element_id,
            // String element_img_url, int rb_color_id, int part_type_id) {

            Part p = new Part(items.get(1),Integer.parseInt(items.get(2)),Integer.parseInt(items.get(3)),items.get(4),items.get(5),
                    items.get(6),items.get(7),items.get(8),0,Integer.parseInt(items.get(10)));

            partRepository.add(p);
        }
        return true;
    }
}
