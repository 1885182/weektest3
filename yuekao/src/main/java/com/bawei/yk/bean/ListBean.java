package com.bawei.yk.bean;

/**
 * @Author: zhang
 * @Date: 2019/4/4 9:06
 * @Description:
 */
public class ListBean {
    private int price;
    private int num;
    private String title;
    private boolean selects;
    private String images;
    private int pid;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
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
