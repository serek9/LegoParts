package com.example.dam.legoparts;

//{"part_id":"2653",
//        "qty":"1",
//        "ldraw_color_id":"0",
//        "type":1,
//        "part_name":"Brick Special 1 x 4 with Groove",
//        "color_name":"Black",
//        "part_img_url":"https:\/\/img.rebrickable.com\/img\/pieces\/elements\/265326.jpg",
//        "element_id":"265326",
//        "element_img_url":"https:\/\/img.rebrickable.com\/img\/pieces\/elements\/265326.jpg",
//        "rb_color_id":0,
//        "part_type_id":21}

public class Part {
    private int part_id;
    private int qty;
    private int ldraw_color_id;
    private int type;
    private String part_name;
    private String color_name;
    private String part_img_url;
    private int element_id;
    private String element_img_url;
    private int rb_color_id;
    private int part_type_id;

    public Part() {}

    public Part(int part_id, int qty, int ldraw_color_id, int type, String part_name, String color_name, String part_img_url, int element_id, String element_img_url, int rb_color_id, int part_type_id) {
        this.part_id = part_id;
        this.qty = qty;
        this.ldraw_color_id = ldraw_color_id;
        this.type = type;
        this.part_name = part_name;
        this.color_name = color_name;
        this.part_img_url = part_img_url;
        this.element_id = element_id;
        this.element_img_url = element_img_url;
        this.rb_color_id = rb_color_id;
        this.part_type_id = part_type_id;
    }

    public int getPart_id() {return part_id;}

    public void setPart_id(int part_id) {this.part_id = part_id;}

    public int getQty() {return qty;}

    public void setQty(int qty) {this.qty = qty;}

    public int getLdraw_color_id() {return ldraw_color_id;}

    public void setLdraw_color_id(int ldraw_color_id) {this.ldraw_color_id = ldraw_color_id;}

    public int getType() {return type;}

    public void setType(int type) {this.type = type;}

    public String getPart_name() {return part_name;}

    public void setPart_name(String part_name) {this.part_name = part_name;}

    public String getColor_name() {return color_name;}

    public void setColor_name(String color_name) {this.color_name = color_name;}

    public String getPart_img_url() {return part_img_url;}

    public void setPart_img_url(String part_img_url) {this.part_img_url = part_img_url;}

    public int getElement_id() {return element_id;}

    public void setElement_id(int element_id) {this.element_id = element_id;}

    public String getElement_img_url() {return element_img_url;}

    public void setElement_img_url(String element_img_url) {this.element_img_url = element_img_url;}

    public int getRb_color_id() {return rb_color_id;}

    public void setRb_color_id(int rb_color_id) {this.rb_color_id = rb_color_id;}

    public int getPart_type_id() {return part_type_id;}

    public void setPart_type_id(int part_type_id) {this.part_type_id = part_type_id;}

    @Override
    public String toString() {
        return "Part{" +
                "part_id=" + part_id +
                ", qty=" + qty +
                ", ldraw_color_id=" + ldraw_color_id +
                ", type=" + type +
                ", part_name='" + part_name + '\'' +
                ", color_name='" + color_name + '\'' +
                ", part_img_url='" + part_img_url + '\'' +
                ", element_id=" + element_id +
                ", element_img_url='" + element_img_url + '\'' +
                ", rb_color_id=" + rb_color_id +
                ", part_type_id=" + part_type_id +
                '}';
    }
}
