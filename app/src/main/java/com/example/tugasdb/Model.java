package com.example.tugasdb;

public class Model {
    private int id;
    private String itemname;
    private String itemprice;
    //generate constructor

    public Model(int id, String itemname, String itemprice) {
        this.id = id;
        this.itemname = itemname;
        this.itemprice = itemprice;
    }
    //generate getter and setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getItemprice() {
        return itemprice;
    }

    public void setItemprice(String itemprice) {
        this.itemprice = itemprice;
    }
}
