package com.bawei.zhangchongru.bean;

/**
 * @Author: zhang
 * @Date: 2019/4/2 15:31
 * @Description:
 */
public class ListBean {
    private String images;
    private int num;
    private int price;
    private String title;
    private boolean selects;

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSelects() {
        return selects;
    }

    public void setSelects(boolean selects) {
        this.selects = selects;
    }
}
