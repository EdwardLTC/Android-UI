package com.edward.lab3;

public class ModelData {

    public ModelData(String txt, int img) {
        this.txt = txt;
        this.img = img;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    private String txt;
    private int img;
}
