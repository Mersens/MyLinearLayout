package com.zzu.ehome.mylinearlayout;

/**
 * Created by Mersens on 2016/10/22.
 */

public class ItemBean {
    private String name;

    public ItemBean(){

    }

    public ItemBean(String name, String img) {
        this.name = name;
        this.img = img;
    }

    private String img;


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
